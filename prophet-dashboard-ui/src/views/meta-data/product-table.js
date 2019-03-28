import Util from '@/libs/util.js';

let productTable = {
  data() {
    return {
      pageTotal:0,
      productListTableColumns:[
        {
          type: 'action',
          width: 100,
          title:'操作',
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h('Button', {
                props: {
                  type: 'warning',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                    Util.showMessage(this, {type: 'success', msg: '这个功能还没开发，没啥用。'});
                  }
                }
              }, '编辑')
            ]);
          }
        },
        {
          title: '项目名称',
          key: 'productName',
          width:200,
          align: 'center'
        },
        {
          title: '项目代码',
          key: 'productCode',
          width:120,
          align: 'center'
        },
        {
          title: '接入时间',
          key: 'createTime',
          width:160,
          align: 'center'
        },
        {
          title: '数据接收密匙',
          key: 'productSecret',
          width:220,
          align: 'center'
        },
        {
          title: '项目描述',
          key: 'productDesc',
          align: 'center'
        }
      ],
    }
  }
}


export default productTable;
