import instance from '../index';

const preUrlPath = '/api';

//查询
const list = {
  r: params => {
    return instance.get(`${preUrlPath}/meta/product/list`, params)
  }

};
//新增
const save = {
  r: params => {
    debugger
    return instance.post(`${preUrlPath}/meta/product/save`, params)
  }
};




export {
  list,
  save
}
