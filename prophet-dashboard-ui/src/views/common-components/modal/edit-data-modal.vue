<template>
  <div>
    <Modal v-model="editDataModalFlag" class-name="vertical-center-modal" width="800"
           @on-cancel="doCancel"
    >
      <p slot="header">
        <Icon type="information-circled"></Icon>
        <span>{{this.modalTitle}}</span>
      </p>
      <div class="center">
        <slot name="center"></slot>
      </div>
      <div slot="footer">
        <Button type="ghost" @click="doCancel">取消</Button>
        <Button type="primary" :loading="modalLoading" @click="doSubmit">确定</Button>
      </div>
    </Modal>
  </div>
</template>
<style lang="less">
</style>
<script>
  export default {
    props: {
      //父组件传入表单项目
      editDataModalFlag: {
        type: Boolean,
        default: false
      },
      modalLoading: {
        type: Boolean,
        default: false
      },
      modalTitle: {
        type: String,
        default: ''
      },
    },
    methods:{
      //确定
      doSubmit(){
        this.$emit('on-submit');
      },
      //取消
      doCancel(){
        this.$emit('on-cancel');
      }
    }
  }
</script>
