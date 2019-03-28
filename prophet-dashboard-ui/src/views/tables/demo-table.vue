<style lang="less">
  @import './demo-table.less';
</style>
<template>
  <div>
    <form-search
      :formItems="formItems"
      @on-form-search="handleFormSearch"
    >
    </form-search>
    <Row>
      <Card style="margin-bottom: 5px">
        <Row>
          <Button v-if="$has([tables.create])" type="primary" @click="handleCreate">{{ $t('btn_create') }}</Button>
          <Button v-if="$has([tables.update])" type="primary" @click="handleUpdate" style="margin-left: 5px">{{ $t('btn_edit') }}</Button>
          <Button v-if="$has([tables.remove])" type="error" @click="handleDel" style="margin-left: 5px">{{ $t('btn_delete') }}</Button>
        </Row>
        <Row style="margin-top: 5px">
          <pageable-table
            refs="table"
            :loading="loading"
            :columns="tableColumns"
            v-model="tableData"
            :pageTotal="pageTotal"
            @on-page-change="handlePageChange"
            @on-checkbox-select="handleSelect"
          >
          </pageable-table>
        </Row>
        <div>
          <Modal v-model="editDataModalFlag" class-name="vertical-center-modal" width="800">
            <p slot="header">
              <Icon type="information-circled"></Icon>
              <span>{{this.dataModalTitle}}</span>
            </p>
            <div class="center">
              <Form :model="editDataModalObject"  label-position="right" :label-width="80">
                <Row type="flex" justify="start" :gutter="16">
                  <Col :xs="24" :sm="12">
                    <FormItem :label="$t('label_user_name')">
                      <Input v-model="editDataModalObject.username" :placeholder="$t('placeholder_text')" ></Input>
                    </FormItem>
                  </Col>
                  <Col :xs="24" :sm="12">
                    <FormItem label="密码">
                      <Input v-model="editDataModalObject.password" type="password"  :placeholder="$t('placeholder_text')"></Input>
                    </FormItem>
                  </Col>
                </Row>
                <Row type="flex" justify="start" :gutter="16">
                  <Col :xs="24" :sm="12">
                    <FormItem label="邮箱">
                      <Input v-model="editDataModalObject.email" type="email" :placeholder="$t('placeholder_text')"></Input>
                    </FormItem>
                  </Col>
                  <Col :xs="24" :sm="12">
                    <FormItem label="手机号">
                      <Input v-model="editDataModalObject.mobile" :placeholder="$t('placeholder_text')"></Input>
                    </FormItem>
                  </Col>
                </Row>
                <Row type="flex" justify="start">
                  <Col span="24">
                    <FormItem label="角色">
                      <CheckboxGroup v-model="editDataModalObject.roleIdList">
                        <Checkbox label="1">TESTER</Checkbox>
                        <Checkbox label="2">PG</Checkbox>
                      </CheckboxGroup>
                    </FormItem>
                  </Col>
                </Row>
                <Row type="flex" justify="start">
                  <Col span="24">
                    <FormItem label="状态">
                      <RadioGroup v-model="editDataModalObject.status">
                        <Radio label="0">禁用</Radio>
                        <Radio label="1">正常</Radio>
                      </RadioGroup>
                    </FormItem>
                  </Col>
                </Row>
              </Form>
            </div>
            <div slot="footer">
              <Button type="ghost" @click="editDataModalFlag=false">取消</Button>
              <Button type="primary" :loading="modalLoading" @click="doEditData">确定</Button>
            </div>
          </Modal>
        </div>
      </Card>
    </Row>
  </div>
</template>
<script>
  import pageableTable from '../common-components/table/pageable-table.vue';
  import formSearch from '../common-components/table/form-search.vue';

  import * as tables from "@/api/sys-user";
  import Util from '@/libs/util.js';

  let mixin = {
    data: function() {
      return { tables }
    }
  };

  export default {
    mixins: [mixin],
    components: {
      formSearch,
      pageableTable
    },
    data () {
      return {
        formItems: [
          {
            key: "inputValue",
            value: '',
            type: 'Input',
            label: this.$t('label_user_name')
          },
          {
            key: "radioValue",
            value: '',
            type: 'Radio',
            label: this.$t('label_user_name'),
            options: [{label: "yes", content: this.$t('label_user_name')},
              {label: "no", content: this.$t('label_user_name')}
              ]
          },
          {
            key: "checkboxValues",
            value: [],
            type: 'Checkbox',
            label: this.$t('label_user_name'),
            options: [{label: "Eat", content: this.$t('label_user_name')},
              {label: "Sleep", content: this.$t('label_user_name')}
            ]
          },
          {
            key: "selectValue",
            value: '',
            type: 'Select',
            label: this.$t('label_user_name'),
            options: [{label: "Eat", content: this.$t('label_user_name')},
              {label: "Sleep", content: this.$t('label_user_name')}
            ]
          }
        ],
        dataModalTitle: '',
        showDataModalObject: {},
        editDataModalFlag: false,
        editDataModalObject: {},
        modalLoading: false,
        //是否加载遮罩
        loading: false,
        pageTotal: 0,
        tableData: [],
        selectedData: [],
        index: 0,
        tableColumns: [
          {
            type: 'selection',
            width: 60,
            align: 'center'
          },
          {
            title: '序号',
            type: 'index',
            width: 80,
            align: 'center'
          },
          {
            title: '用户名',
            key: 'username'
          },
          {
            title: '邮箱',
            key: 'email'
          },
          {
            title: '手机号',
            key: 'mobile'
          },
          {
            title: '状态',
            key: 'status',
            render: (h, params) => {
              const row = params.row;
              const color = row.status === 1 ? 'green' : row.status === 2 ? 'green' : 'red';
              const text = row.status === 1 ? '正常' : '禁用';
              return h('Tag', {
                props: {
                  // type: 'dot',
                  color: color
                }
              }, text);
            }
          },
          {
            title: '创建时间',
            key: 'createTime'
          }
        ]
      }
    },
    mounted(){
      //加载滚动条
      Util.smoothScroll(this, '.center');
    },
    computed: {
      placeholderText () {
        return $t('placeholder_text');
      },
      placeholderDate () {
        return $t('placeholder_date');
      }
    },
    watch: {
      editDataModalFlag () {
        if (this.editDataModalFlag) {
          Util.smoothScrollTo(this, '.center', 0, 0);
        }
      }
    },
    methods:{
      // 获取表格信息
      loadTableData(params){
        this.loading = true;
        tables.request.r(params).then(res => {
          this.loading = false;
          this.selectedData = [];
          this.tableData = res.data.list;
          this.tableData.forEach(item => {
            if (item.password) {
              item.password = '';
            }
            if (item.roleIdList == null) {
              item.roleIdList = [];
            }
          });
          this.pageTotal = res.data.totalCount;
        }).catch((err) => {
          this.loading = false;
          console.log(err);
        });
      },
      // 检索处理
      handleFormSearch (params) {
        let searchParams = Object.assign(params, {
          page: 1,
          limit: 10
        });
        this.loadTableData(searchParams);
      },
      // 翻页处理
      handlePageChange(params){
        let searchParams = params || {
          page: 1,
          limit: 10
        };
        this.loadTableData(searchParams);
      },
      // 新建处理
      handleCreate () {
        this.dataModalTitle = this.$t("title_create_info");;
        this.editDataModalFlag = true;
        this.editDataModalObject = {};
      },
      // 更新处理
      handleUpdate () {
        if (!this.selectedData || this.selectedData.length == 0) {
          let config = {
            type: 'warning',
            msg: this.$t("warning_0001")
          };
          Util.showMessage(this, config);
          return false;
        } else if (this.selectedData.length > 1) {
          let config = {
            type: 'warning',
            msg: this.$t("warning_0002")
          };
          Util.showMessage(this, config);
          return false;
        }

        this.dataModalTitle = this.$t("title_update_info");
        this.editDataModalFlag = true;
        this.editDataModalObject = Object.assign({}, this.selectedData[0]);
      },
      // 删除处理
      handleDel (val, index) {
        this.index = index;
        this.tableData.splice(index, 1);
        this.$Message.success('删除了第' + (index + 1) + '行数据');
      },
      handleSelect (selection, row) {
        this.selectedData = selection;
      },
      doEditData () {
        this.modalLoading = true;
        let promise = {};
        if (this.editDataModalObject.userId) {
          promise = tables.update.r(this.editDataModalObject);
        } else {
          promise = tables.create.r(this.editDataModalObject);
        }
        promise.then(data => {
          this.modalLoading = false;
          if (data.code == 200) {
            this.editDataModalFlag = false;
            let config = {
              type: 'success',
              msg: data.msg
            };
            Util.showMessage(this, config);
            this.handlePageChange();
          } else {
            let config = {
              type: 'error',
              msg: data.msg
            };
            Util.showMessage(this, config);
          }
        });
      }
    },
    created(){
      // 获取表格信息
      this.handlePageChange();
    }
  }
</script>
