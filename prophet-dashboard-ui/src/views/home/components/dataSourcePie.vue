<template>
    <div style="width:100%;height:100%;" id="data_source_con"></div>
</template>

<script>
import echarts from 'echarts';
import * as dashboradApi from "@/api/dashborad/dashborad-api.js";
import * as dictApi from "@/api/dict";

export default {
    name: 'dataSourcePie',
    data () {
        return {
          option : {
            tooltip : {
              trigger: 'axis',
              axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
              }
            },
            legend: {
              data: []
            },
            grid: {
              left: '1%',
              right: '4%',
              bottom: '3%',
              containLabel: true
            },
            xAxis:  {
              type: 'value'
            },
            yAxis: {
              type: 'category',
              data: []
            },
            series: []
          },
          accessModeChart:'',
          productDicts:'',
        };
    },
    mounted () {
      this.drawAccess();
    },
    methods:{
      loadData:function () {

        dashboradApi.accressMode.r().then(res => {
          let accessModeLegendList = res.data.accessModeLegendList;
          let accessModeDataList = res.data.accessModeDataList;

          let pcodeList = [];
          this.productDicts.forEach(p=>{pcodeList.push(p.dictValue)});
            accessModeLegendList.forEach(v=>{
                let seriesObject = new Object();
                seriesObject.name = v;
                seriesObject.type ='bar';
                seriesObject.stack = '总量';

                seriesObject.label={
                    normal: {
                      show: true,
                        position: 'insideRight'
                    }
                };
                seriesObject.data =[];
                accessModeDataList.forEach(v=>{
                  if(v.accessMode == seriesObject.name ){
                      seriesObject.data.push(v.amCount);
                  }
                });

                this.option.series.push(seriesObject);
            });


          this.option.yAxis.data= pcodeList;
          this.option.legend.data= accessModeLegendList;
          this.accessModeChart.setOption(this.option);
        }).catch((err) => {
          console.log(err);
        });
      },
      drawAccess:function () {
        this.accessModeChart = this.$echarts.init(document.getElementById('data_source_con'));
        this.loadData();

      },
      getProduct:function(){
        dictApi.dict.product().then(res => {
          this.productDicts = res.datas;
        }).catch((err) => {
          console.log(err);
        });
      },
    },

    created(){
      this.getProduct();
    }

};
</script>
