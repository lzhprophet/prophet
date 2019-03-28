<style lang="less">
  @import './login.less';
</style>
<template>
  <div class="login" @keydown.enter="handleSubmit">
    <div class="login-con">
      <Card :bordered="false">
        <p slot="title">
          <Icon type="log-in"></Icon>
          {{$t("label_login")}}
        </p>
        <div class="form-con">
          <Form ref="loginForm" :model="form" :rules="rules">
            <FormItem prop="userName">
              <Input v-model="form.userName" :placeholder="$t('placeholder_user_name')">
                <span slot="prepend">
                    <Icon :size="16" type="person"></Icon>
                </span>
              </Input>
            </FormItem>
            <FormItem prop="password">
              <Input type="password" v-model="form.password" :placeholder="$t('placeholder_password')">
                <span slot="prepend">
                    <Icon :size="14" type="locked"></Icon>
                </span>
              </Input>
            </FormItem>
            <FormItem>
              <Button @click="handleSubmit" :loading="loading" type="primary" long>
                <span v-if="!loading">{{$t("btn_login")}}</span>
                <span v-else>{{$t("btn_login_ing")}}</span>
              </Button>
            </FormItem>
          </Form>
          <p class="login-tip">{{$t("tips_login")}}</p>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
  import Util from '@/libs/util';
  //import * as UserApi from "@/api/system/login-sso";
  import * as UserApi from "@/api/system/login-basic";

  let mixin = {
    data: function() {
      return { UserApi }
    }
  };

  export default {
    mixins: [mixin],
    data () {
      return {
        loading: false,
        form: {
          userName: '',
          password: ''
        },
        rules: {
          userName: [
            { required: true, message: this.$t("error_0001"), trigger: 'blur' }
          ],
          password: [
            { required: true, message: this.$t("error_0002"), trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      handleSubmit () {
        this.$refs.loginForm.validate((valid) => {
          if (valid) {
            this.loading = true;
            let params = {'username':this.form.userName, 'password': this.form.password};
            debugger
            UserApi.login.r(params).then(data => {
              debugger
              if (data.code == Util.constant.successCode) {//登录成功
                //SSO重定向
                UserApi.identification.r();
              } else {
                this.loading = false;
                let config = {
                  type: 'error',
                  msg: '登录不成功'
                };
                Util.showMessage(this, config);
            }}).catch((err) => {
              this.loading = false;
              console.log(err);
            })
          }
        })
      }
    }
  };
</script>

<style>

</style>
