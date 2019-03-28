<template>
  <div>
    <Row>
      <Table
        :ref="refs"
        v-if="!canEdit"
        :height="height"
        :loading="loading"
        :columns="columns"
        :data="thisTableData"
        :stripe="stripe"
        :border="border"
        :no-data-text="noDataText"
        :no-filtered-data-text="noFilteredDataText"
        :row-class-name="rowClassName"
        @on-selection-change="handleSelect"
      >
      </Table>
    </Row>
    <Row>
      <Page
        :total="pageTotal"
        placement="top"
        size="small"
        :current="pageNum"
        :page-size="pageSize"
        class-name="paging"
        show-total show-sizer
        @on-change="handlePage"
        @on-page-size-change='handlePageSize'>
      </Page>
    </Row>
  </div>
</template>
<style lang="less">
  @import './pageable-table.less';
</style>
<script>
  import util from '@/libs/util.js';

  const editButton = (vm, h, currentRow, index) => {
    return h('Button', {
      props: {
        type: currentRow.editting ? 'success' : 'primary',
        loading: currentRow.saving
      },
      style: {
        margin: '0 5px'
      },
      on: {
        'click': () => {
            vm.$emit('on-change', vm.thisTableData, index);
        }
      }
    }, '编辑');
  };
  const deleteButton = (vm, h, currentRow, index) => {
    return h('Poptip', {
      props: {
        confirm: true,
        title: '您确定要删除这条数据吗?',
        transfer: true
      },
      on: {
        'on-ok': () => {
          vm.$emit('on-delete', vm.thisTableData, index);
        }
      }
    }, [
      h('Button', {
        style: {
          margin: '0 5px'
        },
        props: {
          type: 'error',
          placement: 'top'
        }
      }, '删除')
    ]);
  };


  export default {
    props: {
      refs: {
        type: String,
        default: 'dataTable'
      },
      canEdit: {
        type: Boolean,
        default: false
      },
      //加载遮罩
      loading: {
        type: Boolean,
        default: false
      },
      //总条数
      pageTotal: {
        type: Number,
        default: 0
      },
      //每页条数
      pageSize: {
        type: Number,
        default: 10
      },
      //当前页码
      pageNum: {
        type: Number,
        default: 1
      },
      //是否显示间隔斑马纹
      stripe: {
        type: Boolean,
        default: true
      },
      //是否显示表格
      border: {
        type: Boolean,
        default: true
      },
      //表格高度
      height: {
        type: Number,
        default: 472
      },
      //数据为空时显示的提示内容
      noDataText: {
        type: String,
        default: '暂无数据',
      },
      //筛选数据为空时显示的提示内容
      noFilteredDataText: {
        type: String,
        default: '暂无筛选结果',
      },
      //表头
      columns: {
        type: Array,
        required: true
      },
      //表数据
      value: {
        type: Array,
        required: true
      }
    },
    data () {
      return {
        thisTableData: []
      };
    },
    created () {
      this.init();
    },
    methods:{
      init () {
        let vm = this;
        this.thisTableData = JSON.parse(JSON.stringify(this.value));
        this.columns.forEach(item => {
          if (item.handle) {
            item.render = (h, param) => {
              let currentRowData = this.thisTableData[param.index];
              if (item.handle.length === 2) {
                return h('div', [
                  editButton(this, h, currentRowData, param.index),
                  deleteButton(this, h, currentRowData, param.index)
                ]);
              } else if (item.handle.length === 1) {
                if (item.handle[0] === 'edit') {
                  return h('div', [
                    editButton(this, h, currentRowData, param.index)
                  ]);
                } else {
                  return h('div', [
                    deleteButton(this, h, currentRowData, param.index)
                  ]);
                }
              }
            };
          }
        });
      },
      rowClassName (row, index) {
        if (index%2 == 0) {
          return 'i-table-even-row';
        } else {
          return 'i-table-odd-row';
        }
      },
      //返回页码及每页条数给父组件
      handlePage(val){
        let params = {
          page: val,
          limit: this.pageSize
        };
        this.$emit('on-page-change', params);
      },
      //返回页码及每页条数给父组件
      handlePageSize(val){
        let params = {
          page: this.pageNum,
          limit: val
        };
        this.$emit('on-page-change', params);
      },
      //多选表格时返回选中行
      handleSelect(selection, row){
        this.$emit('on-checkbox-select', selection, row);
      }
    },
    mounted(){
      //加载滚动条
      util.smoothScroll(this, '.ivu-table-body');
    },
    watch: {
      value (data) {
        this.init();
      },
      loading () {
        //清除全选
        this.$refs[this.refs].selectAll(false);
      }
    }
  }
</script>
