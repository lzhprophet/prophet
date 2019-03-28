// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import store from './store';
import App from './App';
import {router} from './router/index';
import {appRouter} from './router/router';
import iView from 'iview';
import echarts from 'echarts' //引入echarts

import '@/locale';
// import 'iview/dist/styles/iview.css';
import 'vue2-animate/dist/vue2-animate.min.css'
import './styles/index.less';
import Scrollbar from 'smooth-scrollbar';

Vue.prototype.$smoothScroll = Scrollbar;
Vue.prototype.$echarts = echarts //引入组件

Vue.use(iView);

//权限检查方法
Vue.prototype.$has = function(rArray) {
  let resources = [];
  let permission = true;
  if (Array.isArray(rArray)) {
    rArray.forEach(function(e) {
      if (e.p) {
        resources = resources.concat(e.p);
      } else {
        resources = resources.concat(e);
      }
    });
  } else {
    resources = resources.concat(rArray.p);
  }
  resources.forEach(function(p) {
    if (!store.state.user.resourcePermission[p]) {
      return permission = false;
    }
  });
  return permission;
};

//date格式化
Date.prototype.Format = function (fmt) {
  var o = {
    "M+": this.getMonth() + 1, //月份
    "d+": this.getDate(), //日
    "h+": this.getHours(), //小时
    "m+": this.getMinutes(), //分
    "s+": this.getSeconds(), //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    "S": this.getMilliseconds() //毫秒
  };
  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
};

if(!Array.prototype.last){
  Array.prototype.last = function(){
    return this[this.length - 1];
  };
};

//权限指令
Vue.directive('has', {
  bind: function(el, binding) {
    if (!Vue.prototype.$has(binding.value)) {
      el.parentNode.removeChild(el);
    }
  }
});

new Vue({
  el: '#app',
  router: router,
  store: store,
  template: '<App/>',
  components: { App },
  data: {
    currentPageName: ''
  },
  created () {
    let tagsList = [];
    appRouter.map((item) => {
      if (item.children.length <= 1) {
        tagsList.push(item.children[0]);
      } else {
        tagsList.push(...item.children);
      }
    });
    this.$store.commit('setTagsList', tagsList);
  },
  mounted () {
    this.currentPageName = this.$route.name;
    // 显示打开的页面的列表
    this.$store.commit('setOpenedList');
    this.$store.commit('initCachepage');
    // 导航栏自适应
    this.$store.commit('setCurrentWindowWidth', window.screen.width);
    // 监听页面的大小
    window.addEventListener('resize', ()=> {
      this.$store.commit('setCurrentWindowWidth', window.screen.width);
    });
  }
});
