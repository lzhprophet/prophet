import instance from '../index';

const preUrlPath = '/api';

//bashborad数据查询
const accressMode = {
  r: params => {
    return instance.get(`${preUrlPath}/dashboard/accress-mode`, params)
  }
};
//bashborad数据查询
const countInfo = {
  r: params => {
    return instance.get(`${preUrlPath}/dashboard/count`, params)
  }
};

//bashborad数据查询 一周api接口调用量
const apiWeekCount = {
  r: params => {
    return instance.get(`${preUrlPath}/dashboard/api-week-count`, params)
  }
};





export {
  accressMode,
  apiWeekCount,
  countInfo
}
