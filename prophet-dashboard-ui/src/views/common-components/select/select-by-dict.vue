<template>
  <div>
    <Select v-model="sysDictSelect"  :disabled="disabled" 	 :style="selectWidth" @on-change="handChange"  :label-in-value="true">
      <Option v-for="item in dictList" :value="item.dictValue"  :key="item.dictValue">{{ item.dictName }}</Option>
    </Select>
  </div>
</template>

<script>

  import * as dict from "@/api/dict";

  export default {
    mixins: [dict],
    props:['dictKey','width','selected','setDisabled'],
    data:function() {
        return {
          dictList:[],
          selectWidth:'0',
          sysDictSelect:'',
          disabled:this.setDisabled
        }
    },
    methods:{
      //加载数据
      init(){
        if(this.width){
          this.selectWidth = "width:"+this.width +"px";
        }

        dict.list.r({dictKey:this.dictKey}).then(res => {
          this.dictList = res.datas;
        }).catch((err) => {
          console.log(err);
        });

      },
      handChange:function (value) {
        this.$emit('on-select-change', value);
      }
    },
    watch:{
     selected:function(newVal,oldVal){
        this.sysDictSelect = ''+newVal;
      },
      setDisabled:function (newVal,oldVal) {
        this.disabled = newVal;
      }
    },

    created(){
      this.init();
    }


    };

</script>
