<style lang="less">
</style>
<template>
  <div>
    <Row>
      <pageable-table
        refs="table"
        :loading="loading"
        :columns="tableColumns"
        :pageNum=pageNum
        v-model="tableData"
        :pageTotal="pageTotal"
        @on-page-change="handlePageChange"
      >
      </pageable-table>
    </Row>

    <Modal v-model="editDataModalFlag" class-name="vertical-center-modal" width="800">
      <p slot="header">
        <Icon type="ios-checkmark"></Icon>
        <span>标注</span>
      </p>
      <div class="center">
        <Form :model="editDataModalObject"  label-position="right" :label-width="80">
          <Row type="flex" justify="start" :gutter="16">
            <Col :xs="12" :sm="12">
            <FormItem label="接口名称">
              <Input v-model="editDataModalObject.name" ></Input>
            </FormItem>
            </Col>
            <Col :xs="12" :sm="12">
            <FormItem label="URL PATH">
              <Input v-model="editDataModalObject.urlPath" ></Input>
            </FormItem>
            </Col>
          </Row>
          <Row type="flex" justify="start" :gutter="16">
            <Col :xs="24" :sm="24">
            <FormItem label="URL">
              <Input v-model="editDataModalObject.url"  readonly></Input>
            </FormItem>
            </Col>
          </Row>
          <Row type="flex" justify="start" :gutter="16">
            <Col :xs="12" :sm="12">
            <FormItem label="所属项目">
              <Input v-model="editDataModalObject.pname"  disabled></Input>
            </FormItem>
            </Col>
          </Row>
          <Row type="flex" justify="start">
            <Col span="24">
            <FormItem label="接口描述" >
              <Input v-model="editDataModalObject.remark" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="Enter remark..."></Input>
            </FormItem>
            </Col>
          </Row>
        </Form>
      </div>
      <div slot="footer">
        <Button type="ghost" @click="editDataModalFlag=false">取消</Button>
        <Button type="primary"  @click="">确定</Button>
      </div>
    </Modal>

  </div>
</template>

<script>
  import pageableTable from '../common-components/table/pageable-table.vue';
  import Util from '@/libs/util';
  import * as apiDefineApi from "@/api/metadata/metadata-apidefine";


  export default {
    components: {"pageable-table":pageableTable},
    data () {
      return {
        editDataModalFlag:false,
        editDataModalObject:{
          name:'',
          uslPath:'',
        },
        loading: false,
        pageTotal: 0,
        searchParams:{},
        pageNum:1,
        tableColumns: [
          {
            title: '标注',
            type: 'option',
            width: 80,
            align: 'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'warning',
                    size: 'small',
                    icon:"edit"
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.handleIdent(params);
                    }
                  }
                })
              ]);
            }
          },
          {
            title: '接口名称',
            width: 180,
            key: 'name'
          },
          {
            title: 'URL（资源地址）',
            key: 'urlPath'
          },
          {
            title: '所属项目',
            width: 140,
            key: 'productName'
          },
          {
            title: '接口描述',
            width: 240,
            key: 'remark'
          },
          {
            title: '标注时间',
            width: 140,
            key: 'identTime'
          }
        ],
        tableData: [],
      }
    },
    created () {
      //this.init();
    },
    mounted(){

      this.loadTableData(this.searchParams);
    },
    computed: {
    },
    watch: {
    },
    methods:{
      loadTableData(params){
        debugger
        this.searchParams = params;
        params.identStatus = 0;
        this.pageNum = params.page;
        apiDefineApi.list.r(params).then(res => {
          this.pageTotal=res.count;
          this.tableData = res.datas;
        }).catch((err) => {
          console.log(err);
        });
      },
      // 翻页处理
      handlePageChange(params){
        debugger
        this.searchParams = Object.assign(this.searchParams, params);

        this.loadTableData(this.searchParams);
      },
      //标注
      handleIdent (params) {
        debugger
        this.editDataModalFlag = true;
        this.editDataModalObject.name=params.row.name;
        this.editDataModalObject.url=params.row.urlPath;
        this.editDataModalObject.pname=params.row.productName;
        this.editDataModalObject.remark=params.row.remark;
      },

    }
  }
</script>
