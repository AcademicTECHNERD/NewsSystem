import axios from 'axios';
import { ElMessage } from 'element-plus';
import { useTokenStore } from '@/stores/token.js';
import router from '@/router';

const baseURL = '/api';
const instance = axios.create({ baseURL });

instance.interceptors.request.use(
  (config) => {
    const tokenStore = useTokenStore();
    if (tokenStore.token) {
      config.headers.Authorization = tokenStore.token;
      console.log("Token:", tokenStore.token);
    }
    return config;
  },
  (err) => {
    return Promise.reject(err);
  }
);

instance.interceptors.response.use(
  (result) => {
    console.log("Request Headers:", result.config.headers);
    if (result.data.code === 0) {
      return result.data;
    }
    ElMessage.error(result.data.msg ? result.data.msg : '服务异常');
    return Promise.reject(result.data);
  },
  (err) => {
    if (err.response && err.response.status === 401) {
      ElMessage.error('请先登录');
      router.push('/login');
    } else {
      ElMessage.error('服务异常');
    }
    return Promise.reject(err);
  }
);

export default instance;
