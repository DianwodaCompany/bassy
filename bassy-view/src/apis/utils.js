import axios from 'axios';
import { message } from 'antd';

axios.interceptors.response.use(function (response) {
  return response
}, function(error) {
    //TODO
    /**
  const isLocal = window.location.href.includes('localhost')
  if ((error.toString()).includes('Network Error') && !isLocal) {
    var url = JSON.parse(localStorage.getItem('common')).logoutUrl
    window.location.href = url
  }
  if ((error.toString()).includes('400') && !isLocal) {
    window.location.href = '/'
  }
  */
})

/**
 * @param {String} method: post or get
 * @param {String} url : api
 * @param {Object} params: post参数
 */
export async function http (url, method = 'get', params) {
  const { data } = await axios({
    method,
    url,
    headers: {
      'Content-Type': 'application/json',
    },
    data: params,
  });
  if (data.errCode !== 1) {
    message.error(data.msg);
  }
  return data;
};

export async function httpUpload (url, method = 'get', params) {
    const { data } = await axios({
        method,
        url,
        headers: {
            'Content-Type': 'multipart/form-data'
        },
        data: params,
    });
    if (data.errCode !== 1) {
        message.error(data.msg);
    }
    return data;
};

export async function httpParams (url, method = 'get', params) {
  const { data } = await axios({
    method,
    url,
    // headers: {
    //   'Content-Type': 'application/x-www-form-urlencoded'
    // },
    params,
  });
  if (data.errCode !== 1) {
    message.error(data.msg);
  }
  return data;
};



