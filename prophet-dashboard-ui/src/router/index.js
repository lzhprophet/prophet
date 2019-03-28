import Vue from 'vue';
import iView from 'iview';
import Util from '@/libs/util';
import VueRouter from 'vue-router';
import {routers, otherRouter, appRouter} from './router';

Vue.use(VueRouter);

// 路由配置
const RouterConfig = {
    // mode: 'history',
    routes: routers
};

export const router = new VueRouter(RouterConfig);

router.beforeEach((to, from, next) => {
    iView.LoadingBar.start();
    Util.title(to.meta.title);
    if (Util.getCookie('locking') === '1' && to.name !== 'locking') { // 判断当前是否是锁定状态
      next({
        replace: true,
        name: 'locking'
      });
    } else if (Util.getCookie('locking') === '0' && to.name === 'locking') {
      next(false);
    } else {
      if (to.name === 'login') { // 判断是否前往的是登录页
        next();
      } else {
        // 判断当前用户是否已拉取完权限信息
        if (Util.getLoadPermission() && Util.isEmptyObject(Util.getResourcePermission())) {
          let actualRouter = Util.deepCopy(appRouter);
          Util.getUserInfo(router.app, actualRouter).then(() => {
            Util.updateMenuList(Util.getAccessRouters());
            next({ ...to, replace: true });
          }).catch(() => {
            next({
              name: 'login'
            });
          })
        } else {
          //获取当前路由
          const curRouterObj = Util.getRouterObjByName([otherRouter, ...appRouter], to.name);
          //如果需要权限认证
          if (curRouterObj){
            if (Util.hasPermission(router.app, curRouterObj)) {
              // 如果在地址栏输入的是一级菜单则默认打开其第一个二级菜单的页面
              Util.toDefaultPage([otherRouter, ...appRouter], to.name, router, next);
            } else {
              next({
                replace: true,
                name: 'error-403'
              });
            }
          } else { // 没有配置权限的路由, 直接通过
            Util.toDefaultPage([...routers], to.name, router, next);
          }
        }
      }
    }
});

router.afterEach((to) => {
    Util.openNewPage(router.app, to.name, to.params, to.query);
    iView.LoadingBar.finish();
    window.scrollTo(0, 0);
});
