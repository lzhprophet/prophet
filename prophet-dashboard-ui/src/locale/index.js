import Vue from 'vue';
import VueI18n from 'vue-i18n';
import Locales from './locale';

Vue.use(VueI18n);

// 自动设置语言
const navLang = navigator.language;
const localLang = (navLang === 'zh-CN' || navLang === 'en-US') ? navLang : false;
const lang = window.localStorage.lang || localLang || 'zh-CN';

Vue.config.lang = lang;

// 多语言配置
const locales = Locales;
Object.keys(locales).forEach(function (lang) {
  Vue.locale(lang, locales[lang])
});

// const mergeZH = Object.assign(zhLocale, locales['zh-CN']);
// const mergeEN = Object.assign(enLocale, locales['en-US']);
// Vue.locale('zh-CN', mergeZH);
// Vue.locale('en-US', mergeEN);
