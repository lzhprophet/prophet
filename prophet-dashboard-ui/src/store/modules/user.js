//import * as UserApi from "@/api/system/login-sso";
import * as UserApi from "@/api/system/login-basic";
import Util from "@/libs/util";

let mixin = {
  data: function() {
    return { UserApi }
  }
};

const user = {
    mixins: [mixin],
    state: {
      currentWindowWidth: '',
      loadPermission: true,
      userName: '',
      resourcePermission: {},
      routers: []
    },
    mutations: {
      logout (state, vm) {
        // 恢复默认样式
        let themeLink = document.querySelector('link[name="theme"]');
        themeLink.setAttribute('href', '');
        // 清空打开的页面等数据，但是保存主题数据
        let theme = '';
        if (localStorage.theme) {
          theme = localStorage.theme;
        }
        localStorage.clear();
        if (theme) {
          localStorage.theme = theme;
        }
      },
      setUserInfo (state, data) {
        state.loadPermission = false;
        state.userName = data.userName;
        if (Array.isArray(data.permissions)) {
          data.permissions.forEach(function(e, i) {
            state.resourcePermission[e] = true;
          });
        }
      },
      setCurrentWindowWidth (state, width) {
        state.currentWindowWidth = width;
      },
      setRouters (state, routers)  {
        state.routers = routers
      }
    },
    actions: {
      // 用户名登录
      GetUserInfo({commit}) {
        return new Promise((resolve, reject) => {
          UserApi.requestUser.r().then(res => {
            if (res.code == Util.constant.successCode) {
              commit('setUserInfo', res.data);
            }
            resolve(res);
          }).catch(error => {
            reject(error);
          })
        })
      }
    }
};

export default user;
