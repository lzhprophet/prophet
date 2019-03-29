/**
 * @fileoverview easr javascript sdk
 * @author lvzhh
 * @version 1.0
 */

;(function(root,factory) {

  if (typeof exports === 'object' && typeof module === 'object') {
    module.exports = factory();
  } else{
    factory();
  }

})(this,function(){

try{
  var sd = window['easr201901'],has_declare;
  if(sd){
    sd = window[sd];
    has_declare = true;
  }else{
    sd = {};
    has_declare = false;
  }
  // 防止重复引入
  if ((typeof sd !== 'function' && typeof sd !== 'object') || sd.has_load_sdk) {
    return false;
  }
  sd._t = sd._t || 1 * new Date();

  sd.has_load_sdk = true;

// 压缩后的json库
if(typeof JSON!=='object'){JSON={}}(function(){'use strict';var rx_one=/^[\],:{}\s]*$/,rx_two=/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g,rx_three=/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,rx_four=/(?:^|:|,)(?:\s*\[)+/g,rx_escapable=/[\\\"\u0000-\u001f\u007f-\u009f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,rx_dangerous=/[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;function f(n){return n<10?'0'+n:n}function this_value(){return this.valueOf()}if(typeof Date.prototype.toJSON!=='function'){Date.prototype.toJSON=function(){return isFinite(this.valueOf())?this.getUTCFullYear()+'-'+f(this.getUTCMonth()+1)+'-'+f(this.getUTCDate())+'T'+f(this.getUTCHours())+':'+f(this.getUTCMinutes())+':'+f(this.getUTCSeconds())+'Z':null};Boolean.prototype.toJSON=this_value;Number.prototype.toJSON=this_value;String.prototype.toJSON=this_value}var gap,indent,meta,rep;function quote(string){rx_escapable.lastIndex=0;return rx_escapable.test(string)?'"'+string.replace(rx_escapable,function(a){var c=meta[a];return typeof c==='string'?c:'\\u'+('0000'+a.charCodeAt(0).toString(16)).slice(-4)})+'"':'"'+string+'"'}function str(key,holder){var i,k,v,length,mind=gap,partial,value=holder[key];if(value&&typeof value==='object'&&typeof value.toJSON==='function'){value=value.toJSON(key)}if(typeof rep==='function'){value=rep.call(holder,key,value)}switch(typeof value){case'string':return quote(value);case'number':return isFinite(value)?String(value):'null';case'boolean':case'null':return String(value);case'object':if(!value){return'null'}gap+=indent;partial=[];if(Object.prototype.toString.apply(value)==='[object Array]'){length=value.length;for(i=0;i<length;i+=1){partial[i]=str(i,value)||'null'}v=partial.length===0?'[]':gap?'[\n'+gap+partial.join(',\n'+gap)+'\n'+mind+']':'['+partial.join(',')+']';gap=mind;return v}if(rep&&typeof rep==='object'){length=rep.length;for(i=0;i<length;i+=1){if(typeof rep[i]==='string'){k=rep[i];v=str(k,value);if(v){partial.push(quote(k)+(gap?': ':':')+v)}}}}else{for(k in value){if(Object.prototype.hasOwnProperty.call(value,k)){v=str(k,value);if(v){partial.push(quote(k)+(gap?': ':':')+v)}}}}v=partial.length===0?'{}':gap?'{\n'+gap+partial.join(',\n'+gap)+'\n'+mind+'}':'{'+partial.join(',')+'}';gap=mind;return v}}if(typeof JSON.stringify!=='function'){meta={'\b':'\\b','\t':'\\t','\n':'\\n','\f':'\\f','\r':'\\r','"':'\\"','\\':'\\\\'};JSON.stringify=function(value,replacer,space){var i;gap='';indent='';if(typeof space==='number'){for(i=0;i<space;i+=1){indent+=' '}}else if(typeof space==='string'){indent=space}rep=replacer;if(replacer&&typeof replacer!=='function'&&(typeof replacer!=='object'||typeof replacer.length!=='number')){throw new Error('JSON.stringify')}return str('',{'':value})}}if(typeof JSON.parse!=='function'){JSON.parse=function(text,reviver){var j;function walk(holder,key){var k,v,value=holder[key];if(value&&typeof value==='object'){for(k in value){if(Object.prototype.hasOwnProperty.call(value,k)){v=walk(value,k);if(v!==undefined){value[k]=v}else{delete value[k]}}}}return reviver.call(holder,key,value)}text=String(text);rx_dangerous.lastIndex=0;if(rx_dangerous.test(text)){text=text.replace(rx_dangerous,function(a){return'\\u'+('0000'+a.charCodeAt(0).toString(16)).slice(-4)})}if(rx_one.test(text.replace(rx_two,'@').replace(rx_three,']').replace(rx_four,''))){j=eval('('+text+')');return typeof reviver==='function'?walk({'':j},''):j}throw new SyntaxError('JSON.parse')}}}());




  var _ = sd._ = {};



  sd.para_default = {
    //scrollmap:{delay:6000}

    name: 'prophet',
    teancy_code:'company-1',
    project_code:'demo',
    project_model_code:'web',
    project_model_version:'V1.0',
    // referrer字符串截取
    max_referrer_string_length: 200,
    //通用字符串截取，超过7000的字符串会导致url超长发不出去，所以限制长度
    max_string_length: 500,
    //    send_error_event: true,
    cross_subdomain: true,
    show_log: true,
    is_debug: false,
    debug_mode: false,
    debug_mode_upload: false,
    // todo 前端session时长
    session_time: 0,

    use_client_time: false,
    //来源参数名字
    source_channel: [],

    server_url:'http://172.16.101.75/prophet/data?token=4d2687189c6b3f7e',

    auto_init: true,

    is_single_page: false,

    is_trackLink:true,
    // 如果要设置，设置数组
    source_type_config:{
      utm: null,
      search: null,
      social: null
    },
    callback_timeout: 1000,
    is_track_device_id: false,
    use_app_track: false
  };

sd.initPara = function(para){
    // 默认配置
  sd.para = para || sd.para || {};

  var i;
  // 合并配置
  for (i in sd.para_default) {
    if (sd.para[i] === void 0) {
      sd.para[i] = sd.para_default[i];
    }
  }

  // 优化配置
  if(typeof sd.para.server_url === 'object' && sd.para.server_url.length){
    for(i = 0; i < sd.para.server_url.length; i++){ 
      if (!/sa\.gif[^\/]*$/.test(sd.para.server_url[i])) {
        sd.para.server_url[i] = sd.para.server_url[i].replace(/\/sa$/, '/sa.gif').replace(/(\/sa)(\?[^\/]+)$/, '/sa.gif$2');
      }
    }
  }else if (!/sa\.gif[^\/]*$/.test(sd.para.server_url)) {
    sd.para.server_url = sd.para.server_url.replace(/\/sa$/, '/sa.gif').replace(/(\/sa)(\?[^\/]+)$/, '/sa.gif$2');
  }
  if(typeof sd.para.server_url === 'string'){
    sd.para.debug_mode_url = sd.para.debug_mode_url || sd.para.server_url.replace('sa.gif', 'debug');
  } 
  // 是否需要非cache，等于每次请求文件
  if (sd.para.noCache === true) {
    sd.para.noCache = '?' + (new Date()).getTime();
  } else {
    sd.para.noCache = '';
  }

};

var ArrayProto = Array.prototype;
var FuncProto = Function.prototype;
var ObjProto = Object.prototype;
var slice = ArrayProto.slice;
var toString = ObjProto.toString;
var hasOwnProperty = ObjProto.hasOwnProperty;
var LIB_VERSION = '1.0.0';

sd.lib_version = LIB_VERSION;

// 提供错误日志
var error_msg = [];
var is_first_visitor = false;

var just_test_distinctid = 0;
var just_test_distinctid_2 = 0;
var just_test_distinctid_detail = 0;
var just_test_distinctid_detail2 = 0;


// 标准广告系列来源
var source_channel_standard = 'utm_source utm_medium utm_campaign utm_content utm_term';


var logger = typeof logger === 'object' ? logger : {};
logger.info = function() {
  if (!sd.para.show_log) {
    return false;
  }
  if(sd.para.show_log === true || sd.para.show_log === 'string'){
    arguments[0] = _.formatJsonString(arguments[0]);
  }

  if (typeof console === 'object' && console.log) {
    try {
      return console.log.apply(console, arguments);
    } catch (e) {
      console.log(arguments[0]);
    }
  }
};

(function() {
  var nativeBind = FuncProto.bind,
    nativeForEach = ArrayProto.forEach,
    nativeIndexOf = ArrayProto.indexOf,
    nativeIsArray = Array.isArray,
    breaker = {};

  var each = _.each = function(obj, iterator, context) {
    if (obj == null) {
      return false;
    }
    if (nativeForEach && obj.forEach === nativeForEach) {
      obj.forEach(iterator, context);
    } else if (obj.length === +obj.length) {
      for (var i = 0, l = obj.length; i < l; i++) {
        if (i in obj && iterator.call(context, obj[i], i, obj) === breaker) {
          return false;
        }
      }
    } else {
      for (var key in obj) {
        if (hasOwnProperty.call(obj, key)) {
          if (iterator.call(context, obj[key], key, obj) === breaker) {
            return false;
          }
        }
      }
    }
  };

  _.logger = logger;
  // 普通的extend，不能到二级
  _.extend = function(obj) {
    each(slice.call(arguments, 1), function(source) {
      for (var prop in source) {
        if (source[prop] !== void 0) {
          obj[prop] = source[prop];
        }
      }
    });
    return obj;
  };
  // 允许二级的extend
  _.extend2Lev = function(obj) {
    each(slice.call(arguments, 1), function(source) {
      for (var prop in source) {
        if (source[prop] !== void 0) {
          if (_.isObject(source[prop]) && _.isObject(obj[prop])) {
            _.extend(obj[prop], source[prop]);
          } else {
            obj[prop] = source[prop];
          }
        }
      }
    });
    return obj;
  };
  // 如果已经有的属性不覆盖,如果没有的属性加进来
  _.coverExtend = function(obj) {
    each(slice.call(arguments, 1), function(source) {
      for (var prop in source) {
        if (source[prop] !== void 0 && obj[prop] === void 0) {
          obj[prop] = source[prop];
        }
      }
    });
    return obj;
  };

  _.isArray = nativeIsArray || function(obj) {
      return toString.call(obj) === '[object Array]';
    };

  _.isFunction = function(f) {
    if(!f){
      return false;
    }
    try {
      return /^\s*\bfunction\b/.test(f);
    } catch (x) {
      return false;
    }
  };

  _.isArguments = function(obj) {
    return !!(obj && hasOwnProperty.call(obj, 'callee'));
  };

  _.toArray = function(iterable) {
    if (!iterable) {
      return [];
    }
    if (iterable.toArray) {
      return iterable.toArray();
    }
    if (_.isArray(iterable)) {
      return slice.call(iterable);
    }
    if (_.isArguments(iterable)) {
      return slice.call(iterable);
    }
    return _.values(iterable);
  };

  _.values = function(obj) {
    var results = [];
    if (obj == null) {
      return results;
    }
    each(obj, function(value) {
      results[results.length] = value;
    });
    return results;
  };

  _.include = function(obj, target) {
    var found = false;
    if (obj == null) {
      return found;
    }
    if (nativeIndexOf && obj.indexOf === nativeIndexOf) {
      return obj.indexOf(target) != -1;
    }
    each(obj, function(value) {
      if (found || (found = (value === target))) {
        return breaker;
      }
    });
    return found;
  };

})();

_.inherit = function(subclass, superclass) {
  subclass.prototype = new superclass();
  subclass.prototype.constructor = subclass;
  subclass.superclass = superclass.prototype;
  return subclass;
};

_.trim = function(str){
  return str.replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, '');
};

_.isObject = function(obj) {
  return (toString.call(obj) == '[object Object]') && (obj != null);
};

_.isEmptyObject = function(obj) {
  if (_.isObject(obj)) {
    for (var key in obj) {
      if (hasOwnProperty.call(obj, key)) {
        return false;
      }
    }
    return true;
  }
  return false;
};

_.isUndefined = function(obj) {
  return obj === void 0;
};

_.isString = function(obj) {
  return toString.call(obj) == '[object String]';
};

_.isDate = function(obj) {
  return toString.call(obj) == '[object Date]';
};

_.isBoolean = function(obj) {
  return toString.call(obj) == '[object Boolean]';
};

_.isNumber = function(obj) {
  return (toString.call(obj) == '[object Number]' && /[\d\.]+/.test(String(obj)));
};

_.isElement = function(obj) {
    return !!(obj && obj.nodeType === 1);
};

_.isJSONString = function(str) {
  try {
    JSON.parse(str);
  } catch (e) {
    return false;
  }
  return true;
};
// gbk等编码decode会异常
_.decodeURIComponent = function(val){
  var result = '';
  try{
    result = decodeURIComponent(val);
  }catch(e){
    result = val;
  }
  return result;
};

_.encodeDates = function(obj) {
  _.each(obj, function(v, k) {
    if (_.isDate(v)) {
      obj[k] = _.formatDate(v);
    } else if (_.isObject(v)) {
      obj[k] = _.encodeDates(v); // recurse
    }
  });
  return obj;
};

_.now = Date.now || function() {
  return new Date().getTime();
};

_.throttle = function(func, wait, options) {
    var context, args, result;
    var timeout = null;
    var previous = 0;
    if (!options) options = {};
    var later = function() {
      previous = options.leading === false ? 0 : _.now();
      timeout = null;
      result = func.apply(context, args);
      if (!timeout) context = args = null;
    };
    return function() {
      var now = _.now();
      if (!previous && options.leading === false) previous = now;
      var remaining = wait - (now - previous);
      context = this;
      args = arguments;
      if (remaining <= 0 || remaining > wait) {
        if (timeout) {
          clearTimeout(timeout);
          timeout = null;
        }
        previous = now;
        result = func.apply(context, args);
        if (!timeout) context = args = null;
      } else if (!timeout && options.trailing !== false) {
        timeout = setTimeout(later, remaining);
      }
      return result;
    };
};

_.hashCode = function(str){
  if(typeof str !== 'string'){
    return 0;
  }
  var hash = 0;
  var char = null;
  if (str.length == 0) {
    return hash;
  }
  for (var i = 0; i < str.length; i++) {
    char = str.charCodeAt(i);
    hash = ((hash<<5)-hash)+char;
    hash = hash & hash;
  }
  return hash;
};

_.formatDate = function(d) {
  function pad(n) {
    return n < 10 ? '0' + n : n;
  }

  return d.getFullYear() + '-'
    + pad(d.getMonth() + 1) + '-'
    + pad(d.getDate()) + ' '
    + pad(d.getHours()) + ':'
    + pad(d.getMinutes()) + ':'
    + pad(d.getSeconds()) + '.'
    + pad(d.getMilliseconds());
};

// 把日期格式全部转化成日期字符串
_.searchObjDate = function(o) {
  if (_.isObject(o)) {
    _.each(o, function(a, b) {
      if (_.isObject(a)) {
        _.searchObjDate(o[b]);
      } else {
        if (_.isDate(a)) {
          o[b] = _.formatDate(a);
        }
      }
    });
  }
};

_.formatJsonString = function(obj){
  try{
    return JSON.stringify(obj, null, '  ');
  }catch(e){
    return JSON.stringify(obj);
  }
};

// 把字符串格式数据限制字符串长度
_.formatString = function(str) {
  if (str.length > sd.para.max_string_length) {
    logger.info('字符串长度超过限制，已经做截取--' + str);
    return str.slice(0, sd.para.max_string_length);
  } else {
    return str;
  }
};

// 把字符串格式数据限制字符串长度
_.searchObjString = function(o) {
  if (_.isObject(o)) {
    _.each(o, function(a, b) {
      if (_.isObject(a)) {
        _.searchObjString(o[b]);
      } else {
        if (_.isString(a)) {
          o[b] = _.formatString(a);
        }
      }
    });
  }
};


// 数组去重复
_.unique = function(ar) {
  var temp, n = [], o = {};
  for (var i = 0; i < ar.length; i++) {
    temp = ar[i];
    if (!(temp in o)) {
      o[temp] = true;
      n.push(temp);
    }
  }
  return n;
};

// 只能是sensors满足的数据格式
_.strip_sa_properties = function(p) {
  if (!_.isObject(p)) {
    return p;
  }
  _.each(p, function(v, k) {
    // 如果是数组，把值自动转换成string
    if (_.isArray(v)) {
      var temp = [];
      _.each(v, function(arrv) {
        if (_.isString(arrv)) {
          temp.push(arrv);
        } else {
          logger.info('您的数据-',k, v, '的数组里的值必须是字符串,已经将其删除');
        }
      });
      if (temp.length !== 0) {
        p[k] = temp;
      } else {
        delete p[k];
        logger.info('已经删除空的数组');
      }
    }
    // 只能是字符串，数字，日期,布尔，数组
    if (!(_.isString(v) || _.isNumber(v) || _.isDate(v) || _.isBoolean(v) || _.isArray(v))) {
      logger.info('您的数据-',k, v, '-格式不满足要求，我们已经将其删除');
      delete p[k];
    }
  });
  return p;
};

// 去掉undefined和null
_.strip_empty_properties = function(p) {
  var ret = {};
  _.each(p, function(v, k) {
    if (v != null) {
      ret[k] = v;
    }
  });
  return ret;
};

_.utf8Encode = function(string) {
  string = (string + '').replace(/\r\n/g, '\n').replace(/\r/g, '\n');

  var utftext = '', start, end;
  var stringl = 0, n;

  start = end = 0;
  stringl = string.length;

  for (n = 0; n < stringl; n++) {
    var c1 = string.charCodeAt(n);
    var enc = null;

    if (c1 < 128) {
      end++;
    } else if ((c1 > 127) && (c1 < 2048)) {
      enc = String.fromCharCode((c1 >> 6) | 192, (c1 & 63) | 128);
    } else {
      enc = String.fromCharCode((c1 >> 12) | 224, ((c1 >> 6) & 63) | 128, (c1 & 63) | 128);
    }
    if (enc !== null) {
      if (end > start) {
        utftext += string.substring(start, end);
      }
      utftext += enc;
      start = end = n + 1;
    }
  }

  if (end > start) {
    utftext += string.substring(start, string.length);
  }

  return utftext;
};

_.base64Encode = function(data) {
  var b64 = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
  var o1, o2, o3, h1, h2, h3, h4, bits, i = 0, ac = 0, enc = '', tmp_arr = [];
  if (!data) {
    return data;
  }
  data = _.utf8Encode(data);
  do {
    o1 = data.charCodeAt(i++);
    o2 = data.charCodeAt(i++);
    o3 = data.charCodeAt(i++);

    bits = o1 << 16 | o2 << 8 | o3;

    h1 = bits >> 18 & 0x3f;
    h2 = bits >> 12 & 0x3f;
    h3 = bits >> 6 & 0x3f;
    h4 = bits & 0x3f;
    tmp_arr[ac++] = b64.charAt(h1) + b64.charAt(h2) + b64.charAt(h3) + b64.charAt(h4);
  } while (i < data.length);

  enc = tmp_arr.join('');

  switch (data.length % 3) {
    case 1:
      enc = enc.slice(0, -2) + '==';
      break;
    case 2:
      enc = enc.slice(0, -1) + '=';
      break;
  }
  //todo 开发中  先不加密
  return enc;
};


_.UUID = (function() {
  var T = function() {
    var d = 1 * new Date(), i = 0;
    while (d == 1 * new Date()) {
      i++;
    }
    return d.toString(16) + i.toString(16);
  };
  var R = function() {
    return Math.random().toString(16).replace('.', '');
  };
  var UA = function(n) {
    var ua = navigator.userAgent, i, ch, buffer = [], ret = 0;

    function xor(result, byte_array) {
      var j, tmp = 0;
      for (j = 0; j < byte_array.length; j++) {
        tmp |= (buffer[j] << j * 8);
      }
      return result ^ tmp;
    }

    for (i = 0; i < ua.length; i++) {
      ch = ua.charCodeAt(i);
      buffer.unshift(ch & 0xFF);
      if (buffer.length >= 4) {
        ret = xor(ret, buffer);
        buffer = [];
      }
    }

    if (buffer.length > 0) {
      ret = xor(ret, buffer);
    }

    return ret.toString(16);
  };

  return function() {
    // 有些浏览器取个屏幕宽度都异常...
    var se = String(screen.height * screen.width);
    if (se && /\d{5,}/.test(se)) {
      se = se.toString(16);
    } else {
      se = String(Math.random() * 31242).replace('.', '').slice(0, 8);
    }
    var t= T();
    var r= R();
    var ua = UA();

    var val = (T() + '-' + R() + '-' + UA() + '-' + se + '-' + T());
    if(val){
      just_test_distinctid_2 = 1;
      return val; 
    }else{
      just_test_distinctid_2 = 2;
      return (String(Math.random()) + String(Math.random()) + String(Math.random())).slice(2, 15);
    }

  };
})();


_.getQueryParam = function(url, param) {
  param = param.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
  var regexS = "[\\?&]" + param + "=([^&#]*)",
    regex = new RegExp(regexS),
    results = regex.exec(url);
  if (results === null || (results && typeof(results[1]) !== 'string' && results[1].length)) {
    return '';
  } else {
    return _.decodeURIComponent(results[1]).replace(/\+/g, ' ');
  }
};

_.urlParse = function(para) {
  var URLParser = function(a) {
    this._fields = {
      Username: 4,
      Password: 5,
      Port: 7,
      Protocol: 2,
      Host: 6,
      Path: 8,
      URL: 0,
      QueryString: 9,
      Fragment: 10
    };
    this._values = {};
    this._regex = null;
    this._regex = /^((\w+):\/\/)?((\w+):?(\w+)?@)?([^\/\?:]+):?(\d+)?(\/?[^\?#]+)?\??([^#]+)?#?(\w*)/;

    if (typeof a != 'undefined') {
      this._parse(a)
    }
  };
  URLParser.prototype.setUrl = function(a) {
    this._parse(a)
  };
  URLParser.prototype._initValues = function() {
    for (var a in this._fields) {
      this._values[a] = ''
    }
  };
  URLParser.prototype.addQueryString = function(queryObj) {
      if(typeof queryObj !== 'object'){
          return false;
      }
      var query = this._values.QueryString || '';
      for(var i in queryObj){
         if(new RegExp(i+'[^&]+').test(query)){         
            query = query.replace(new RegExp(i+'[^&]+'), i + '=' + queryObj[i]);
         }else{
             if(query.slice(-1) === '&'){
                 query = query + i + '=' + queryObj[i];
             }else{
              if(query === ''){
                query = i + '=' + queryObj[i];
              }else{
                query = query + '&' + i + '=' + queryObj[i];
              }
             }
         }
      }
      this._values.QueryString = query;
  };  
  URLParser.prototype.getUrl = function() {
    var url = '';
    url += this._values.Origin;
    url += this._values.Port ? ':' + this._values.Port : '';
    url += this._values.Path;
    url += this._values.QueryString ? '?' + this._values.QueryString : '';
    url += this._values.Fragment ? '#'+this._values.Fragment : '';    
    return url;
  };

  URLParser.prototype.getUrl = function() {
    var url = '';
    url += this._values.Origin;
    url += this._values.Port ? ':' + this._values.Port : '';
    url += this._values.Path;
    url += this._values.QueryString ? '?' + this._values.QueryString : '';
    return url;
  };
  URLParser.prototype._parse = function(a) {
    this._initValues();
    var b = this._regex.exec(a);
    if (!b) {
      throw 'DPURLParser::_parse -> Invalid URL'
    }
    for (var c in this._fields) {
      if (typeof b[this._fields[c]] != 'undefined') {
        this._values[c] = b[this._fields[c]]
      }
    }
    this._values['Hostname'] = this._values['Host'].replace(/:\d+$/, '');
    this._values['Origin'] = this._values['Protocol'] + '://' + this._values['Hostname'];

  };
  return new URLParser(para);
};

//2018-03-16 获取sessionID
_.getSessionId=  function() {

    //普通的循环遍历方式
    var sessionid= _.cookie.get("prophet_session_id");


    return sessionid;
};

_.addEvent = function() {

    function fixEvent(event) {
        if (event) {
            event.preventDefault = fixEvent.preventDefault;
            event.stopPropagation = fixEvent.stopPropagation;
            event._getPath = fixEvent._getPath;
        }
        return event;
    }
    fixEvent._getPath = function(){
      var ev = this;
      var polyfill = function () {
        try{
          var element = ev.target;
          var pathArr = [element];
          if (element === null || element.parentElement === null) {
              return [];
          }
          while (element.parentElement !== null) {
              element = element.parentElement;
              pathArr.unshift(element); 
          }
          return pathArr;
        }catch(err){
          return [];
        }

      };
      return this.path || (this.composedPath && this.composedPath()) || polyfill();
    };
    fixEvent.preventDefault = function() {
        this.returnValue = false;
    };
    fixEvent.stopPropagation = function() {
        this.cancelBubble = true;
    };


    var register_event = function(element, type, handler) {
        if (element && element.addEventListener) {
            element.addEventListener(type, function(e){
              e._getPath = fixEvent._getPath;
              handler.call(this,e);
            }, false);
        } else {
            var ontype = 'on' + type;
            var old_handler = element[ontype];
            element[ontype] = makeHandler(element, handler, old_handler);
        }
    };
    function makeHandler(element, new_handler, old_handlers) {
        var handler = function(event) {
            event = event || fixEvent(window.event);
            if (!event) {
                return undefined;
            }
            event.target = event.srcElement;

            var ret = true;
            var old_result, new_result;
            if (typeof old_handlers === 'function') {
                old_result = old_handlers(event);
            }
            new_result = new_handler.call(element, event);
            if ((false === old_result) || (false === new_result)) {
                ret = false;
            }
            return ret;
        };
        return handler;
    }

    register_event.apply(null,arguments);
};


_.addHashEvent = function(callback){
  var hashEvent = ('pushState' in window.history ? 'popstate' : 'hashchange');
  _.addEvent(window,hashEvent,callback);
};

_.cookie = {
  get: function(name) {
    var nameEQ = name + '=';
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1, c.length);
      }
      if (c.indexOf(nameEQ) == 0) {
        return _.decodeURIComponent(c.substring(nameEQ.length, c.length));
      }
    }
    return null;
  },
  set: function(name, value, days, cross_subdomain, is_secure) {
    cross_subdomain = typeof cross_subdomain === 'undefined' ? sd.para.cross_subdomain : cross_subdomain;
    var cdomain = '', expires = '', secure = '';
    days = days == null ? 73000 : days;

    if (cross_subdomain) {
      var domain = _.url('domain',location.href);

      cdomain = ((domain) ? '; domain=.' + domain : '');
    }

    // 0 session
    // -1 马上过期
    //
    if (days !== 0) {
      var date = new Date();
      // 默认是填，可以是秒
      if (String(days).slice(-1) === 's') {
        date.setTime(date.getTime() + (Number(String(days).slice(0, -1)) * 1000));
      } else {
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
      }

      expires = '; expires=' + date.toGMTString();
    }

    if (is_secure) {
      secure = '; secure';
    }

    document.cookie = name + '=' + encodeURIComponent(value) + expires
      + '; path=/' + cdomain + secure;
  },

  remove: function(name, cross_subdomain) {
    cross_subdomain = typeof cross_subdomain === 'undefined' ? sd.para.cross_subdomain : cross_subdomain;
    _.cookie.set(name, '', -1, cross_subdomain);

  },

  getCookieName: function(name_prefix){
    var sub = '';
    if(sd.para.cross_subdomain === false){
      sub = _.url('sub',location.href);
      if(typeof sub === 'string' && sub !== ''){
        sub = 'sajssdk_2015_' + name_prefix + '_' + sub;
      }else{
        sub = 'sajssdk_2015_root_' + name_prefix;
      }
    }else{
      sub = 'sajssdk_2015_cross_' + name_prefix;
    } 
    return sub;
  },
// 针对新用户的兼容性判断,兼容子域名
  getNewUser: function(){
    var prefix = 'new_user';
    if(this.get('sensorsdata_is_new_user') !== null || this.get(this.getCookieName(prefix)) !== null){
      return true;
    }else{
      return false;
    }
  }
};

// 获取元素的一些信息
_.getEleInfo = function(obj){
  if(!obj.target){
    return false;
  }

  var target = obj.target;
  var tagName = target.tagName.toLowerCase();


  var props = {};

  props.$element_type = tagName;
  props.$element_name = target.getAttribute('name');
  props.$element_id = target.getAttribute('id');
  props.$element_class_name = typeof target.className === 'string' ? target.className : null;
  props.$element_target_url = target.getAttribute('href');

  // 获取内容
  
  var textContent = '';
  if (target.textContent) {
    textContent = _.trim(target.textContent);
  }else if(target.innerText){
    textContent = _.trim(target.innerText);
  }
  if (textContent) {
    textContent = textContent.replace(/[\r\n]/g, ' ').replace(/[ ]+/g, ' ').substring(0, 255);
  }
  props.$element_content = textContent || '';


  props = _.strip_empty_properties(props);

  props.$url = location.href;
  props.$url_path = location.pathname;
  props.$title = document.title;

  return props;

};

// _.localStorage
_.localStorage = {
  get: function(name) {
    return window.localStorage.getItem(name);
  },

  parse: function(name) {
    var storedValue;
    try {
      storedValue = JSON.parse(_.localStorage.get(name)) || null;
    } catch (err) {
    }
    return storedValue;
  },

  set: function(name, value) {
    window.localStorage.setItem(name, value);
  },

  remove: function(name) {
    window.localStorage.removeItem(name);
  },

  isSupport: function() {
    var supported = true;
    try {
      var key = '__sensorsdatasupport__';
      var val = 'testIsSupportStorage';
      _.localStorage.set(key, val);
      if (_.localStorage.get(key) !== val) {
        supported = false;
      }
      _.localStorage.remove(key);
    } catch (err) {
      supported = false;
    }
    return supported;
  }

};

_.sessionStorage = {

  isSupport:function(){
      var supported = true;

      var key = '__sensorsdatasupport__';
      var val = 'testIsSupportStorage';
      try{
        if(sessionStorage && sessionStorage.setItem){
          sessionStorage.setItem(key,val);
          sessionStorage.removeItem(key,val);
          supported = true;
        }else{
          supported = false;          
        }
      }catch(e){
        supported = false;
      }
      return supported;
  }
};

_.xhr = function(cors) {
  if (cors) {
    var xhr = new XMLHttpRequest();
    if ("withCredentials" in xhr) {
      return xhr;
    } else if (typeof XDomainRequest != "undefined") {
      return new XDomainRequest();
    } else {
      return xhr;
    }
  } else {
    if (XMLHttpRequest) {
      return new XMLHttpRequest();
    }
    if (window.ActiveXObject) {
      try {
        return new ActiveXObject('Msxml2.XMLHTTP')
      } catch (d) {
        try {
          return new ActiveXObject('Microsoft.XMLHTTP')
        } catch (d) {
        }
      }
    }
  }
};

_.ajax = function(para) {
  function getJSON(data) {
    try {
      return JSON.parse(data);
    } catch (e) {
      return {};
    }
  }

  var g = _.xhr(para.cors);
  if (!para.type) {
    para.type = para.data ? 'POST' : 'GET';
  }
  para = _.extend({
    success: function() {},
    error: function() {}
  }, para);


  g.onreadystatechange = function() {
    if (g.readyState == 4) {
      if ((g.status >= 200 && g.status < 300) || g.status == 304) {
        para.success(getJSON(g.responseText));
      } else {
        para.error(getJSON(g.responseText), g.status);
      }
      g.onreadystatechange = null;
      g.onload = null;
    }
  };


  g.open(para.type, para.url, true);

  try {
    g.withCredentials = true;

    if (_.isObject(para.header)) {
      for (var i in para.header) {
        g.setRequestHeader(i, para.header[i]);
      }
    }

    if (para.data) {
      g.setRequestHeader("X-Requested-With", "XMLHttpRequest");
      if (para.contentType === 'application/json') {
        g.setRequestHeader("Content-type", "application/json; charset=UTF-8");
      } else {
        g.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      }

    }
  } catch (e) {
  }

  g.send(para.data || null);


};


 _.loadScript = function(para) {
  para = _.extend({
    success: function() {},
    error: function() {},
    appendCall: function(g) {
      document.getElementsByTagName('head')[0].appendChild(g);
    }
  }, para);
  
    var g = null;
    if (para.type === 'css') {
      g = document.createElement('link');
      g.rel = 'stylesheet';
      g.href = para.url;
    }
    if (para.type === 'js') {
      g = document.createElement('script');
      g.async = 'async';
      g.setAttribute('charset','UTF-8');
      g.src = para.url;
      g.type = 'text/javascript';
    }
    g.onload = g.onreadystatechange = function() {
      if (!this.readyState || this.readyState === 'loaded' || this.readyState === 'complete') {
        para.success();
        g.onload = g.onreadystatechange = null;
      }
    };
    g.onerror = function() {
      para.error();
      g.onerror = null;
    };
    // if iframe
    para.appendCall(g);  
};

//https://github.com/websanova/js-url

_.url = (function() {

    function _t() {
      return new RegExp(/(.*?)\.?([^\.]*?)\.(com|net|org|biz|ws|in|me|co\.uk|co|org\.uk|ltd\.uk|plc\.uk|me\.uk|edu|mil|br\.com|cn\.com|eu\.com|hu\.com|no\.com|qc\.com|sa\.com|se\.com|se\.net|us\.com|uy\.com|ac|co\.ac|gv\.ac|or\.ac|ac\.ac|af|am|as|at|ac\.at|co\.at|gv\.at|or\.at|asn\.au|com\.au|edu\.au|org\.au|net\.au|id\.au|be|ac\.be|adm\.br|adv\.br|am\.br|arq\.br|art\.br|bio\.br|cng\.br|cnt\.br|com\.br|ecn\.br|eng\.br|esp\.br|etc\.br|eti\.br|fm\.br|fot\.br|fst\.br|g12\.br|gov\.br|ind\.br|inf\.br|jor\.br|lel\.br|med\.br|mil\.br|net\.br|nom\.br|ntr\.br|odo\.br|org\.br|ppg\.br|pro\.br|psc\.br|psi\.br|rec\.br|slg\.br|tmp\.br|tur\.br|tv\.br|vet\.br|zlg\.br|br|ab\.ca|bc\.ca|mb\.ca|nb\.ca|nf\.ca|ns\.ca|nt\.ca|on\.ca|pe\.ca|qc\.ca|sk\.ca|yk\.ca|ca|cc|ac\.cn|net\.cn|com\.cn|edu\.cn|gov\.cn|org\.cn|bj\.cn|sh\.cn|tj\.cn|cq\.cn|he\.cn|nm\.cn|ln\.cn|jl\.cn|hl\.cn|js\.cn|zj\.cn|ah\.cn|gd\.cn|gx\.cn|hi\.cn|sc\.cn|gz\.cn|yn\.cn|xz\.cn|sn\.cn|gs\.cn|qh\.cn|nx\.cn|xj\.cn|tw\.cn|hk\.cn|mo\.cn|cn|cx|cz|de|dk|fo|com\.ec|tm\.fr|com\.fr|asso\.fr|presse\.fr|fr|gf|gs|co\.il|net\.il|ac\.il|k12\.il|gov\.il|muni\.il|ac\.in|co\.in|org\.in|ernet\.in|gov\.in|net\.in|res\.in|is|it|ac\.jp|co\.jp|go\.jp|or\.jp|ne\.jp|ac\.kr|co\.kr|go\.kr|ne\.kr|nm\.kr|or\.kr|li|lt|lu|asso\.mc|tm\.mc|com\.mm|org\.mm|net\.mm|edu\.mm|gov\.mm|ms|nl|no|nu|pl|ro|org\.ro|store\.ro|tm\.ro|firm\.ro|www\.ro|arts\.ro|rec\.ro|info\.ro|nom\.ro|nt\.ro|se|si|com\.sg|org\.sg|net\.sg|gov\.sg|sk|st|tf|ac\.th|co\.th|go\.th|mi\.th|net\.th|or\.th|tm|to|com\.tr|edu\.tr|gov\.tr|k12\.tr|net\.tr|org\.tr|com\.tw|org\.tw|net\.tw|ac\.uk|uk\.com|uk\.net|gb\.com|gb\.net|vg|sh|kz|ch|info|ua|gov|name|pro|ie|hk|com\.hk|org\.hk|net\.hk|edu\.hk|us|tk|cd|by|ad|lv|eu\.lv|bz|es|jp|cl|ag|mobi|eu|co\.nz|org\.nz|net\.nz|maori\.nz|iwi\.nz|io|la|md|sc|sg|vc|tw|travel|my|se|tv|pt|com\.pt|edu\.pt|asia|fi|com\.ve|net\.ve|fi|org\.ve|web\.ve|info\.ve|co\.ve|tel|im|gr|ru|net\.ru|org\.ru|hr|com\.hr|ly|xyz)$/);
    }

    function _d(s) {
      return _.decodeURIComponent(s.replace(/\+/g, ' '));
    }

    function _i(arg, str) {
        var sptr = arg.charAt(0),
            split = str.split(sptr);

        if (sptr === arg) { return split; }

        arg = parseInt(arg.substring(1), 10);

        return split[arg < 0 ? split.length + arg : arg - 1];
    }

    function _f(arg, str) {
        var sptr = arg.charAt(0),
            split = str.split('&'),
            field = [],
            params = {},
            tmp = [],
            arg2 = arg.substring(1);

        for (var i = 0, ii = split.length; i < ii; i++) {
            field = split[i].match(/(.*?)=(.*)/);

            // TODO: regex should be able to handle this.
            if ( ! field) {
                field = [split[i], split[i], ''];
            }

            if (field[1].replace(/\s/g, '') !== '') {
                field[2] = _d(field[2] || '');

                // If we have a match just return it right away.
                if (arg2 === field[1]) { return field[2]; }

                // Check for array pattern.
                tmp = field[1].match(/(.*)\[([0-9]+)\]/);

                if (tmp) {
                    params[tmp[1]] = params[tmp[1]] || [];
                
                    params[tmp[1]][tmp[2]] = field[2];
                }
                else {
                    params[field[1]] = field[2];
                }
            }
        }

        if (sptr === arg) { return params; }

        return params[arg2];
    }

    return function(arg, url) {
        var _l = {}, tmp, tmp2;

        if (arg === 'tld?') { return _t(); }

        url = url || window.location.toString();

        if ( ! arg) { return url; }

        arg = arg.toString();

        if (tmp = url.match(/^mailto:([^\/].+)/)) {
            _l.protocol = 'mailto';
            _l.email = tmp[1];
        }
        else {

            // Ignore Hashbangs.
            if (tmp = url.match(/(.*?)\/#\!(.*)/)) {
                url = tmp[1] + tmp[2];
            }

            // Hash.
            if (tmp = url.match(/(.*?)#(.*)/)) {
                _l.hash = tmp[2];
                url = tmp[1];
            }

            // Return hash parts.
            if (_l.hash && arg.match(/^#/)) { return _f(arg, _l.hash); }

            // Query
            if (tmp = url.match(/(.*?)\?(.*)/)) {
                _l.query = tmp[2];
                url = tmp[1];
            }

            // Return query parts.
            if (_l.query && arg.match(/^\?/)) { return _f(arg, _l.query); }

            // Protocol.
            if (tmp = url.match(/(.*?)\:?\/\/(.*)/)) {
                _l.protocol = tmp[1].toLowerCase();
                url = tmp[2];
            }

            // Path.
            if (tmp = url.match(/(.*?)(\/.*)/)) {
                _l.path = tmp[2];
                url = tmp[1];
            }

            // Clean up path.
            _l.path = (_l.path || '').replace(/^([^\/])/, '/$1').replace(/\/$/, '');

            // Return path parts.
            if (arg.match(/^[\-0-9]+$/)) { arg = arg.replace(/^([^\/])/, '/$1'); }
            if (arg.match(/^\//)) { return _i(arg, _l.path.substring(1)); }

            // File.
            tmp = _i('/-1', _l.path.substring(1));
            
            if (tmp && (tmp = tmp.match(/(.*?)\.(.*)/))) {
                _l.file = tmp[0];
                _l.filename = tmp[1];
                _l.fileext = tmp[2];
            }

            // Port.
            if (tmp = url.match(/(.*)\:([0-9]+)$/)) {
                _l.port = tmp[2];
                url = tmp[1];
            }

            // Auth.
            if (tmp = url.match(/(.*?)@(.*)/)) {
                _l.auth = tmp[1];
                url = tmp[2];
            }

            // User and pass.
            if (_l.auth) {
                tmp = _l.auth.match(/(.*)\:(.*)/);

                _l.user = tmp ? tmp[1] : _l.auth;
                _l.pass = tmp ? tmp[2] : undefined;
            }

            // Hostname.
            _l.hostname = url.toLowerCase();

            // Return hostname parts.
            if (arg.charAt(0) === '.') { return _i(arg, _l.hostname); }

            // Domain, tld and sub domain.
            if (_t()) {
                tmp = _l.hostname.match(_t());

                if (tmp) {
                    _l.tld = tmp[3];
                    _l.domain = tmp[2] ? tmp[2] + '.' + tmp[3] : undefined;
                    _l.sub = tmp[1] || undefined;
                }
            }

            // Set port and protocol defaults if not set.
            _l.port = _l.port || (_l.protocol === 'https' ? '443' : '80');
            _l.protocol = _l.protocol || (_l.port === '443' ? 'https' : 'http');
        }

        // Return arg.
        if (arg in _l) { return _l[arg]; }

        // Return everything.
        if (arg === '{}') { return _l; }

        // Default to undefined for no match.
        return '';
    };
})();

_.ry = function(dom){  
  return new _.ry.init(dom);
};
_.ry.init = function(dom){
  this.ele = dom;
};
_.ry.init.prototype = {
  addClass: function(para){
    var classes = ' ' + this.ele.className + ' ';
    if(classes.indexOf(' ' + para + ' ') === -1){
      this.ele.className = this.ele.className + (this.ele.className === '' ? '' : ' ') + para;
    }
    return this;
  },
  removeClass: function(para){
    var classes = ' ' + this.ele.className + ' ';
    if(classes.indexOf(' ' + para + ' ') !== -1){
      this.ele.className = classes.replace(' ' + para + ' ', ' ').slice(1,-1);
    }
    return this;
  },
  hasClass: function(para){
    var classes = ' ' + this.ele.className + ' ';    
    if(classes.indexOf(' ' + para + ' ') !== -1){
      return true;
    }else{
      return false;
    }
  },
  attr: function(key,value){
    if(typeof key === 'string' && _.isUndefined(value)){
      return this.ele.getAttribute(key);
    }
    if(typeof key === 'string'){
      value = String(value);
      this.ele.setAttribute(key,value);
    }
    return this;
  },
  offset: function(){
      var rect = this.ele.getBoundingClientRect();
      if ( rect.width || rect.height ) {
        var doc = this.ele.ownerDocument;
        var docElem = doc.documentElement;

        return {
          top: rect.top + window.pageYOffset - docElem.clientTop,
          left: rect.left + window.pageXOffset - docElem.clientLeft
        };
      }else{
        return {
          top: 0,
          left: 0
        }
      }

  },
  getSize: function(){
    if (!window.getComputedStyle) {
      return {width: this.ele.offsetWidth, height: this.ele.offsetHeight};
    }
    try {
      var bounds = this.ele.getBoundingClientRect();
      return {width: bounds.width, height: bounds.height};
    } catch (e){
      return {width: 0, height: 0};
    }
  },
  getStyle: function(value){
    if(this.ele.currentStyle){
      return this.ele.currentStyle[value];
    }else{
      return this.ele.ownerDocument.defaultView.getComputedStyle(this.ele, null).getPropertyValue(value);
    }
  },
  wrap: function(elementTagName){
    var ele = document.createElement(elementTagName);
    this.ele.parentNode.insertBefore(ele, this.ele);
    ele.appendChild(this.ele);
    return _.ry(ele);
  },
  getCssStyle: function(prop){
    var result = this.ele.style.getPropertyValue(prop);
    if (result) {
        return result;
    }
    var rules = null;
    if(typeof window.getMatchedCSSRules === 'function'){
      rules = getMatchedCSSRules(this.ele);
    }
    if(!rules || !_.isArray(rules)){
      return null;
    }
    for (var i = rules.length - 1; i >= 0; i--) {
        var r = rules[i];
        result = r.style.getPropertyValue(prop);
        if (result) {
            return result;
        }
    }
  },
  sibling:function(cur, dir ){
    while ( ( cur = cur[ dir ] ) && cur.nodeType !== 1 ) {}
    return cur;
  },
  next: function() {
    return this.sibling( this.ele, "nextSibling" );
  },
  prev: function( elem ) {
    return this.sibling( this.ele, "previousSibling" );
  },
  siblings: function( elem ) {
    return this.siblings( ( this.ele.parentNode || {} ).firstChild, this.ele);
  },
  children: function( elem ) {
    return this.siblings( this.ele.firstChild );
  },
  parent: function(){
    var parent = this.ele.parentNode;
    parent = parent && parent.nodeType !== 11 ? parent : null;
    return _.ry(parent);
  }
};

_.jssdkDebug = function(recevie_prop,has_prop){
  if (sd.para.is_debug) {
    if(_.isString(recevie_prop)){

      sd.registerPage({_jssdk_debug_info: recevie_prop});

    }else{

      var cookie = store.getCookieName();
      var match = document.cookie.match(new RegExp(cookie + '[^;]+'));
      if(match && match[0] ){
        cookie = match[0];
      }else{
        cookie = '';
      }
      recevie_prop._jssdk_debug_info = '(' + cookie + ')' + navigator.userAgent;

    }
  }
};
_.strToUnicode = function(str){
  if(typeof str !== 'string'){
    logger.info('转换unicode错误',str);
    return str;    
  }
  var nstr = '';
  for(var i = 0; i<str.length; i++){
    nstr += '\\'+str.charCodeAt(i).toString(16);
  }
  return nstr;
};

_.querySelectorAll = function(val){

  if(typeof val !== 'string'){
    logger.info('选择器错误',val);
    return [];    
  }
  // 替换纯数字的id
  var sp = val.split(' ');
  if(sp.length === 1){
    if(/^#\d+/.test(sp[0])){
      val = '#' + _.strToUnicode(sp[0].slice(1));
    }
  }else{
    if(/^#\d+/.test(sp[0])){
      sp[0] = '#' + _.strToUnicode(sp[0].slice(1));
      val = sp.join(' ');
    }
  }

  try{
     return document.querySelectorAll(val);
  }catch(e){
    logger.info('错误',val);
    return [];
  }
};

_.getReferrer = function(referrer){
  var referrer = referrer || document.referrer;
  if(typeof referrer !== 'string'){
    return '取值异常_referrer异常_' + String(referrer);
  }
  if (referrer.indexOf("https://www.baidu.com/") === 0) {
    referrer =  referrer.split('?')[0];
  }
  referrer = referrer.slice(0, sd.para.max_referrer_string_length);
  return (typeof referrer === 'string' ? referrer : '' );
};

_.getKeywordFromReferrer = function(){
  var search_keyword = {baidu:'wd',google:'q',bing:'q',yahoo:'p',sogou:'query',so:'q'};
  if(document && typeof document.referrer === 'string'){
    if(document.referrer.indexOf('http') === 0) {
      var domain = _.url('domain',document.referrer);
      var query = _.url('?',document.referrer);
      for(var i in search_keyword){
        if(domain.indexOf(i) === 0){
          if(typeof query === 'object' && query[search_keyword[i]]){
            return query[search_keyword[i]];
          }
        }
      }
      return '未取到值';
    }else{
      if(document.referrer === ''){
        return '未取到值_直接打开';
      }else{
        return '未取到值_非http的url';
      }
    }
  }else{
    return '取值异常_referrer异常_' + String(document.referrer);
  }
};

_.getSourceFromReferrer = function(){
  function getMatchStrFromArr(arr,str){
    for(var i = 0; i<arr.length; i++){
      if(str.split('?')[0].indexOf(arr[i]) !== -1){
        return true;
      }
    }
  }

  var search_engine = ['www.baidu.','m.baidu.','m.sm.cn','so.com','sogou.com','youdao.com','google.','yahoo.com/','bing.com/','ask.com/'];
  var social_engine = ['weibo.com','renren.com','kaixin001.com','douban.com','qzone.qq.com','zhihu.com','tieba.baidu.com','weixin.qq.com'];

  var referrer = document.referrer || '';
  var url = _.info.pageProp.url;
  if(url){
    var utm_match = url.match(/(utm_source|utm_medium|utm_campaign|utm_content|utm_term)\=[^&]+/);    
    if(utm_match && utm_match[0]){
      return '付费广告流量';
    }else if(getMatchStrFromArr(search_engine,referrer)){
      return '自然搜索流量';
    }else if(getMatchStrFromArr(social_engine,referrer)){
      return '社交网站流量';
    }else if(referrer === ''){
      return '直接流量';
    }else{
      return '引荐流量';
    }
  }else{
    return '获取url异常';
  }
};

_.info = {
  initPage: function() {
    var referrer = _.getReferrer();
    var referrer_host = referrer ? _.url('hostname',referrer) : referrer;
    var referrer_domain = referrer ? _.url('domain',referrer) : referrer;
    var url = location.href;
    var url_host = url ? _.url('hostname',url) : url;
    var url_domain = url ? _.url('domain',url) : url;

    if(referrer && !referrer_domain){
      _.jssdkDebug('referrer_domain异常_' + referrer + '_' + referrer_domain);
    }
    if(!url_domain){
      _.jssdkDebug('url_domain异常_'+ url + '_' + url_domain);
    }

    this.pageProp = {
      referrer: referrer,
      referrer_host: referrer_host,
      referrer_domain: referrer_domain,
      url: url,
      url_host: url_host,
      url_domain: url_domain
    };


  },
  //当前页面的一些属性，在store初始化是生成
  pageProp: {}, 

  campaignParams: function() {
    var campaign_keywords = source_channel_standard.split(' ')
      , kw = ''
      , params = {};
    if (_.isArray(sd.para.source_channel) && sd.para.source_channel.length > 0) {
      campaign_keywords = campaign_keywords.concat(sd.para.source_channel);
      campaign_keywords = _.unique(campaign_keywords);
    }
    _.each(campaign_keywords, function(kwkey) {
      kw = _.getQueryParam(location.href, kwkey);
      if (kw.length) {
        params[kwkey] = kw;
      }
    });

    return params;
  },
  campaignParamsStandard: function(prefix,prefix_add) {
    prefix = prefix || '';
    prefix_add = prefix_add || '';
    var utms = _.info.campaignParams();
    var $utms = {}, otherUtms = {};
    for (var i in utms) {
      if ((' ' + source_channel_standard + ' ').indexOf(' ' + i + ' ') !== -1) {
        $utms[prefix + i] = utms[i];
      } else {
        otherUtms[prefix_add + i] = utms[i];
      }
    }
    return {
      $utms: $utms,
      otherUtms: otherUtms
    };
  },
  // 预置属性
  properties: function() {
    return {
      $screen_height: Number(screen.height) || 0,
      $screen_width: Number(screen.width) || 0,
      // 我说两遍写的重复，佳捷说就写两遍
      //$lib: 'js',
      //$lib_version: String(LIB_VERSION)
    };
  },
  // 保存临时的一些变量，只针对当前页面有效
  currentProps: {},
  register: function(obj) {
    _.extend(_.info.currentProps, obj);
  }
};

// 数据发送状态
sd.sendState = {}
//成功发送数
sd.sendState._complete = 0;
//接受发送数
sd.sendState._receive = 0;

sd.sendState.getSendCall = function(data, callback) {


  // 加cache防止缓存
  data._nocache = (String(Math.random()) + String(Math.random()) + String(Math.random())).replace(/\./g,'').slice(0,15);
  var originData = data;
  data = JSON.stringify(data);
  logger.info(originData);

  this.prepareServerUrl(data,callback);

  //todo  打通app传数据给app 暂时不用
 /* if(sd.para.use_app_track === true || sd.para.use_app_track === 'only'){
    if((typeof SensorsData_APP_JS_Bridge === 'object') && SensorsData_APP_JS_Bridge.sensorsdata_track){
      SensorsData_APP_JS_Bridge.sensorsdata_track(data);
      (typeof callback === 'function') && callback();      
    }else if(/sa-sdk-ios/.test(navigator.userAgent) && !window.MSStream){
      var iframe = document.createElement('iframe');
      iframe.setAttribute('src', 'sensorsanalytics://trackEvent?event=' + encodeURIComponent(data));
      document.documentElement.appendChild(iframe);
      iframe.parentNode.removeChild(iframe);
      iframe = null;
      (typeof callback === 'function') && callback();      
    }else{
      if(sd.para.use_app_track === true){
        this.prepareServerUrl(data,callback);
      }
    }
  }else if(sd.para.use_app_track === 'mui'){
    if(_.isObject(window.plus) && window.plus.SDAnalytics && window.plus.SDAnalytics.trackH5Event){
      window.plus.SDAnalytics.trackH5Event(data);
    }
  } else{
    this.prepareServerUrl(data,callback);
  }*/
};

sd.sendState.getUrlPara = function(url,data){
  //todo 不进行base64
  //var base64Data = _.base64Encode(data);
  var crc = 'crc=' + _.hashCode(data);
  if (url.indexOf('?') !== -1) {
    return url + '&data=' + encodeURIComponent(data) + '&ext=' + encodeURIComponent(crc);
  }else{
    return url + '?data=' + encodeURIComponent(data) + '&ext=' + encodeURIComponent(crc);
  }

};

sd.sendState.prepareServerUrl = function(data,callback){
  if(_.isArray(sd.para.server_url)){
    for(var i =0; i<sd.para.server_url.length;i++){
      this.sendCall(this.getUrlPara(sd.para.server_url[i],data),callback);
    }
  }else{
    this.sendCall(this.getUrlPara(sd.para.server_url,data),callback);
  }
};

sd.sendState.stateInfo = function(para){
  this.callback = para.callback;
  this.hasCalled = false;
  this.img = document.createElement('img');
  this.server_url = para.server_url;
  this.sendState = para.sendState;
  this.start();
};

sd.sendState.stateInfo.prototype.start = function(){
  var me = this;
  function callAndDelete(){
    if(typeof me === 'object' && typeof me.callback === 'function' && !me.hasCalled){
      me.hasCalled = true;
      me.callback();
    }
  }
  setTimeout(callAndDelete, sd.para.callback_timeout);
  this.img.onload = function(e) {
    this.onload = null;
    ++me.sendState._complete;
    callAndDelete();
  };
  this.img.onerror = function(e) {
    this.onerror = null;
    callAndDelete();
  };
  this.img.onabort = function(e) {
    this.onabort = null;
    callAndDelete();
  };
  this.img.src = this.server_url;
};

sd.sendState.sendCall = function(server_url,callback){
  ++this._receive;
  var state = '_state' + this._receive;
  var me = this;
  this[state] = new this.stateInfo({
    callback: callback,
    server_url: server_url,
    sendState: this
  });  
};

// 检查是否是新用户（第一次种cookie，且在8个小时内的）
var saNewUser = {
  checkIsAddSign: function(data) {
    if (data.type === 'track') {
      if (_.cookie.getNewUser()) {
        data.properties.$is_first_day = true;
      } else {
        data.properties.$is_first_day = false;
      }
    }
  },
  is_first_visit_time: false,
  checkIsFirstTime: function(data) {
    if (data.type === 'track' && data.event === '$pageview') {
      if (this.is_first_visit_time) {
        data.properties.$is_first_time = true;
        this.is_first_visit_time = false;
      } else {
        data.properties.$is_first_time = false;
      }
    }
  },
  setDeviceId: function(uuid){
    // deviceid必须跨子域
    var device_id = null;
    var ds = _.cookie.get('prophet2018jssdkcross');
    var state = {};
    if (ds != null && _.isJSONString(ds)) {
      state = JSON.parse(ds);
      if(state.$device_id) {
        device_id = state.$device_id;
      }
    }

    device_id = device_id || uuid;

    if(sd.para.cross_subdomain === true){
      store.set('$device_id',device_id);
    }else{
      state.$device_id = device_id;
      _.cookie.set('prophet2018jssdkcross',JSON.stringify(state),null,true);
    }

    if(sd.para.is_track_device_id){
      _.info.currentProps.$device_id = device_id;
    }

  },
  storeInitCheck: function() {
    // 如果是新用户，种cookie
    if (is_first_visitor) {

      var date = new Date();
      var obj = {
        h: 23 - date.getHours(),
        m: 59 - date.getMinutes(),
        s: 59 - date.getSeconds()
      };
      _.cookie.set(_.cookie.getCookieName('new_user'), '1', obj.h * 3600 + obj.m * 60 + obj.s + 's');
      // 如果是is_first_visit_time，且第一次，那就发数据
      this.is_first_visit_time = true;
    } else {
      // 如果没有这个cookie，肯定不是首日
      if (!_.cookie.getNewUser()) {
        this.checkIsAddSign = function(data) {
          if (data.type === 'track') {
            data.properties.$is_first_day = false;
          }
        };
      }
      // 如果不是第一次打开的用户，肯定不是首次访问
      this.checkIsFirstTime = function(data) {
        if (data.type === 'track' && data.event === '$pageview') {
          data.properties.$is_first_time = false;
        }
      }
    }
  },
  //检查是否是latest
  checkIsFirstLatest: function() {
    var url_domain = _.info.pageProp.url_domain;
    var referrer_domain = _.info.pageProp.referrer_domain;

    //去除详叔的坑，utm_source相关   
     var latest_utms = ['$utm_source','$utm_medium', '$utm_campaign', '$utm_content', '$utm_term'];   
     var props = store.getProps();
     for(var i =0;i<latest_utms.length;i++){   
       if(latest_utms[i] in props){    
         delete props[latest_utms[i]];   
       }   
     }   
     store.setProps(props,true);


    // 判断最近一次，如果前向地址跟自己域名一致，且cookie中取不到值，认为有异常
    // 最近一次站外前向地址，如果域名不一致，就认未register为latest
    if(url_domain !== referrer_domain){
      sd.register({
        $latest_traffic_source_type:_.getSourceFromReferrer(),
        $latest_referrer: _.info.pageProp.referrer,
        $latest_referrer_host: _.info.pageProp.referrer_host,
        $latest_search_keyword: _.getKeywordFromReferrer()        
      });
    }
    // utm
    var allUtms = _.info.campaignParamsStandard('$latest_','_latest_');
    var $utms = allUtms.$utms;
    var otherUtms = allUtms.otherUtms;
    if (!_.isEmptyObject($utms)) {
      sd.register($utms);
    }
    if (!_.isEmptyObject(otherUtms)) {
      sd.register(otherUtms);
    }


  }

};

var saEvent = {};

saEvent.checkOption = {
  // event和property里的key要是一个合法的变量名，由大小写字母、数字、下划线和$组成，并且首字符不能是数字。
  regChecks: {
    regName: /^((?!^distinct_id$|^original_id$|^time$|^properties$|^id$|^first_id$|^second_id$|^users$|^events$|^event$|^user_id$|^date$|^datetime$)[a-zA-Z_$][a-zA-Z\d_$]{0,99})$/i
  },
  checkPropertiesKey: function(obj) {
    var me = this, flag = true;
    _.each(obj, function(content, key) {
      if (!me.regChecks.regName.test(key)) {
        flag = false;
      }
    });
    return flag;
  },
  check: function(a, b) {
    if (typeof this[a] === 'string') {
      return this[this[a]](b);
    } else {
      return this[a](b);
    }
  },
  str: function(s) {
    if (!_.isString(s)) {
      logger.info('请检查参数格式,必须是字符串');
      //return false;
      return true;
    } else {
      return true;
    }
  },
  properties: function(p) {
    _.strip_sa_properties(p);
    if (p) {
      if (_.isObject(p)) {
        if (this.checkPropertiesKey(p)) {
          return true;
        } else {
          logger.info('properties里的key必须是由字符串数字_组成，且不能是系统保留字');
          //return false;
          return true;
        }
      } else {
        logger.info('properties可以没有，但有的话必须是对象');
        return true;
        //return false;
      }
    } else {
      return true;
    }
  },
  propertiesMust: function(p) {
    _.strip_sa_properties(p);
    if (p === undefined || !_.isObject(p) || _.isEmptyObject(p)) {
      logger.info('properties必须是对象且有值');
      return true;
      //return false;
    } else {
      if (this.checkPropertiesKey(p)) {
        return true;
      } else {
        logger.info('properties里的key必须是由字符串数字_组成，且不能是系统保留字');
        return true;
        //return false;
      }
    }
  },
  // event要检查name
  event: function(s) {
    if (!_.isString(s) || !this['regChecks']['regName'].test(s)) {
      logger.info('请检查参数格式,必须是字符串,且eventName必须是字符串_开头,且不能是系统保留字');
      //return false;
      return true;
    } else {
      return true;
    }

  },
  test_id: 'str',
  group_id: 'str',
  distinct_id: function(id) {
    if (_.isString(id) && /^.{1,255}$/.test(id)) {
      return true;
    } else {
      logger.info('distinct_id必须是不能为空，且小于255位的字符串');
      return false;
    }
  }
};

saEvent.check = function(p) {
  var flag = true;
  for (var i in p) {
    if (!this.checkOption.check(i, p[i])) {
      return false;
    }
  }
  return flag;
};

saEvent.send = function(p, callback) {
  var data = {
    distinct_id: store.getDistinctId(),
    lib: {
      $lib: 'js',
      $lib_method: 'code',
      $lib_version: String(LIB_VERSION)
    },
    project: {
      $teancy_code: sd.para.teancy_code,
      $project_code: sd.para.project_code,
      $project_model_code: sd.para.project_model_code,
      $project_model_version: sd.para.project_model_version
    },
    properties: { }
  };

  if (_.isObject(p) && _.isObject(p.properties) && !_.isEmptyObject(p.properties) && p.properties.$lib_detail) {
    data.lib.$lib_detail = p.properties.$lib_detail;
    delete p.properties.$lib_detail;
  }


  // 测试部分数据没有distinct_id的问题
  if (typeof store.getDistinctId() !== 'string' || typeof store.getDistinctId() === '') {
    var wrong_case = '';
    switch (store.getDistinctId()) {
      case null :
        wrong_case = 'null';
        break;
      case (void 0) :
        wrong_case = 'undefined';
        break;
      case '':
        wrong_case = '空';
        break;
      default:
        wrong_case = String(store.getDistinctId());
    }
    error_msg.push('distinct_id-' + just_test_distinctid + '-' + just_test_distinctid_2 + '-' + wrong_case + '-' + just_test_distinctid_detail + '-' + just_test_distinctid_detail2);
  }

  _.extend(data, p);

  if (error_msg.length > 0) {
    data.jssdk_error = error_msg.join('--');
  }

  // 合并properties里的属性
  if (_.isObject(p.properties) && !_.isEmptyObject(p.properties)) {
    _.extend(data.properties, p.properties);
      data.properties.$session_id=_.getSessionId();
  }
  // 合并lib里的属性
  if (_.isObject(callback)) {
    _.extend(data.lib, callback);
  }

  // profile时不传公用属性
  if (!p.type || p.type.slice(0, 7) !== 'profile') {
    // 传入的属性 > 当前页面的属性 > session的属性 > cookie的属性 > 预定义属性

    data.properties = _.extend({}, _.info.properties(), store.getProps(), store.getSessionProps(), _.info.currentProps, data.properties);
    if(!_.isString(data.properties.$latest_referrer)){
      data.properties.$latest_referrer = '取值异常';
      _.jssdkDebug(data.properties,store.getProps());
    }
    if(!_.isString(data.properties.$latest_referrer_host)){
      data.properties.$latest_referrer_host = '取值异常';
    }
    if(!_.isString(data.properties.$latest_search_keyword)){
      data.properties.$latest_search_keyword = '取值异常';
    }
    if(!_.isString(data.properties.$latest_traffic_source_type)){
      data.properties.$latest_traffic_source_type = '取值异常';
    }

  }



  // 如果$time是传入的就用，否则使用服务端时间
  if (data.properties.$time && _.isDate(data.properties.$time)) {
    data.time = data.properties.$time * 1;
    delete data.properties.$time;
  } else {
    if (sd.para.use_client_time) {
      data.time = (new Date()) * 1;
    }
  }
  _.searchObjDate(data);
  _.searchObjString(data);
  //判断是否要给数据增加新用户属性
  saNewUser.checkIsAddSign(data);
  saNewUser.checkIsFirstTime(data);
    
  
    if (sd.para.debug_mode === true) {
      logger.info(data);
      this.debugPath(JSON.stringify(data), callback);
    } else {
      sd.sendState.getSendCall(data, callback);
    }

  };

  // 发送debug数据请求
  saEvent.debugPath = function(data, callback) {
    var _data = data; //存数据
    var url = '';
    if (sd.para.debug_mode_url.indexOf('?') !== -1) {
      url = sd.para.debug_mode_url + '&data=' + encodeURIComponent(_.base64Encode(data));
    } else {
      url = sd.para.debug_mode_url + '?data=' + encodeURIComponent(_.base64Encode(data));
    }

    _.ajax({
         url: url,
         type: 'GET',
         cors: true,
         header: {'Dry-Run': String(sd.para.debug_mode_upload)},
         success:function(data){
         // debug 模式下 提示框 
          _.isEmptyObject(data) === true ? alert('debug数据发送成功' + _data) : alert('debug失败 错误原因' + JSON.stringify(data));
         }
       });

  };
  
  var store = sd.store = {
      _sessionState: {},
      _state: {},
      getProps: function() {
        return this._state.props || {};
      },
      getSessionProps: function() {
        return this._sessionState;
      },
      getDistinctId: function() {
        return this._state.distinct_id;
      },
      getFirstId: function(){
        return this._state.first_id;
      },
      toState: function(ds) {
        var state = null;
        if (ds != null && _.isJSONString(ds)) {
          state = JSON.parse(ds);
          this._state = _.extend(state);
          if (state.distinct_id) {
            if(typeof(state.props) === 'object'){
              for(var key in state.props){
                if(typeof state.props[key] === 'string'){
                  state.props[key] = state.props[key].slice(0, sd.para.max_referrer_string_length);
                }
              }
              this.save();
            }

          } else {
            this.set('distinct_id', _.UUID());
            error_msg.push('toStateParseDistinctError');
          }
        } else {
          this.set('distinct_id', _.UUID());
          error_msg.push('toStateParseError');
        }
      },
      initSessionState: function() {
        var ds = _.cookie.get('prophet2018session');
        var state = null;
        if (ds !== null && (typeof (state = JSON.parse(ds)) === 'object')) {
          this._sessionState = state || {};
        }
      },

      setOnce: function(a, b) {
        if (!(a in this._state)) {
          this.set(a, b);
        }
      },
      set: function(name, value) {
        this._state = this._state || {};
        this._state[name] = value;
        this.save();
      },
      // 针对当前页面修改
      change: function(name, value) {
        this._state[name] = value;
      },
      setSessionProps: function(newp) {
        var props = this._sessionState;
        _.extend(props, newp);
        this.sessionSave(props);
      },
      setSessionPropsOnce: function(newp) {
        var props = this._sessionState;
        _.coverExtend(props, newp);
        this.sessionSave(props);
      },
      setProps: function(newp,isCover) {
        var props = this._state.props || {};
        if(!isCover){
          _.extend(props, newp);          
          this.set('props', props);
        }else{
          this.set('props', newp);
        }
      },
      removeProps: function(newp){
        if(!_.isArray(newp)){
          return false;
        }
        var props = this._state.props || {};
        for(var i =0 ;i<newp.length;i++){
          if(newp[i] in props){
            delete props[i];
          }
        }
        this.set('props', props);
      },
      setPropsOnce: function(newp) {
        var props = this._state.props || {};
        _.coverExtend(props, newp);
        this.set('props', props);
      },
      clearAllProps: function() {
        this._sessionState = {};      
        for(var i in this._state.props){
          if(i.indexOf('latest_') !== 1){
            delete this._state.props[i];
          }
        }
        this.sessionSave({});
        this.save();
      },
    sessionSave: function(props) {
      this._sessionState = props;
      _.cookie.set('prophet2018session', JSON.stringify(this._sessionState), 0);
    },
    save: function() {
      _.cookie.set(this.getCookieName(), JSON.stringify(this._state), 73000, sd.para.cross_subdomain);
    },
    getCookieName: function(){
      var sub = '';
      if(sd.para.cross_subdomain === false){
        sub = _.url('sub',location.href);
        if(typeof sub === 'string' && sub !== ''){
          sub = 'sa_jssdk_2015_' + sub;
        }else{
          sub = 'sa_jssdk_2015_root';
        }
      }else{
        sub = 'prophet2018jssdkcross';
      } 
      return sub;
    },
    init: function() {
      // 如果不支持cookie，设置新的id，并且带有error_msg
      if (!navigator.cookieEnabled) {
        error_msg.push('cookieNotEnable');
        if (!_.localStorage.isSupport) {
          error_msg.push('localStorageNotEnable');
        }
      }

      this.initSessionState();
      var uuid = _.UUID();
      var cross = _.cookie.get(this.getCookieName());
      if (cross === null) {
        // null肯定是首次，非null，看是否有distinct_id
        is_first_visitor = true;
        
        just_test_distinctid = 1;
        
        this.set('distinct_id', uuid);
      } else {
        
        just_test_distinctid = 2;
        just_test_distinctid_detail = JSON.stringify(cross);
        just_test_distinctid_detail2 = JSON.stringify(document.cookie.match(/2015[^;]+/g));                                          

        if (!_.isJSONString(cross) || !(JSON.parse(cross)).distinct_id){
          is_first_visitor = true;
        }

        this.toState(cross);
      }


      // 如果没有跨域的cookie，且没有当前域cookie，那当前域的cookie和跨域cookie一致
        saNewUser.setDeviceId(uuid);

        //判断新用户
        saNewUser.storeInitCheck();
        saNewUser.checkIsFirstLatest();

      }
    };


    //todo 性能相关
    var project_performance = {
        init: function() {
            // 如果不支持window.performance.timing，error_msg
            if (!performance.timing) {
                error_msg.push('performance.timing');
                logger.info('浏览器不支持performance.timing');
            }else{
                if(this.getPageLoadTime() <= 0) {
                    // 未加载完，延迟200ms后继续times方法，直到成功
                    setTimeout(function(){
                        project_performance.init();
                    }, 200);

                    logger.info('浏览器支持performance.timing:'+'---NdsQuerySpeed---'+this.getNdsQuerySpeed());
                    return;
                }


            }
        },
        getNdsQuerySpeed:function(){
            if(!performance.timing){return ;}
            return performance.timing.domainLookupEnd - performance.timing.domainLookupStart;
        },
        getTCPConnSpeed:function () {
            if(!performance.timing){return ;}
            return performance.timing.connectEnd - performance.timing.connectStart;
        },
        getWhiteScreenTime:function () {
            if(!performance.timing){return ;}
            return performance.timing.responseStart - performance.timing.navigationStart;
        },
        getRtSpeed:function () {
            if(!performance.timing){return ;}
            return performance.timing.responseEnd - performance.timing.requestStart;
        },
        getPageLoadTime:function () {
            if(!performance.timing){return ;}
            return performance.timing.loadEventEnd - performance.timing.navigationStart;
        },



    };

  var commonWays = {
    isReady:function(callback){
      callback();
    },
    // 获取谷歌标准参数
    getUtm: function() {
      return _.info.campaignParams();
    },
    // 获取当前页面停留时间
    getStayTime: function() {
      return ((new Date()) - sd._t) / 1000;
    },

    //set init referrer
    setInitReferrer: function() {
      var _referrer = _.getReferrer();
      sd.setOnceProfile({
        _init_referrer: _referrer,
        _init_referrer_host: _.info.pageProp.referrer_host
      });
    },
    // set init sessionRegister cookie
    setSessionReferrer: function() {
      var _referrer = _.getReferrer();
      store.setSessionPropsOnce({
        _session_referrer: _referrer,
        _session_referrer_host: _.info.pageProp.referrer_host
      });
    },
    // set default referrr and pageurl
    setDefaultAttr: function() {
      _.info.register({
        _current_url: location.href,
        _referrer: _.getReferrer(),
        _referring_host: _.info.pageProp.referrer_host
      });
    },

    autoTrackSinglePage:function(para,callback){
      var url = _.info.pageProp.url;

      function getUtm(){    
         var utms = _.info.campaignParams();   
         var $utms = {};   
         for (var i in utms) {   
           if ((' ' + source_channel_standard + ' ').indexOf(' ' + i + ' ') !== -1) {    
             $utms['$' + i] = utms[i];   
           } else {    
             $utms[i] = utms[i];   
           }   
         }   
         return $utms;   
       }


      function closure(){
        sd.track('$pageview', _.extend({
            $referrer: url,
            $referrer_host: _.url('hostname',url) || '',
            $url: location.href,
            $url_path: location.pathname,
            $title: document.title,
            $RT:project_performance.getWhiteScreenTime()
          }, para, getUtm()),callback
        );
        url = location.href;
      }
      closure();
      this.autoTrackSinglePage = closure;
    },
    autoTrackWithoutProfile:function(para,callback){
      this.autoTrack(_.extend(para,{not_set_profile:true}),callback);
    },
    autoTrack: function(para, callback) {
      para = _.isObject(para) ? para : {};

      var utms = _.info.campaignParams();
      var $utms = {};
      for (var i in utms) {
        if ((' ' + source_channel_standard + ' ').indexOf(' ' + i + ' ') !== -1) {
          $utms['$' + i] = utms[i];
        } else {
          $utms[i] = utms[i];
        }
      }
      // setOnceProfile 如果是新用户，且允许设置profile
      if (is_first_visitor && !para.not_set_profile) {
        sd.setOnceProfile(_.extend({
            // 暂时隐藏，等extractor都部署上去 $first_landing_page: _.info.pageProp.url.slice(0, sd.para.max_referrer_string_length),
            $first_visit_time: new Date(),
            $first_referrer: _.getReferrer(),
            $first_browser_language: navigator.language || '取值异常',
            $first_browser_charset: document.charset || '取值异常',
            $first_referrer_host: _.info.pageProp.referrer_host,
            $first_traffic_source_type: _.getSourceFromReferrer(),
            $first_search_keyword: _.getKeywordFromReferrer(),
            $device_id:_.info.currentProps.$device_id

          }, $utms)
        );
      }
      if(para.not_set_profile){
        delete para.not_set_profile;
      }

      // 解决单页面的referrer问题
      var current_page_url = location.href;

      if(sd.para.is_single_page){
        _.addHashEvent(function(){
          var referrer = _.getReferrer(current_page_url);
          sd.track('$pageview', _.extend({
              $referrer: referrer,
              $referrer_host: _.url('hostname',referrer) || '',
              $url: location.href,
              $url_path: location.pathname,
              $title: document.title,
              $RT:project_performance.getWhiteScreenTime()
            }, $utms, para),callback
          );        
          current_page_url = location.href;
        });
      }
      
      sd.track('$pageview', _.extend({
          $referrer: _.getReferrer(),
          $referrer_host: _.info.pageProp.referrer_host,
          $url: location.href,
          $url_path: location.pathname,
          $title: document.title,
          $RT:project_performance.getWhiteScreenTime()
        }, $utms, para),callback
      );

    }


  };

  // 一些常见的方法
  sd.quick = function() {
    var arg = slice.call(arguments);
    var arg0 = arg[0];
    var arg1 = arg.slice(1);
    if (typeof arg0 === 'string' && commonWays[arg0]) {
      return commonWays[arg0].apply(commonWays, arg1);
    } else if (typeof arg0 === 'function') {
      arg0.apply(sd, arg1);
    } else {
      logger.info('quick方法中没有这个功能' + arg[0]);
    }
  };


  /*
   * @param {string} event
   * @param {string} properties
   * */
  sd.track = function(e, p, c) {
    if (saEvent.check({event: e, properties: p})) {
      saEvent.send({
        type: 'track',
        event: e,
        properties: p
      }, c);
    }
  };

  _.trackLink = function(obj,event_name,event_prop){
    obj = obj || {};
    var link = null;
    if(obj.ele){
      link = obj.ele;
    }
    if(obj.event){
      if(obj.target){
         link = obj.target;
       }else{
         link = obj.event.target;
       }
    }

    event_prop = event_prop || {};
    if(!link || (typeof link !== 'object')){
      return false;
    }
    // 如果是非当前页面会跳转的链接，直接track
    if (!link.href || /^javascript/.test(link.href) || link.target || link.download || link.onclick) {
      sd.track(event_name, event_prop);
      return false;
    }
    function linkFunc(e){
      e.stopPropagation();      
      e.preventDefault();   // 阻止默认跳转
      var hasCalled = false;
      function track_a_click(){
        if (!hasCalled) {
          hasCalled = true;
          location.href = link.href;  //把 A 链接的点击跳转,改成 location 的方式跳转
        }
      }
      setTimeout(track_a_click, 1000);  //如果没有回调成功，设置超时回调      
      sd.track(event_name, event_prop, track_a_click); //把跳转操作加在callback里
    }
    if(obj.event){
      linkFunc(obj.event);
    }
    if(obj.ele){
      _.addEvent(obj.ele,'click',function(e){
        linkFunc(e);
      });
    }
  };

  sd.trackLink = function(link,event_name,event_prop){
    if(typeof link === 'object' && link.tagName){
      _.trackLink({ele:link},event_name,event_prop);
    }else if(typeof link === 'object' && link.target && link.event){
      _.trackLink(link,event_name,event_prop);
    }
  };
    // 跟踪链接
  sd.trackLinks = function(link,event_name,event_prop){
    var ele = link;
    event_prop = event_prop || {};
    if(!link || (typeof link !== 'object')){
      return false;
    }
    if (!link.href || /^javascript/.test(link.href) || link.target) {
      return false;
    }
    _.addEvent(link,'click',function(e){
      e.preventDefault();   // 阻止默认跳转
      var hasCalled = false;
      setTimeout(track_a_click, 1000);  //如果没有回调成功，设置超时回调
      function track_a_click(){
        if (!hasCalled) {
          hasCalled = true;
          location.href = link.href;  //把 A 链接的点击跳转,改成 location 的方式跳转
        }
      }
      sd.track(event_name, event_prop, track_a_click); //把跳转操作加在callback里
    });

  };


  /*
   * @param {object} properties
   * */
  sd.setProfile = function(p, c) {
    if (saEvent.check({propertiesMust: p})) {
      saEvent.send({
        type: 'profile_set',
        properties: p
      }, c);
    }
  };

  sd.setOnceProfile = function(p, c) {
    if (saEvent.check({propertiesMust: p})) {
      saEvent.send({
        type: 'profile_set_once',
        properties: p
      }, c);
    }
  };

  /*
   * @param {object} properties
   * */
  sd.appendProfile = function(p, c) {
    if (saEvent.check({propertiesMust: p})) {
      _.each(p, function(value, key) {
        if (_.isString(value)) {
          p[key] = [value];
        } else if (_.isArray(value)) {

        } else {
          delete p[key];
          logger.info('appendProfile属性的值必须是字符串或者数组');
        }
      });
      if (!_.isEmptyObject(p)) {
        saEvent.send({
          type: 'profile_append',
          properties: p
        }, c);
      }
    }
  };
  /*
   * @param {object} properties
   * */
  sd.incrementProfile = function(p, c) {
    var str = p;
    if (_.isString(p)) {
      p = {}
      p[str] = 1;
    }
    function isChecked(p) {
      for (var i in p) {
        if (!/-*\d+/.test(String(p[i]))) {
          return false;
        }
      }
      return true;
    }

    if (saEvent.check({propertiesMust: p})) {
      if (isChecked(p)) {
        saEvent.send({
          type: 'profile_increment',
          properties: p
        }, c);
      } else {
        logger.info('profile_increment的值只能是数字');
      }
    }
  };

  sd.deleteProfile = function(c) {
    saEvent.send({
      type: 'profile_delete'
    }, c);
    store.set('distinct_id', _.UUID());
  };
  /*
   * @param {object} properties
   * */
  sd.unsetProfile = function(p, c) {
    var str = p;
    var temp = {};
    if (_.isString(p)) {
      p = [];
      p.push(str);
    }
    if (_.isArray(p)) {
      _.each(p, function(v) {
        if (_.isString(v)) {
          temp[v] = true;
        } else {
          logger.info('profile_unset给的数组里面的值必须时string,已经过滤掉', v);
        }
      });
      saEvent.send({
        type: 'profile_unset',
        properties: temp
      }, c);
    } else {
      logger.info('profile_unset的参数是数组');
    }
  };
  /*
   * @param {string} distinct_id
   * */
  sd.identify = function(id, isSave) {
    if(typeof id === 'number'){
      id = String(id);
    }
    var firstId = store.getFirstId();
    if (typeof id === 'undefined') {
      if(firstId){      
        store.set('first_id', _.UUID());
      }else{
        store.set('distinct_id', _.UUID());
      }
    } else if (saEvent.check({distinct_id: id})) {
      if (isSave === true) {
        if(firstId){
          store.set('first_id', id);
        }else{
          store.set('distinct_id', id);          
        }
      } else {
        if(firstId){
          store.change('first_id', id);
        }else{
          store.change('distinct_id', id);
        }
      }

    } else {
      logger.info('identify的参数必须是字符串');
    }
  };
  /*
   * 这个接口是一个较为复杂的功能，请在使用前先阅读相关说明:http://www.sensorsdata.cn/manual/track_signup.html，并在必要时联系我们的技术支持人员。
   * @param {string} distinct_id
   * @param {string} event
   * @param {object} properties
   * */
  sd.trackSignup = function(id, e, p, c) {
    if (saEvent.check({distinct_id: id, event: e, properties: p})) {
      saEvent.send({
        original_id: store.getFirstId() || store.getDistinctId(),
        distinct_id: id,
        type: 'track_signup',
        event: e,
        properties: p
      }, c);
      store.set('distinct_id', id);
    }
  };


  sd.registerPage = function(obj) {
    if (saEvent.check({properties: obj})) {
      _.extend(_.info.currentProps, obj);
    } else {
      logger.info('register输入的参数有误');
    }
  };

  sd.clearAllRegister = function(){
    store.clearAllProps();
  };

  sd.register = function(props) {
    if (saEvent.check({properties: props})) {
      store.setProps(props);
    } else {
      logger.info('register输入的参数有误');
    }
  };

  sd.registerOnce = function(props) {
    if (saEvent.check({properties: props})) {
      store.setPropsOnce(props);
    } else {
      logger.info('registerOnce输入的参数有误');
    }
  };

  sd.registerSession = function(props) {
    if (saEvent.check({properties: props})) {
      store.setSessionProps(props);
    } else {
      logger.info('registerSession输入的参数有误');
    }
  };

  sd.registerSessionOnce = function(props) {
    if (saEvent.check({properties: props})) {
      store.setSessionPropsOnce(props);
    } else {
      logger.info('registerSessionOnce输入的参数有误');
    }
  };

  sd.login = function(id){
    if(typeof id === 'number'){
      id = String(id);
    }
    if (saEvent.check({distinct_id: id})) {
      var firstId = store.getFirstId();
      var distinctId = store.getDistinctId();
      if(id !== distinctId){
        if(firstId){
          sd.trackSignup(id,'$SignUp');
        }else{
          store.set('first_id',distinctId);
          sd.trackSignup(id,'$SignUp');
        }
        //TODO:添加sessionID  每次 登录时生成，登出是删除
          if(_.cookie.get("prophet_session_id")|| _.cookie.get("easr_session_id")=='' || _.cookie.get("easr_session_id")==null){
              _.cookie.set("prophet_session_id",_.UUID());
          }
      }
    } else {
      logger.info('login的参数必须是字符串');
    }
  };

  sd.logout = function(isChangeId){
    var firstId = store.getFirstId();
    if(firstId){
      store.set('first_id','');
      if(isChangeId === true){
        store.set('distinct_id',_.UUID());
      }else{
        store.set('distinct_id',firstId);
      }
    }else{
      logger.info('没有first_id，logout失败');
    }
      _.cookie.set("prophet_session_id",null);
  };




    
  sd.init = function(para){
    if((!para && has_declare) || (para && !has_declare)){
      sd.initPara(para);
      _.info.initPage();
      store.init();
      project_performance.init();
      //window.onload= project_performance.init();
      //sd._init();
    }
  };





    sd.init();
  
  
  return sd;
  
}catch(err){
  if (typeof console === 'object' && console.log) {
    try {console.log(err)} catch (e) {};
  }

}



});