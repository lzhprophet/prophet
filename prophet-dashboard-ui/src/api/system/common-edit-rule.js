//验证以为小数
const validateNumber = (rule, value, callback) => {
  var patt = /^\d+(?:\.\d{1,1})?$/;
  // debugger
  if (String(value).length == 0) {
    callback(new Error('不能为空'));
  } else if (!patt.test(value)) {
    callback(new Error('只能是整数或一位小数'));
  } else {
    callback();
  }
};
//验证正整数
const validateInteger = (rule, value, callback) => {
  var patt = /^([1-9][0-9]*)$/;
  if (value == '') {
    callback(new Error('不能为空'));
  } else if (!patt.test(value)) {
    callback(new Error('只能是整数'));
  } else {
    callback();
  }
};
//验证长度
const validateStr = (rule, value, callback) => {

  if (value == '' || value == null) {
    callback(new Error('不能为空'));
    return;
  }

  var cArr = value.match(/[^\x00-\xff]/ig);
  let length = value.length + (cArr == null ? 0 : cArr.length);

  if (length > 200) {
    callback(new Error('字符串超长'));
  } else {
    callback();
  }
};
//验证长度
const validateStr60 = (rule, value, callback) => {
  var cArr = value.match(/[^\x00-\xff]/ig);
  let length = value.length + (cArr == null ? 0 : cArr.length);

  if (value == '') {
    callback(new Error('不能为空'));
  } else if (length > 60) {
    callback(new Error('字符串超长'));
  } else {
    callback();
  }
};


export {
  validateNumber,
  validateInteger,
  validateStr,
  validateStr60
}
