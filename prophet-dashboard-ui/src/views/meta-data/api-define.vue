<style lang="less">
</style>
<template>
  <div>
    <form-search
      ref="formSearch"
      :formItems="formItems"
      @on-form-search="handleFormSearch"
    >
    </form-search>

    <Row>
      <Card style="margin-bottom: 5px">
        <Tabs ref="defineTabs" value="defineYesPane" @on-click="reloadTabDate">
          <TabPane label="已标注接口" name="defineYesPane">
            <api-difine-finish ref="defineYes"></api-difine-finish>
          </TabPane>
          <TabPane label="未标注接口" name="defineNotPane">
            <api-difine-not ref="defineNot"></api-difine-not>
          </TabPane>
        </Tabs>

      </Card>
    </Row>
  </div>
</template>
<script>
  import pageableTable from '../common-components/table/pageable-table.vue';
  import formSearch from '../common-components/table/form-search.vue';
  import * as dictApi from "@/api/dict";
  import apiDifineFinishPlane from './api-difine-finish.vue';
  import apiDifineNotPlane from './api-difine-not.vue';

  import Util from '@/libs/util.js';


  export default {

    components: {
      "form-search":formSearch,
      "pageable-table":pageableTable,
      "api-difine-finish":apiDifineFinishPlane,
      "api-difine-not":apiDifineNotPlane
    },
    data () {
      return {
        formItems: [
          {
            key: "pcode",
            value: '',
            default: true,
            type: 'SelectDict',
            label: '所属项目',
            options: this.productDicts
          },
          {
            key: "urlPath",
            value: '',
            type: 'Input',
            label: 'URL PATH'
          },
          {
            key: "name",
            value: '',
            type: 'Input',
            label: '接口名称'
          }
        ],

        dataModalTitle: '',
        showDataModalObject: {},
        editDataModalFlag: false,
        editDataModalObject: {},
        modalLoading: false,
        //是否加载遮罩

        selectedData: [],
        index: 0,

      }
    },
    mounted(){

    },
    computed: {
    },
    watch: {
    },
    methods:{
      initPage:function () {

      },
      // 获取表格信息
      loadTableData(params){
        //this.loading = true;

      },
      // 检索处理
      handleFormSearch (params) {
        let searchParams = Object.assign(params, {
          page: 1,
          limit: 10
        });
        //this.loadTableData(searchParams);
        this.reloadTabDate();
      },

      //刷新子组件数据
      reloadTabDate(){
        let params = this.$refs.formSearch.getParams();
        let selectedTab =  this.$refs.defineTabs.activeKey;
        if(selectedTab == "defineYesPane"){
          params.identStatus = 1;
          this.$refs.defineYes.loadTableData(params);
        }else if(selectedTab == "defineNotPane"){
          params.identStatus = 0;
          this.$refs.defineNot.loadTableData(params);
        }
      },

      handleSelect (selection, row) {
        this.selectedData = selection;
      },
    },
    created(){
      this.initPage();
      // 获取表格信息

    }
  }
</script>
