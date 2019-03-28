<style lang="less" >
    @import "./main.less";
</style>
<template>
    <div class="main" :class="{'main-hide-text': shrink}">
      <div :class="['sidebar-menu-con', classMenuWidth]">
        <shrinkable-menu
          :shrink="shrink"
          @on-change="handleSubmenuChange"
          :theme="menuTheme"
          :before-push="beforePush"
          :open-names="openedSubmenuArr"
          :menu-list="menuList">
          <div slot="top" class="logo-con">
              <!--<div class="logo-con-left"><Icon type="paper-airplane" size="32"></Icon></div>-->
              <!--<div class="logo-con-right" v-show="!shrink" >Admin V.1</div>-->
              <img v-show="!shrink"  src="~images/logo.jpg" key="max-logo" />
              <img v-show="shrink" src="~images/logo-min.jpg" key="min-logo" />
          </div>
        </shrinkable-menu>
      </div>
      <div :class="['main-header-con', classMenuWidth]">
        <div class="main-header">
          <div class="navicon-con">
              <Button :class="{'button-vertical': this.navigationShrink}" type="text" @click="toggleClick">
                  <Icon type="navicon" size="32"></Icon>
              </Button>
          </div>
          <div class="header-middle-con">
              <div class="main-breadcrumb">
                  <breadcrumb-nav :currentPath="currentPath"></breadcrumb-nav>
              </div>
          </div>
          <div class="header-avator-con">
            <full-screen v-model="isFullScreen" @on-change="fullscreenChange"></full-screen>
            <lock-screen></lock-screen>
            <message-tip v-model="mesCount"></message-tip>

              <div class="user-dropdown-menu-con">
                  <Row type="flex" justify="end" align="middle" class="user-dropdown-innercon">
                      <Dropdown transfer trigger="click" @on-click="handleClickUserDropdown">
                          <a href="javascript:void(0)">
                              <span class="main-user-name">{{ userName }}</span>
                              <Icon type="arrow-down-b"></Icon>
                          </a>
                          <DropdownMenu slot="list">
                              <DropdownItem name="ownSpace">{{$t("label_center")}}</DropdownItem>
                              <DropdownItem name="loginout">{{$t("label_logout")}}</DropdownItem>
                          </DropdownMenu>
                      </Dropdown>
                      <Avatar :src="avatorPath" icon="person" style="background: #619fe7;margin-left: 10px;"></Avatar>
                  </Row>
              </div>
            </div>
        </div>
        <div class="tags-con">
          <tags-page-opened :pageTagsList="pageTagsList"></tags-page-opened>
        </div>
        </div>
        <div :class="['single-page-con', classMenuWidth]">
          <div class="single-page">
            <keep-alive :include="cachePage">
                <router-view></router-view>
            </keep-alive>
          </div>
        </div>
    </div>
</template>
<script>
    import shrinkableMenu from './main-components/shrinkable-menu/shrinkable-menu.vue';
    import tagsPageOpened from './main-components/tags-page-opened.vue';
    import breadcrumbNav from './main-components/breadcrumb-nav.vue';
    import fullScreen from './main-components/fullscreen.vue';
    import lockScreen from './main-components/lockscreen/lockscreen.vue';
    import messageTip from './main-components/message-tip.vue';
    import Cookies from 'js-cookie';
    import util from '@/libs/util.js';

    export default {
        components: {
            shrinkableMenu,
            tagsPageOpened,
            breadcrumbNav,
            fullScreen,
            lockScreen,
            messageTip
        },
        data () {
            return {
              // userName: '',
              shrink: false,
              navigationShrink: false,
              isFullScreen: false,
              openedSubmenuArr: this.$store.state.app.openedSubmenuArr
            };
        },
        computed: {
            userName: {
              get: function () {
                return this.$store.state.user.userName;
              },
              set: function () {
              }
            },
            menuList () {
                return this.$store.state.app.menuList;
            },
            pageTagsList () {
                return this.$store.state.app.pageOpenedList; // 打开的页面的页面对象
            },
            currentPath () {
                return this.$store.state.app.currentPath; // 当前面包屑数组
            },
            avatorPath () {
                return localStorage.avatorImgPath;
            },
            cachePage () {
                return this.$store.state.app.cachePage;
            },
            lang () {
                return this.$store.state.app.lang;
            },
            menuTheme () {
                return this.$store.state.app.menuTheme;
            },
            mesCount () {
                return this.$store.state.app.messageCount;
            },
            classMenuWidth () {
              if (this.navigationShrink) {
                if (this.$store.state.user.currentWindowWidth <= 768) {
                  this.shrink = true;
                  return 'hide-min-width';
                } else {
                  this.shrink = true;
                  return 'min-width';
                }
              } else {
                if (this.$store.state.user.currentWindowWidth <= 768) {
                  this.shrink = true;
                  return 'min-width';
                } else {
                  this.shrink = false;
                  return 'max-width';
                }
              }
            }
        },
        methods: {
            init () {
                //加载滚动条
                util.smoothScroll(this, '.ivu-shrinkable-menu, .single-page-con, .ivu-dropdown-transfer');
                let pathArr = util.setCurrentPath(this, this.$route.name);
                if (pathArr.length >= 2) {
                    this.$store.commit('addOpenSubmenu', pathArr[1].name);
                }
                this.userName = Cookies.get('user');
                let messageCount = 3;
                this.messageCount = messageCount.toString();
                this.checkTag(this.$route.name);
                this.$store.commit('setMessageCount', 3);
            },
            toggleClick () {
                this.navigationShrink = !this.navigationShrink;
            },
            handleClickUserDropdown (name) {
                if (name === 'ownSpace') {
                    util.openNewPage(this, 'ownspace_index');
                    this.$router.push({
                        name: 'ownspace_index'
                    });
                } else if (name === 'loginout') {
                    // 退出登录
                    this.$store.commit('logout', this);
                    this.$store.commit('clearOpenedSubmenu');
                    // SSO注销
                    window.location.href = "/demo/logout/cas?service=" + "http://localhost:8081";
                    // this.$router.push({
                    //     name: 'login'
                    // });
                }
            },
            checkTag (name) {
                let openpageHasTag = this.pageTagsList.some(item => {
                    if (item.name === name) {
                        return true;
                    }
                });
                if (!openpageHasTag) { //  解决关闭当前标签后再点击回退按钮会退到当前页时没有标签的问题
                    util.openNewPage(this, name, this.$route.params || {}, this.$route.query || {});
                }
            },
            handleSubmenuChange (val) {
                // console.log(val)
            },
            beforePush (name) {
                // if (name === 'accesstest_index') {
                //     return false;
                // } else {
                //     return true;
                // }
                return true;
            },
            fullscreenChange (isFullScreen) {
                // console.log(isFullScreen);
            }
        },
        watch: {
            '$route' (to) {
                this.$store.commit('setCurrentPageName', to.name);
                let pathArr = util.setCurrentPath(this, to.name);
                if (pathArr.length > 2) {
                    this.$store.commit('addOpenSubmenu', pathArr[1].name);
                }
                this.checkTag(to.name);
                localStorage.currentPageName = to.name;
            },
            lang () {
                util.setCurrentPath(this, this.$route.name); // 在切换语言时用于刷新面包屑
            }
        },
        mounted () {
            this.init();
        },
        created () {
            // 显示打开的页面的列表
            this.$store.commit('setOpenedList');
        }
    };
</script>
