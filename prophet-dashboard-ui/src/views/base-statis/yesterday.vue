<style lang="less">

</style>
<template>
  <div>
      <card style="margin-bottom: 10px;">
        <div id="myChart" style="height:300px; width:100%"></div>
      </card>
      <Table
        border
        :columns="ipTableColumns"
        :data="tableData"
      >
      </Table>
  </div>
</template>

<script>
  import pageableTable from '../common-components/table/pageable-table.vue';
  import Util from '@/libs/util';
  import * as baseStatisApi from "@/api/dashborad/base-statis.js";

  export default {
    components: {"pageable-table":pageableTable},
    props: ['pcode'],
    data () {
      return {
        ipTableColumns:[
          {
            title: '日期',
            key: 'pday',
            align: 'center'
          },
          {
            title: '时段',
            key: 'timeSlot',
            align: 'center'
          },
          {
            title: '独立IP',
            key: 'ipCount',
            align: 'center'
          },
          {
            title: '新增用户',
            key: 'newUserCount',
            align: 'center'
          },
          {
            title: '活跃用户',
            key: 'uvCount',
            align: 'center'
          }
          ,
          {
            title: '接口调用',
            key: 'apiCount',
            align: 'center'
          }

        ],
        tableData:[],
        myChart:'',
        myChartOption:{
          series: [

            {
              name:'新增用户',
              type:'line',
              stack: '总量',
              data:[]
            },
            {
              name:'独立IP',
              type:'line',
              stack: '总量',
              data:[]
            },
            {
              name:'活跃用户',
              type:'line',
              stack: '总量',
              data:[]
            },
            {
              name:'接口调用',
              type:'line',
              stack: '总量',
              data:[]
            }
          ]
        }
      }
    },
    created () {
      //this.init();
    },
    mounted(){
      this.drawLine();
    },
    computed: {
    },
    watch: {
      pcode:  function (newPcode, oldPcode) {
        this.loadTableData(newPcode);
      }
    },
    methods:{
      loadTableData(newPcode){
        baseStatisApi.timeSloatBasestaticInfo.list({'pcode':newPcode}).then(res => {
          this.tableData = res.datas;
          let ipData=[],uvData=[],apiData=[],newUserData=[];


          this.tableData.forEach(v=>{
            ipData.push(parseInt(v.ipCount));
            uvData.push(parseInt(v.uvCount));
            apiData.push(parseInt(v.apiCount));
            newUserData.push(parseInt(v.newUserCount));

          });
          this.myChartOption.series[0].data=newUserData;
          this.myChartOption.series[1].data=ipData;
          this.myChartOption.series[2].data=uvData;
          this.myChartOption.series[3].data=apiData;
          this.myChart.setOption(this.myChartOption);
        }).catch((err) => {
          console.log(err);
        });

      },

      drawLine(){
        // 基于准备好的dom，初始化echarts实例
        this.myChart = this.$echarts.init(document.getElementById('myChart'))
        // 绘制图表
        this.myChart.setOption({

          tooltip: {
            trigger: 'axis'
          },

          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['02:00','04:00','06:00','08:00','10:00','12:00','14:00','16:00','18:00','20:00','22:00','24:00']
          },
          yAxis: {
            type: 'value'
          },
          grid: {
            left: '10px',
            right: '20px',
            bottom: '50px',
            top:'20px',
            containLabel: true
          },
          legend: {
            bottom:'5px',
            data:['新增用户','独立IP','活跃用户','接口调用']
          },
          series:this.myChartOption.series

        });
      }

    }
  }
</script>
