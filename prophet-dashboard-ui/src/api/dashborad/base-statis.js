import instance from '../index';

const preUrlPath = '/api';



//查询按接入产品
const countInfoByPcode = {
  info: params => {
    return instance.get(`${preUrlPath}/basestatis/count-by-pcode`, {params:params})
  }
};

//最大、平均值统计按项目
const maxAvgByPcode = {
  info: params => {
    return instance.get(`${preUrlPath}/basestatis/maxavg-by-pcode`, {params:params})
  }
};



//查询
const timeSloatBasestaticInfo = {
  list: params => {
    return instance.get(`${preUrlPath}/basestatis/base-statis-info-timesole`, {params:params})
  }
};




export {
  countInfoByPcode,
  maxAvgByPcode,
  timeSloatBasestaticInfo
}
