<style lang="less">

</style>
<template>
  <div>


    <Card :bordered="false">

       <Row style="margin-top: 5px;margin-bottom: 5px">
         <Table border :columns="productListTableColumns"   :data="tableData"></Table>
       </Row>

    </Card>

    <Card style="margin-top: 5px">
      <Form ref="productFrom" :model="productFromModel" :rules="ruleProductfrom" >
        <Row>
          <Col span="5">
          <FormItem>
            <Input type="text"  v-model="productFromModel.productName"  placeholder="产品名称">
            <span slot="prepend">项目名称</span>
            </Input>
          </FormItem>
          </Col>
          <Col span="1" style="text-align: center">&nbsp;&nbsp;&nbsp;</Col>
          <Col span="5">
          <FormItem prop="code">
            <Input type="text" v-model="productFromModel.productCode" placeholder="产品标识">
            <span slot="prepend">项目标识</span>
            </Input>
          </FormItem>
          </Col>
          <Col span="1" style="text-align: center">&nbsp;&nbsp;&nbsp;</Col>
          <Col span="5">
          <FormItem prop="code">
            <Input type="text" v-model="productFromModel.productAdmin" placeholder="项目负责人">
            <span slot="prepend">项目负责人</span>
            </Input>
          </FormItem>
          </Col>
          <Col span="1" style="text-align: center">&nbsp;&nbsp;&nbsp;</Col>
          <Col span="6">
          <FormItem prop="code">
            <Input type="text" v-model="productFromModel.productSecret" placeholder="数据接收密匙">
            <span slot="prepend">数据接收密匙	</span>
            </Input>
          </FormItem>
          </Col>

        </Row>

        <FormItem prop="desc">
          <Input v-model="productFromModel.productDesc"  type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="项目描述...">
          </Input>
        </FormItem>
      </Form>
      <div style="text-align:right">
        <Button type="primary"  @click="validateFromAndSubmit('productFrom')">添加</Button>
        <Button type="ghost" @click="handleReset('productFrom')">取消</Button>
      </div>

    </Card>

  </div>
</template>


<script>
  import pageableTable from '../common-components/table/pageable-table.vue';
  import formSearch from '../common-components/table/form-search.vue';
  import productTable from "./product-table";
  import * as productApi from "@/api/metadata/metadata-product";

  import Util from '@/libs/util.js';

  export default {
    components: {"form-search":formSearch,"pageable-table":pageableTable},
    mixins: [productTable,productApi],
    data: function(){
      return {
        productFromModel:{
          id:'',
          productName: '',
          productCode: '',
          productAdmin: '',
          productSecret:'',
          productDesc:''
        },
        ruleProductfrom:{
          name: [
            { required: true, message: '请输入项目名称', trigger: 'blur' },
            { type: 'string',max: 20, message: '最大长度20字符', trigger: 'blur'  }
          ],
          desc:[{type: 'string',max: 200, message: '最大长度200汉字', trigger: 'blur'}]
        },
        tableData:[]
      }
    },
    methods:{
      //验证重置
      handleReset (name) {
        this.$refs[name].resetFields();
      },

      //验证内容并提交
      validateFromAndSubmit(name){
        this.$refs[name].validate((valid) => {

          if(valid){
            productApi.save.r(this.productFromModel).then(res => {
              debugger
              let config = {
                type: 'success',
                msg: '添加成功'
              };
              if (res.code == 200) {
                this.loadProductData();
              } else if(res.code == 'BUS00000'){
                config.type = 'error';
                config.msg = "失败,项目代码不能重复.";
              }

              Util.showMessage(this, config);
            }).catch((err) => {
              console.log(err);
            });


          }
        });
      },

      // 加载数据
      loadProductData(){
        productApi.list.r().then(res => {
          debugger
          this.tableData = res.datas;
        }).catch((err) => {
          console.log(err);
        });
      },


    },

    created(){
      this.loadProductData();
    }


  };
</script>
