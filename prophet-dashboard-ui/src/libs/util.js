import Cookies from 'js-cookie';
import store from '@/store';

let util = {};

util.constant = {
  successCode: 200
};

util.getCookie = function (key) {
  return Cookies.get(key);
};

util.setCookie = function (key, value) {
  Cookies.set(key, value);
};

util.title = function (title) {
    title = title || 'Easr-UI';
    window.document.title = title;
};

util.inOf = function (arr, targetArr) {
    let res = true;
    arr.forEach(item => {
        if (targetArr.indexOf(item) < 0) {
            res = false;
        }
    });
    return res;
};

util.oneOf = function (ele, targetArr) {
    if (targetArr.indexOf(ele) >= 0) {
        return true;
    } else {
        return false;
    }
};

util.showThisRoute = function (itAccess, currentAccess) {
    if (typeof itAccess === 'object' && Array.isArray(itAccess)) {
        return util.oneOf(currentAccess, itAccess);
    } else {
        return itAccess === currentAccess;
    }
};

util.getRouterObjByName = function (routers, name) {
    if (!name || !routers || !routers.length) {
        return null;
    }
    let routerObj = null;
    for (let item of routers) {
        if (item.name === name) {
            return item;
        }
        routerObj = util.getRouterObjByName(item.children, name);
        if (routerObj) {
            return routerObj;
        }
    }
    return null;
};

util.handleTitle = function (vm, item) {
    if (typeof item.title === 'object') {
        return vm.$t(item.title.i18n);
    } else {
        return item.title;
    }
};

util.setCurrentPath = function (vm, name) {
    let title = '';
    let isOtherRouter = false;
    vm.$store.state.app.routers.forEach(item => {
        if (item.children.length === 1) {
            if (item.children[0].name === name) {
                title = util.handleTitle(vm, item);
                if (item.name === 'otherRouter') {
                    isOtherRouter = true;
                }
            }
        } else {
            item.children.forEach(child => {
                if (child.name === name) {
                    title = util.handleTitle(vm, child);
                    if (item.name === 'otherRouter') {
                        isOtherRouter = true;
                    }
                }
            });
        }
    });
    let currentPathArr = [];
    if (name === 'home_index') {
        currentPathArr = [
            {
                title: util.handleTitle(vm, util.getRouterObjByName(vm.$store.state.app.routers, 'home_index')),
                path: '',
                name: 'home_index'
            }
        ];
    } else if ((name.indexOf('_index') >= 0 || isOtherRouter) && name !== 'home_index') {
        currentPathArr = [
            {
                title: util.handleTitle(vm, util.getRouterObjByName(vm.$store.state.app.routers, 'home_index')),
                path: '/home',
                name: 'home_index'
            },
            {
                title: title,
                path: '',
                name: name
            }
        ];
    } else {
        let currentPathObj = vm.$store.state.app.routers.filter(item => {
            if (item.children.length <= 1) {
                return item.children[0].name === name;
            } else {
                let i = 0;
                let childArr = item.children;
                let len = childArr.length;
                while (i < len) {
                    if (childArr[i].name === name) {
                        return true;
                    }
                    i++;
                }
                return false;
            }
        })[0];
        if (currentPathObj.children.length <= 1 && currentPathObj.name === 'home') {
            currentPathArr = [
                {
                    title: {i18n: 'menu_home'},
                    path: '',
                    name: 'home_index'
                }
            ];
        } else if (currentPathObj.children.length <= 1 && currentPathObj.name !== 'home') {
            currentPathArr = [
                {
                    title: {i18n: 'menu_home'},
                    path: '/home',
                    name: 'home_index'
                },
                {
                    title: currentPathObj.title,
                    path: '',
                    name: name
                }
            ];
        } else {
            let childObj = currentPathObj.children.filter((child) => {
                return child.name === name;
            })[0];
            currentPathArr = [
                {
                    title: {i18n: 'menu_home'},
                    path: '/home',
                    name: 'home_index'
                },
                {
                    title: currentPathObj.title,
                    path: '',
                    name: currentPathObj.name
                },
                {
                    title: childObj.title,
                    path: currentPathObj.path + '/' + childObj.path,
                    name: name
                }
            ];
        }
    }
    vm.$store.commit('setCurrentPath', currentPathArr);

    return currentPathArr;
};

util.openNewPage = function (vm, name, argu, query) {
    let pageOpenedList = vm.$store.state.app.pageOpenedList;
    let openedPageLen = pageOpenedList.length;
    let i = 0;
    let tagHasOpened = false;
    while (i < openedPageLen) {
        if (name === pageOpenedList[i].name) { // 页面已经打开
            vm.$store.commit('pageOpenedList', {
                index: i,
                argu: argu,
                query: query
            });
            tagHasOpened = true;
            break;
        }
        i++;
    }
    if (!tagHasOpened) {
        let tag = vm.$store.state.app.tagsList.filter((item) => {
            if (item.children) {
                return name === item.children[0].name;
            } else {
                return name === item.name;
            }
        });
        tag = tag[0];
        if (tag) {
            tag = tag.children ? tag.children[0] : tag;
            if (argu) {
                tag.argu = argu;
            }
            if (query) {
                tag.query = query;
            }
            vm.$store.commit('increateTag', tag);
        }
    }
    vm.$store.commit('setCurrentPageName', name);
};

util.toDefaultPage = function (routers, name, route, next) {

    let len = routers.length;
    let i = 0;
    let notHandle = true;
    while (i < len) {
        if (routers[i].name === name && routers[i].children && routers[i].redirect === undefined) {
            route.replace({
                name: routers[i].children[0].name
            });
            notHandle = false;
            next();
            break;
        }
        i++;
    }
    if (notHandle) {
        next();
    }
};

//全屏事件
util.fullscreenEvent = function (vm) {
    vm.$store.commit('initCachepage');
};

//设置滚动条
util.smoothScroll = function (vm, el) {
 /* let nodeList = document.querySelectorAll(el);
  nodeList.forEach((currentValue) => {
    vm.$smoothScroll.init(currentValue, {alwaysShowTracks: false, continuousScrolling: false});
  })*/
};

//滑动滚动条
util.smoothScrollTo = function (vm, el, x, y) {
  /*let nodeList = document.querySelectorAll(el);
  nodeList.forEach((currentValue) => {
    vm.$smoothScroll.get(currentValue).scrollTo(x, y);
  })*/
};

//判断对象是否为空
util.isEmptyObject = function(e) {
  var t;
  for (t in e)
    return !1;
  return !0
};

//深拷贝
util.deepCopy = function (source) {
  if (!source) {
    return source;
  }
  let sourceCopy = source instanceof Array ? [] : {};
  for (let item in source) {
    sourceCopy[item] = typeof source[item] === 'object' ? util.deepCopy(source[item]) : source[item];
  }
  return sourceCopy;
};

//获取用户权限
util.getUserInfo = function(vm, asyncRouterMap) {
  return new Promise((resolve, reject) => {
    store.dispatch('GetUserInfo').then(res => {
      let accessedRouters = util.filterAsyncRouter(vm, asyncRouterMap);
      store.commit('setRouters', accessedRouters);
      resolve(res)
    }).catch((error) => {
      reject(error)
    })
  })
};

//获取用户权限
util.getResourcePermission = function() {
  return store.state.user.resourcePermission;
};

//获取是否加载用户权限
util.getLoadPermission = function() {
  return store.state.user.loadPermission;
};

//获取用户可以访问的路由
util.getAccessRouters = function() {
  return store.state.user.routers;
};

//更新用户菜单
util.updateMenuList = function(routers) {
  store.commit('updateMenulist', routers);
};

//递归过滤路由表，返回符合用户角色权限的路由表
util.filterAsyncRouter = function (vm, asyncRouterMap) {
  const accessedRouters = asyncRouterMap.filter(route => {
    if (util.hasPermission(vm, route)) {
      if (route.children && route.children.length) {
        route.children = util.filterAsyncRouter(vm, route.children)
      }
      return true;
    }
    return false;
  });
  return accessedRouters;
};

//通过meta.role判断是否与当前用户权限匹配
util.hasPermission = function(vm, route) {
  if (route.meta && route.meta.roles) {
    return vm.$has(route.meta.roles);
  } else {
    return true;
  }
};

//整理用户权限
util.getPermission = function(userInfo) {
  let resourcePermission = {};
  if (Array.isArray(userInfo.resources)) {
    userInfo.resources.forEach(function(e, i) {
      let key = e.perms;
      resourcePermission[key] = true;
    });
  }
  return resourcePermission;
};

//通过meta.role判断是否与当前用户权限匹配
util.showMessage = function(vm, config) {
  let title = config.title || vm.$t('title_message');
  let content = config.msg;
  switch (config.type) {
    case 'info':
      vm.$Modal.info({
        title: title,
        content: content
      });
      break;
    case 'success':
      vm.$Modal.success({
        title: title,
        content: content
      });
      break;
    case 'warning':
      vm.$Modal.warning({
        title: title,
        content: content
      });
      break;
    case 'error':
      vm.$Modal.error({
        title: title,
        content: content
      });
      break;
  }
};



export default util;
