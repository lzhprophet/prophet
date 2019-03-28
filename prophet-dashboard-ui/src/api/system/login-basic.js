import instance from '../index';

const preUrlPath = '/api';

//查询
const login = {
  r: params => {
    return instance.get(`${preUrlPath}/login`, {params: params})
  }
};

//同步
const identification = {
  p: [],
  r: () => {
    window.location.href=`${preUrlPath}/login/identification`;
  }
};
//获取用户信息
const requestUser = {
  p: [],
  r: () => {
    return instance.get(`${preUrlPath}/login/currentuser`)
  }
};

export {
  login,
  identification,
  requestUser
}
