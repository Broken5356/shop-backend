export default {
	// 开启命名空间
	namespaced: true,

	// 数据， state 对象是响应式的，也就是说当数据发生变化时，相关的组件也会自动更新
	state: () => ({
		address: JSON.parse(uni.getStorageSync('address') || '{}'),
		token: uni.getStorageSync('token') || '',
		// 用户的信息对象
		userinfo: JSON.parse(uni.getStorageSync('userinfo') || '{}'),
		//重定向的Object对象
		redirectInfo: null
	}),

	// 方法
	mutations: {
		// 更新收货地址
		updateAddress(state, address) {
			state.address = address
			this.commit('m_user/saveAdressToStorage')
		},
		//持久化存储address
		saveAdressToStorage(state) {
			uni.setStorageSync('address', JSON.stringify(state.address))
		},
		
		//更新用户信息
		updateUserInfo(state, userinfo) {
			state.userinfo = userinfo
			this.commit('m_user/saveInfoToStorage')
		},
		saveInfoToStorage(state) {
			uni.setStorageSync('userinfo', JSON.stringify(state.userinfo))
		},
		
		//更新用户令牌
		updateToken(state, token) {
			state.token = token
			this.commit('m_user/saveTokenToStorage')
		},
		saveTokenToStorage(state) {
			uni.setStorageSync('token', state.token)
		},
		
		//更新重定向信息对象
		updateRedirectInfo(state, info) {
			state.redirectInfo = info
		}
	},


	getters: {
		addstr(state) {
			if (!state.address.provinceName) return ''

			return state.address.provinceName + state.address.cityName + state.address.countyName + state.address
				.detailInfo
		}
	}

}
