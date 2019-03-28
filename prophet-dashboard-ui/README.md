# admin-opos

> A Vue.js project for opos

## Build Setup

``` bash
# install dependencies
cnpm install

# serve with hot reload at localhost:8081
cnpm run dev

# build for production with minification
cnpm run build

# build for production and view the bundle analyzer report
cnpm run build --report
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).

## 文件结构
```shell
.
├── build  项目构建
├── config  项目构建配置
└── src
    ├── api  调用后端服务
    ├── images  图片文件
    ├── libs  工具方法
    ├── locale  多语言文件（本项目用统一做文本管理）
    ├── router  路由配置
    ├── store  状态管理
    ├── styles  通用样式文件
      ├── views
          ├── access  权限管理
          ├── common-components  通用组件
          ├── error_page  错误页面
          ├── home  首页
          │   ├── components  首页组件
          ├── main_components  Main组件
          │   ├── fullscreen  全屏
          │   └── shrinkable-menu  可收缩菜单
          ├── tables  Demo表格
          ├── main.vue  主页面
          └── login.vue  登录页面
```
## 编码规约
- 使用es6进行编码
- 系统用到的所有文本统一配置在locale/locale.js中，不允许写死在代码里
- 每个vue文件对应的样式就近维护 比如：login.vue和login.less，禁止样式污染
- 通用的组件放入common-components中，模块自用的组件放入各模块下
- 其他参照vue风格指南：https://cn.vuejs.org/v2/style-guide/