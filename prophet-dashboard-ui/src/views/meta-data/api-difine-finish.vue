<style lang="less">
</style>
<template>
  <div>
    <Row>
      <pageable-table
        refs="table"
        :loading="loading"
        :pageNum=pageNum
        :columns="tableColumns"
        v-model="tableData"
        :pageTotal="pageTotal"
        @on-page-change="handlePageChange"
      >
      </pageable-table>
    </Row>
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
        loading: false,
        pageTotal: 0,
        pageNum:1,
        searchParams:{},
        tableColumns: [
          {
            title: '操作',
            type: 'option',
            width: 160,
            align: 'center'
          },
          {
            title: '所属项目',
            width: 180,
            key: 'productName'
          },
          {
            title: '接口名称',
            width: 180,
            key: 'name'
          },
          {
            title: 'URL PATH（接口路径）',
            key: 'urlPath'
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
        this.searchParams = params;
        params.identStatus = 1;
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
        this.searchParams = Object.assign(params, {
          page: 1,
          limit: 10
        });
        this.loadTableData(this.searchParams);
      },

    }
  }
</script>
