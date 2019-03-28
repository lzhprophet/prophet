import instance from './index';

const preUrlPath = '/api';

//查询
const request = {
  p: ['sys:user:list'],
  r: params => {
    return instance.post(`${preUrlPath}/sys/user/list`, params)
  }
};
//新增
const create = {
  p: ['sys:user:save'],
  r: params => {
    return instance.post(`${preUrlPath}/sys/user/save`, params)
  }
};
//更新
const update = {
  p: ['sys:user:update'],
  r: params => {
    return instance.put(`${preUrlPath}/sys/user/update`, params)
  }
};
//刪除
const remove = {
  p: ['sys:user:delete'],
  r: params => {
    return instance.delete(`${preUrlPath}/sys/user/delete/${params.id}`)
  }
};

export {
  request,
  create,
  remove,
  update
}
