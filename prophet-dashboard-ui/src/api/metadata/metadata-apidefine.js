import instance from '../index';

const preUrlPath = '/api';

//查询
const list = {
  r: params => {
    return instance.get(`${preUrlPath}/meta/apidefine/list`, {params:params})
  }

};



export {
  list
}
