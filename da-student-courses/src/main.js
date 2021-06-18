import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './axios'
import store from './store'
import VueCookies from 'vue3-cookies'

axios.defaults.withCredentials = true


// axios.interceptors.response.use(function(res) {
// 	var res = res.data;
// 	if(res.status === 403 ) {
// 		router.push('/')
// 		return res;
// 	}
// 	return res;
// }, function(error) {
// 	return Promise.reject(error);
// });
// axios.defaults.withCredentials = true; //意思是携带cookie信息,保持session的一致性
// Vue.prototype.$axios = axios
// Vue.prototype.stringify = require('qs').stringify;
// Vue.config.productionTip = false


createApp(App).use(store).use(router).use(VueCookies).mount('#app')


