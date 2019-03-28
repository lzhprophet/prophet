<style lang="less">
  @import "./base-info.less";
</style>
<template>
  <div>
    <Card :bordered="false">
      <Tabs :model ="selectPcode" @on-click="changeTabs">
        <TabPane v-for="(productDict,index) in  productDicts" :label="productDict.dictName" :name="productDict.dictValue" :key="index"></TabPane>
      </Tabs>

      <div class="quota-div">
        <Row>
          <Col  class="quota-header" span="24"><h4>昨日关键指标</h4></Col>
        </Row>
        <Row style="height: 150px;">
          <Col span="6" style="padding:20px;border-right: solid 1px #ebebeb;text-align: left;height: 100%" >
            <p class="p-font" style="font-size: 15px">新增用户数</p>
            <p class="p-font-bold" style="">{{baseStatisInfo.newUserCount}}</p>
            <p class="p-font"><Tooltip placement="top-start" content="历史最高值">最高值 <span style="color: red;font-weight: bold"> {{baseStatisMaxAvg.maxNu}} </span></Tooltip></p>
            <p class="p-font"><Tooltip placement="bottom-start" content="历史平均值">平均值 <span style="color: #19be6b;font-weight: bold"> {{baseStatisMaxAvg.avgNu}} </span></Tooltip></p>
          </Col>
          <Col span="6" style="padding:20px;border-right: solid 1px #ebebeb;text-align: left;height: 100%" >
            <p class="p-font" style="font-size: 15px">独立IP</p>
            <p class="p-font-bold">{{baseStatisInfo.ipCount}}</p>
            <p class="p-font"><Tooltip placement="top-start" content="历史最高值">最高值 <span style="color: red;font-weight: bold"> {{baseStatisMaxAvg.maxIp}} </span></Tooltip></p>
            <p class="p-font"><Tooltip placement="bottom-start" content="历史平均值">平均值 <span style="color: #19be6b;font-weight: bold"> {{baseStatisMaxAvg.avgIp}} </span></Tooltip></p>
          </Col>
          <Col span="6" style="padding:20px;border-right: solid 1px #ebebeb;text-align: left;height: 100%" >
            <p class="p-font" style="font-size: 15px">活跃用户数</p>
            <p class="p-font-bold" >{{baseStatisInfo.uvCount}}</p>
            <p class="p-font"><Tooltip placement="top-start" content="历史最高值">最高值 <span style="color: red;font-weight: bold"> {{baseStatisMaxAvg.maxUv}} </span></Tooltip></p>
            <p class="p-font"><Tooltip placement="bottom-start" content="历史平均值">平均值 <span style="color: #19be6b;font-weight: bold"> {{baseStatisMaxAvg.avgUv}} </span></Tooltip></p>
          </Col>
          <Col span="6" style="padding:20px;text-align: left;height: 100%" >
            <p class="p-font" style="font-size: 15px">接口调用量</p>
            <p class="p-font-bold" >{{baseStatisInfo.apiCount}}</p>
            <p class="p-font"><Tooltip placement="top-start" content="历史最高值">最高值 <span style="color: red;font-weight: bold"> {{baseStatisMaxAvg.maxApi}} </span></Tooltip></p>
            <p class="p-font"><Tooltip placement="bottom-start" content="历史平均值">平均值 <span style="color: #19be6b;font-weight: bold"> {{baseStatisMaxAvg.avgApi}} </span></Tooltip></p>
          </Col>
        </Row>

      </div>



      <Tabs size="small">
        <TabPane  label="昨日" ><yesterday-plane :pcode="selectPcode"></yesterday-plane></TabPane>
        <TabPane  label="本周" ></TabPane>
        <TabPane  label="上周" ></TabPane>
        <TabPane  label="本月" ></TabPane>
        <TabPane  label="上月" ></TabPane>
        <TabPane  label="全年" ></TabPane>
      </Tabs>



    </Card>


  </div>
</template>


<script>
  import pageableTable from '../common-components/table/pageable-table.vue';
  import formSearch from '../common-components/table/form-search.vue';
  import yesterdayPlane from './yesterday.vue';
  import baseInfoTable from "./base-info-table";
  import * as dictApi from "@/api/dict";
  import * as baseStatisApi from "@/api/dashborad/base-statis.js";

  import Util from '@/libs/util.js';

  export default {
    components: {"form-search":formSearch,"pageable-table":pageableTable,"yesterday-plane":yesterdayPlane},
    mixins: [baseInfoTable],
    data: function(){
      return {
        productDicts:[],
        selectPcode:'',
        baseStatisInfo:{},
        baseStatisMaxAvg:{},
        formItems: [
          {
            key: 'dataTime',
            value: '',
            type: 'DatePicker',
            editable: false,
            placeholder: '选择时间',
            label: '查询日期',
            options: {
              disabledDate (date) {
                return date && date.valueOf() > Date.now() - 86400000 ;
              }
            },
          },
        ],
      }
    },
    methods:{
      //打开编辑
      changeTabs(name){
        this.selectPcode = name;
      },

      initPageTabs(){
        dictApi.dict.product().then(res => {
          this.productDicts = res.datas;
          this.selectPcode = res.datas[0].dictValue;
        }).catch((err) => {
          console.log(err);
        });

      },
      loadQuotaDivData(pcode){
        baseStatisApi.countInfoByPcode.info({'pcode':pcode}).then(res => {
          this.baseStatisInfo  = res.data;
        }).catch((err) => {
          console.log(err);
        });

        baseStatisApi.maxAvgByPcode.info({'pcode':pcode}).then(res => {
          this.baseStatisMaxAvg  = res.data;
        }).catch((err) => {
          console.log(err);
        });

      }

    },
    watch: {
      // 如果 `question` 发生改变，这个函数就会运行
      selectPcode:  function (newPcode, oldPcode) {
        this.loadQuotaDivData(newPcode);
      }
    },

    created(){
      this.initPageTabs();
    }


  };
</script>
