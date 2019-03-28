<template>
    <div style="width:100%;height:100%;" id="service_request_con"></div>
</template>

<script>
import echarts from 'echarts';
import * as dashboradApi from "@/api/dashborad/dashborad-api.js";

export default {
    name: 'serviceRequests',
    data () {
      return {
        option : {
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'cross',
              label: {
                backgroundColor: '#6a7985'
              }
            }
          },
          grid: {
            top: '3%',
            left: '1.2%',
            right: '1%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: [
            {
              type: 'category',
              boundaryGap: false,
              data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
            }
          ],
          yAxis: [
            {
              type: 'value'
            }
          ],
          series: [],
        },
        serviceRequestCharts:'',

      };
    },
    mounted () {

        this.loadData();

            //series: [],
            /*series: [
              {
                name: '运营商/网络服务',
                type: 'line',
                stack: '总量',
                areaStyle: {normal: {
                  color: '#2d8cf0'
                }},
                data: [120, 132, 101, 134, 90, 230, 210]
              },
              {
                name: '快递/电商',
                type: 'line',
                stack: '总量',
                label: {
                  normal: {
                    show: true,
                    position: 'top'
                  }
                },
                areaStyle: {normal: {
                  color: '#398DBF'
                }},
                data: [820, 645, 546, 745, 872, 624, 258]
              }
            ]*/



    },
    methods:{
      loadData:function () {
        this.serviceRequestCharts = echarts.init(document.getElementById('service_request_con'));
        dashboradApi.apiWeekCount.r().then(res => {
          let tempCode = "";
          let seriesObject ;
          let productData= [];
          let temp;
          res.datas.forEach((v,step)=> {
                if(tempCode !== v.pcode) {
                  temp = {pcode: '', data: []};
                  temp.pcode = v.pcode;
                  temp.data.push(v.apiWeekCount);
                  productData.push(temp);
                }else{
                  temp.data.push(v.apiWeekCount);
                }
                tempCode =v.pcode;
            }
          );

          productData.forEach((v,step)=>{
            seriesObject = new Object();
            seriesObject.name = v.pcode;
            seriesObject.type = 'line';
            seriesObject.stack = '总量';

            seriesObject.areaStyle={normal: {}};
            seriesObject.label = {
              normal: {
                show: true,
              }
            };
            seriesObject.data =[];
            for(let i =0;i<7;i++) {
              if (v.data[i]) {
                seriesObject.data.push(v.data[i]);
              } else {
                seriesObject.data.push(0);
              }
            }
            this.option.series.push(seriesObject);

          });
          /*


          */


          this.serviceRequestCharts.setOption(this.option);

        }).catch((err) => {
          console.log(err);
        });

      }

    }
};
</script>
