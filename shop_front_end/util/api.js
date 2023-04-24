// 异步请求接口封装
const base_url = 'http://localhost:8088'
export const myRequest = (options) => {
	return new Promise((resolve, reject) => {
		uni.request({
			url: base_url + options.url,
			method: options.method || 'GET',
			data: options.data || {},
			success: (res) => {
				if (res.data.code !== 200) {
					return uni.showToast({
						title: '获取数据失败'
					})
				}
				resolve(res)
			},
			fail: (err) => {
				uni.showToast({
					title: '请求接口失败'
				})
				reject(err)
			},
		})
	})
}
