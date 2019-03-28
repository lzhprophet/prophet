import Main from '@/views/main.vue';

// 不作为Main组件的子页面展示的页面单独写，如下
export const loginRouter = {
  path: '/login',
  name: 'login',
  meta: {
    title: "登录"
  },
  component: () => import('@/views/login.vue')
};

export const page403 = {
  path: '/403',
  meta: {
    title: "403-权限不足"
  },
  name: 'error-403',
  component: () => import('@//views/error-page/403.vue')
};

export const page404 = {
  path: '/*',
  name: 'error-404',
  meta: {
    title: "404-页面不存在"
  },
  component: () => import('@/views/error-page/404.vue')
};

export const page500 = {
  path: '/500',
  meta: {
    title: "500-服务端错误"
  },
  name: 'error-500',
  component: () => import('@/views/error-page/500.vue')
};

export const locking = {
  path: '/locking',
  name: 'locking',
  component: () => import('@/views/main-components/lockscreen/components/locking-page.vue')
};

// 作为Main组件的子页面展示但是不在左侧菜单显示的路由写在otherRouter里
export const otherRouter = {
  path: '/',
  name: 'otherRouter',
  redirect: '/home',
  component: Main,
  children: [
    { path: 'home', title: {i18n: 'menu_home'}, name: 'home_index', component: () => import('@/views/home/home.vue') },
    { path: 'ownspace', title: {i18n: 'menu_center'}, name: 'ownspace_index', component: () => import('@/views/own-space/own-space.vue') },
    { path: 'message', title: {i18n: 'menu_message'}, name: 'message_index', component: () => import('@/views/message/message.vue') }
  ]
};

// 作为Main组件的子页面展示并且在左侧菜单显示的路由写在appRouter里
export const appRouter = [
  {
    path: '/base-info',
    icon: 'ios-grid-view',
    name: 'tables',
    title: 'Dashboard',
    component: Main,
    children: [
      { path: 'base-info', title: '关键指标', name: 'base-info', icon: 'search', component: () => import('@/views/base-statis/base-info.vue') }
    ]
  },
  {
    path: '/behavior-analysis',
    icon: 'shuffle',
    title: '用户行为分析',
    name: 'behavior-analysis',
    component: Main,
    children: [
      { path: 'product',  title: '事件分析', name: 'bbb', icon: 'arrow-right-b', component: () => import('@/views/meta-data/product.vue') },
      { path: 'berth2',  title: '漏斗分析', meta: { roles: ['menu:berth']}, name: 'berth1', icon: 'arrow-right-b', component: () => import('@/views/meta-data/product.vue') }
    ]
  },
  {
    path: '/user-analysis',
    icon: 'star',
    title: '用户分析',
    name: 'user-analysis',
    component: Main,
    children: [
      { path: 'product',  title: '用户分群', name: 'aaa', icon: 'arrow-right-b', component: () => import('@/views/meta-data/product.vue') },
      { path: 'berth2',  title: '属性分析', meta: { roles: ['menu:berth']}, name: 'berth1', icon: 'arrow-right-b', component: () => import('@/views/meta-data/product.vue') }
    ]
  },
  {
    path: '/metadata',
    icon: 'compose',
    title: '元数据管理',
    name: 'metadata',
    component: Main,
    children: [
      { path: 'product',  title: '接入项目管理', name: 'product', icon: 'arrow-right-b', component: () => import('@/views/meta-data/product.vue') },
      { path: 'api-define',  title: 'API接口标注',   name: 'api-define', icon: 'checkmark-circled', component: () => import('@/views/meta-data/api-define.vue') },
      { path: 'berth2',  title: 'Event事件管理', meta: { roles: ['menu:berth']}, name: 'berth1', icon: 'arrow-right-b', component: () => import('@/views/meta-data/product.vue') },
      { path: 'test-demo',  title: '任务调度管理',   name: 'test-demo', icon: 'ios-grid-view', component: () => import('@/views/tables/demo-table.vue') },
      { path: 'table-demo',  title: '用户管理',   name: 'table-demo', icon: 'ios-grid-view', component: () => import('@/views/tables/demo-table.vue') }
    ]
  }
];

// 所有上面定义的路由都要写在下面的routers里
export const routers = [
  loginRouter,
  otherRouter,
  locking,
  ...appRouter,
  page500,
  page403,
  page404
];
