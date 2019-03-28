<template>
  <div class="form-search">
    <Card style="margin-bottom: 5px">
      <p slot="title">
        <Icon type="ios-search-strong" style="font-size: 15px"></Icon>
        {{ $t('title_search_condition') }}
      </p>
      <div slot="extra">
        <Button type="primary" @click="handleSubmit('formSearch')" >{{ $t('btn_search') }}</Button>
        <Button type="ghost" @click="handleReset('formSearch')" style="margin-left: 5px">{{ $t('btn_reset') }}</Button>
        <a @click="toggleClick" style="margin-left: 5px">
          <!--{{this.collapse ? $t('label_open'): $t('label_close')}}-->
          <Icon :type="iconType"></Icon>
        </a>
      </div>
      <Form ref="formSearch" :model="formSearch" label-position="right" :label-width="80">
        <Row  v-for="(row, rowIndex) of listItems" :key="rowIndex" :style="rowIndex >= 0 ? rowClass : ''" type="flex" justify="start" :gutter="16">
          <Col :xs="24" :sm="6" v-for="(item, itemIndex) of row.items" :key="itemIndex" >
            <FormItem :label="item.label" :prop="'items.' + item.index + '.value'">
                <Input v-if="item.type == 'Input'" v-model="item.value" :placeholder="item.placeholder ? item.placeholder : $t('placeholder_text')"></Input>
                <RadioGroup v-else-if="item.type == 'Radio'" v-model="item.value">
                  <Radio v-for="(option, optionIndex) of item.options" :key="optionIndex" :label="option.label">{{option.content}}</Radio>
                </RadioGroup>
                <CheckboxGroup v-else-if="item.type == 'Checkbox'" v-model="item.value">
                  <Checkbox v-for="(option, optionIndex) of item.options" :key="optionIndex" :label="option.label">{{option.content}}</Checkbox>
                </CheckboxGroup>
                <Select v-else-if="item.type == 'Select'" v-model="item.value">
                  <Option v-for="(option, optionIndex) of item.options" :key="optionIndex" :value="option.label">{{option.content}}</Option>
                </Select>

                <Select v-else-if="item.type == 'SelectDict'" v-model="item.value">
                  <template v-for = "(option, optionIndex) of  dictData">
                    <Option v-if = "option.dictKey === item.dictKey"  :key="optionIndex" :value="option.dictValue">{{option.dictName}}</Option>
                  </template>
                </Select>

                <DatePicker :editable="item.editable" v-else-if="item.type == 'DatePicker'" v-model="item.value" :options="item.options" type="date" :placeholder="item.placeholder ? item.placeholder : '选择日期'" ></DatePicker>
                <DatePicker  :editable="item.editable" type="daterange"  v-else-if="item.type == 'DateRange'"   v-model="item.value":placeholder="item.placeholder ? item.placeholder : '选择日期'" ></DatePicker>
            </FormItem>
          </Col>
        </Row>
      </Form>
    </Card>
  </div>
</template>
<style lang="less">
  @import 'from-search.less';
</style>
<script>
  import Util from '@/libs/util';
  import * as dictApi from "@/api/dict";



  export default {
    props: {
      //父组件传入表单项目
      formItems: {
        required: true
      }
    },
    data () {
      return {
        //字典下拉况
        dictData:[],
        //表单项目
        formSearch: {
          items: []
        },
        //展开、收起
        collapse: true

      }
    },
    created () {
      this.init();
    },

    computed: {
      iconType () {
        return this.collapse ? "android-remove-circle" : "android-add-circle";
      },
      rowClass () {
        return this.collapse ? "" :"display: none" ;
      },
      listItems: function () {
        let list = this.formSearch.items;
        let listItems = [];
        let index = 0;
        //每行显示4个项目
        let sectionCount = 4;
        for (let i = 0; i < list.length; i++) {
          index = parseInt(i / sectionCount);
          if (listItems.length <= index) {
            listItems.push({items:[]});
          }
          //为prop设置index
          list[i].index = i;
          listItems[index].items.push(list[i]);
        }
        return listItems;
      },


    },
    methods:{

      init () {
        this.formSearch.items = Util.deepCopy(this.formItems);

        this.formSearch.items.forEach(dictItem => {
          if(dictItem.type  == 'SelectDict'){

            //产品下拉列表
            if(dictItem.key  == 'pcode'){
              dictApi.dict.product().then(res => {
                this.dictData = res.datas;
              }).catch((err) => {
                console.log(err);
              });
            }
            return ;
          }

        });


      },
      toggleClick () {
        this.collapse = !this.collapse;
      },
      selectByDictChange (option){

      },
      handleSubmit (name) {
        this.$refs[name].validate((valid) => {
          if (valid) {
            let params  = this.getParams();
            this.$emit('on-form-search', params);
          } else {
            this.$Message.error('Fail!');
          }
        })
      },
      getParams(){
        let params = {};
        this.formSearch.items.forEach(item => {
          params[item.key] = item.value;

          if(item.type === 'DateRange'){

            if(params[item.key]){
              params[item.startDate?item.startDate:'startDate'] = "";
              params[item.endDate?item.endDate:'endDate'] = "";
              if(params[item.key][0] != "" ||  params[item.key][1] != ""){
                params['startDate'] = params[item.key][0].Format("yyyy-MM-dd");
                params['endDate'] = params[item.key][1].Format('yyyy-MM-dd');
              }
            }
          }

        });

        return params;
      },
      handleReset (name) {
        this.$refs[name].resetFields();

      }
    }
  }
</script>
