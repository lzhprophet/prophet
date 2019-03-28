import instance from '../index';
import qs from 'qs'

const preUrlPath = '/api';
//SSO CAS登录API
const SSO_CAS_URI = 'http://10.100.131.206:8081/cas/api/v2/login';

//SSO登录
const login = {
  p: [],
  r: params => {
    let ssoParams = {
      identity: params.username,
      password: params.password,
      tenancyCode: "DIANRONG"
    };
    return instance.post(
      SSO_CAS_URI,
      qs.stringify(ssoParams),
      {
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
      }
    )
  }
};
//同步SSO
const identification = {
  p: [],
  r: () => {
    window.location.href=`${preUrlPath}/sys/sso/identification`;
  }
};
//SSO获取用户信息
const requestUser = {
  p: [],
  r: () => {
    return instance.get(`${preUrlPath}/sys/sso`)
  }
};

export {
  login,
  identification,
  requestUser
  //ssoRequestUser
}
