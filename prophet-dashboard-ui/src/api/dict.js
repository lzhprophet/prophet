import instance from './index';
import Axios from 'axios';

const preUrlPath = '/api';



//产品字典表
const dict = {
  product: params => {
    return instance.get(`${preUrlPath}/meta/product/dict`, {});
  },
  product1: params => {
    return instance.get(`${preUrlPath}/meta/product/dict`, {});
  }
};







export {
  dict
}
