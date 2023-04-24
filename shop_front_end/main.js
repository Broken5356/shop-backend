// #ifndef VUE3
import Vue from 'vue'
import App from './App'
import store from '@/store/store.js'
import { myRequest } from './util/api.js'

// //导入网络请求的包
// import {
// 	$http
// } from '@escook/request-miniprogram'

// uni.$http = $http

Vue.prototype.$myRequest = myRequest


//请求的根路径
// $http.baseUrl = 'http://127.0.0.1:8088'

// //请求拦截器
// $myRequest.beforeRequest = function(options) {
// 	uni.showLoading({
// 		title: '数据加载中...'
// 	})

// 	//判断当前请求的是否为有权限的接口
// 	if (options.url.indexOf('/my/') !== -1) {
// 		options.header = {
// 			Authorization: store.state.m_user.token
// 		}
// 	}

// }

// //响应拦截器
// $myRequest.afterRequest = function() {
// 	uni.hideLoading()
// }

// 封装弹框的方法
uni.$showMsg = function(title = '数据请求失败！', duration = 1500) {
	uni.showToast({
		title,
		duration,
		icon: 'none'
	})
}


Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
	...App,
	store
})
app.$mount()

// #endif

// #ifdef VUE3
import {
	createSSRApp
} from 'vue'
import App from './App.vue'
export function createApp() {
	const app = createSSRApp(App)
	return {
		app
	}
}
// #endif
