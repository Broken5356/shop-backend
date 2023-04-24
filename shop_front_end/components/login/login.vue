<template>
	<div class="login-wrapper">
		<div class="shading">
			<image src="/static/images//avator2.webp" class="avatar" />
		</div>
		<div class="whiteBg">
			<div class="list">
				<div class="item">
					<div class="row-middle">
						<image src="/static/images/phone_1.png" style="width: 24rpx; height: 34rpx;"></image>
						<input type="text" class="texts" placeholder="输入登录账户" v-model="account" required />
					</div>
				</div>
				<div class="item">
					<div class="row-middle">
						<image src="/static/images/code_2.png" style="width: 28rpx; height: 32rpx;"></image>
						<input type="password" class="texts" placeholder="填写登录密码" v-model="password" required />
					</div>
				</div>
			</div>
			<div class="logon" @click="submit">账户登录</div>
			<div class="tips">
				<button open-type="getUserInfo" @getuserinfo="getUserInfo">一键登录</button>
			</div>
		</div>
	</div>
</template>

<script>
	import {
		mapMutations,
		mapState
	} from 'vuex'
	export default {
		name: "login",

		data() {
			return {
				//用户名
				account: '',
				//密码
				password: '',
				role: 'user',
				//用户信息
				userInfo: [],
			};
		},
		computed: {
			...mapState('m_user', ['redirectInfo'])
		},
		methods: {
			...mapMutations('m_user', ['updateUserInfo', 'updateToken', 'updateRedirectInfo']),
			//登录
			async submit() {
				if (!this.account) return uni.$showMsg('请填写账号')
				if (!/^[\w\d]{5,16}$/i.test(this.account)) return uni.$showMsg('请输入正确的账号')
				if (!this.password) return uni.$showMsg('请填写密码')

				const res = await this.$myRequest({
					url: '/login/doLogin',
					method: 'POST',
					data: {
						account: this.account,
						password: this.password,
						role: this.role
					}
				})
				//请求失败
				if (res.statusCode != 200) return uni.$showMsg()
				this.updateUserInfo(res.data.data)
				this.updateToken(this.role)
				this.navigateBack()
			},
			navigateBack() {
				if (this.redirectInfo && this.redirectInfo.openType === 'switchTab') {
					uni.switchTab({
						url: this.redirectInfo.from,
						complete: () => {
							this.updateRedirectInfo(null)
						}
					})
				}
			},
			// 用户授权之后，获取用户基本信息
			getUserInfo(e) {
				if (e.detail.errMsg === 'getUserInfo:fail auth deny') return uni.$showMsg('您取消了登录授权')
				//将用户的基本信息存储到vuex中
				this.updateUserInfo(e.detail.userInfo)
				//获取登录成功后的Token字符串
				this.getToken(e.detail)
			},
			async getToken(info) {
				// 获取code对应的值
				const [err, res] = await uni.login().catch(err => err)
				if (err || res.errMsg !== 'login:ok') return uni.$showMsg('登录失败！')
				// 准备参数对象
				const query = {
					code: res.code,
					encryptedData: info.encryptedData,
					iv: info.iv,
					rawData: info.rawData,
					signature: info.signature
				}
				this.updateToken(query.code)
				this.navigateBack()
			}
		}
	}
</script>

<style lang="scss" scoped>
	page {
		background: #fff;
	}

	.login-wrapper {
		.shading {
			display: flex;
			align-items: center;
			justify-content: center;
			width: 100%;
			padding-top: 150rpx;

			image {
				width: 180rpx;
				height: 180rpx;
			}

			.avatar {
				width: 110px;
				height: 110px;
				border-radius: 60px;
				border: 2px solid white;
				box-shadow: 0 1px 5px black;
			}
		}

		.whiteBg {
			margin-top: 100rpx;

			.list {
				border-radius: 16rpx;
				overflow: hidden;

				.item {
					border-bottom: 1px solid #F0F0F0;
					background: #fff;

					.row-middle {
						position: relative;
						padding: 0rpx 48rpx;
						display: flex;
						justify-content: center;
						align-items: center;

						image {
							margin-top: 10rpx;
						}

						.texts {
							font-size: 28rpx;
							height: 120rpx;
							line-height: 120rpx;
							display: flex;
							justify-content: center;
							align-items: center;
						}

						input {
							flex: 1;
							font-size: 28rpx;
							height: 120rpx;
							line-height: 120rpx;
							display: flex;
							justify-content: center;
							align-items: center;
							margin-right: 260rpx;
						}

						.code {
							position: absolute;
							right: 30rpx;
							top: 50%;
							color: #e93323;
							font-size: 26rpx;
							transform: translateY(-50%);
						}
					}
				}
			}

			.logon {
				display: flex;
				align-items: center;
				justify-content: center;
				width: 100%;
				height: 86rpx;
				margin-top: 80rpx;
				background-color: #c00000;
				border-radius: 120rpx;
				color: #FFFFFF;
				font-size: 30rpx;
			}

			.tips {
				margin: 30rpx;
				text-align: center;
				color: #999;

				button {
					width: 100%;
					background-color: #f5f5f5;
					border-radius: 120rpx;
				}
			}
		}
	}
</style>
