<template>
	<div class="login-wrapper">
		<div class="shading">
			<image src="/static/images//avator2.webp" />
		</div>
		<div class="whiteBg" v-if="formItem === 1">
			<div class="list" v-if="current !== 1">
				<form @submit.prevent="submit">
					<div class="item">
						<div class="row-middle">
							<image src="/static/images/phone_1.png" style="width: 24rpx; height: 34rpx;"></image>
							<input type="text" class="texts" placeholder="输入手机号码" v-model="account" required />
						</div>
					</div>
					<div class="item">
						<div class="row-middle">
							<image src="/static/images/code_2.png" style="width: 28rpx; height: 32rpx;"></image>
							<input type="password" class="texts" placeholder="填写登录密码" v-model="password" required />
						</div>
					</div>
				</form>
			</div>

			<div class="list" v-if="current !== 0 ">
				<div class="item">
					<div class="row-middle">
						<image src="/static/images/phone_1.png" style="width: 24rpx; height: 34rpx;"></image>
						<input type="text" class="texts" placeholder="输入手机号码" v-model="account" />
					</div>
				</div>
				<div class="item">
					<div class="row-middle">
						<image src="/static/images/code_2.png" style="width: 28rpx; height: 32rpx;"></image>
						<input type="text" placeholder="填写验证码" class="codeIput" v-model="captcha" />
						<button class="code" :disabled="disabled" :class="disabled === true ? 'on' : ''" @click="code">
							{{ text }}
						</button>
					</div>
				</div>
				<div class="item" v-if="isShowCode">
					<div class="row-middle">
						<image src="/static/images/code_2.png" style="width: 28rpx; height: 32rpx;"></image>
						<input type="text" placeholder="填写验证码" class="codeIput" v-model="codeVal" />
						<div class="code" @click="again"><img :src="codeUrl" /></div>
					</div>
				</div>
			</div>

			<div class="logon" @click="loginMobile" v-if="current !== 0">登录</div>
			<div class="logon" @click="submit" v-if="current === 0">登录</div>
			<div class="tips">
				<div v-if="current==0" @click="current = 1">快速登录</div>
				<div v-if="current==1" @click="current = 0">账号登录</div>
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
				//验证码
				captcha: '',
				//表单
				formItem: 1,
				//当前登录方式
				current: 1,
				codeVal: '',
				role: 'user',
				//用户信息
				userInfo: [],
				//倒计时的秒数
				seconds: 3
			};
		},
		computed: {
			...mapState('m_user',['redirectInfo'])
		},
		methods: {
			...mapMutations('m_user', ['updateUserInfo', 'updateToken', 'updateRedirectInfo']),
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
				console.log(res);
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
			}
		}
	}
</style>
