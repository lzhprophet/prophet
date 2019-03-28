import Axios from 'axios';
import Vue from 'vue';

const instance = Axios.create({
  // baseURL: 'http://localhost:8081',
  timeout: 5000,
  //SSO跨域请求
  withCredentials: true
});

instance.defaults.headers.post['Content-Type'] = 'application/json';
// request interceptor
instance.interceptors.request.use(config => {
  // Do something before request is sent
  return config
}, error => {
  // Do something with request error
  return Promise.reject(error);
});

//返回状态判断(添加响应拦截器)
instance.interceptors.response.use(res =>{
  return res.data;
}, error => {
  if (error.response) {
    switch (error.response.status) {
      case 302:
        Vue.prototype.$Message.error({content: error.response.data.error || '请求参数异常', duration: 0, closable: true});
        break;
      case 400:
        Vue.prototype.$Message.error({content: error.response.data.error || '请求参数异常', duration: 0, closable: true});
        break;
      case 401:
        Vue.prototype.$Message.warning({content: error.response.data.error || '密码错误或账号不存在', duration: 0, closable: true});
        break;
      case 403:
        Vue.prototype.$Message.warning({content: error.response.data.error || '无访问权限，请联系企业管理员', duration: 0, closable: true});
        break;
      case 404:
        Vue.prototype.$Message.warning({content: error.response.data.error || '请求的地址不存在', duration: 0, closable: true});
        break;
      default:
        Vue.prototype.$Message.error({content: error.response.data.error || '服务端异常，请联系技术支持', duration: 0, closable: true});
    }
  }
  return Promise.reject(error);
});

export default instance;
