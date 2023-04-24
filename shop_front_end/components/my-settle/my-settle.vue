<template>
	<view class="my-settle-container">
		<!-- 全选 -->
		<label class="radio" @click="changeAllState">
			<radio color="#c00000" :checked="isFullcheck" /><text>全选</text>
		</label>


		<!-- 合计 -->
		<view class="amount-box">
			合计:<text class="amount">${{checkedGoodsAmount}}</text>
		</view>

		<!-- 结算 -->
		<view class="btn-settle" @click="settlement">结算{{checkedCount}}</view>
	</view>
</template>

<script>
	import {
		mapGetters,
		mapMutations,
		mapState
	} from 'vuex'

	export default {
		name: "my-settle",
		data() {
			return {
				//倒计时的秒数
				seconds: 3,
				//定时器的ID
				timer: null
			};
		},
		computed: {
			...mapGetters('m_cart', ['checkedCount', 'total', 'checkedGoodsAmount']),
			...mapGetters('m_user', ['addstr']),
			...mapState('m_user', ['token', 'userinfo', "address"]),
			...mapState('m_cart', ['cart']),
			isFullcheck() {
				return this.total === this.checkedCount
			}
		},
		methods: {
			...mapMutations('m_cart', ['updateAllGoodsState', 'addToCart', "updateGoodsState"]),
			...mapMutations('m_user', ['updateRedirectInfo', 'updateAddress']),
			changeAllState() {
				this.updateAllGoodsState(!this.isFullcheck)
			},
			// 用户点击了结算按钮
			settlement() {
				if (!this.checkedCount) return uni.$showMsg('请选择要结算的商品！')
				if (!this.addstr) return uni.$showMsg('请选择收货地址！')
				// if (!this.token) return uni.$showMsg('请先登录！')
				if (!this.token) return this.delayNavigate()

				this.payOrder()
			},
			// 微信支付
			async payOrder() {
				// 1. 创建订单
				// 1.1 组织订单的信息对象
				const orderInfo = {
					goods: this.cart.filter(x => x.goods_state).map(x => ({
						order_name: this.userinfo.nickName,
						goods_id: x.goods_id,
						goods_name: x.goodsName,
						goods_small_logo:x.goodsSmallLogo,
						goods_number: x.goods_count,
						goods_price: x.goodsPrice
					}))
				}
				// 1.2 发起请求创建订单
				for (let i = 0; i < orderInfo.goods.length; i++) {
					const res = await this.$myRequest({
						url: '/order/add',
						method: 'POST',
						data: {
							consigneeAddr: this.addstr,
							consignee: this.address.userName,
							orderPhone: this.address.telNumber,
							orderPrice: orderInfo.goods[i].goods_price,
							amount: orderInfo.goods[i].goods_number,
							goodsName: orderInfo.goods[i].goods_name,
							goodsSmallLogo:orderInfo.goods[i].goods_small_logo
						}
					})
					if (res.statusCode === 200) {
						uni.$showMsg("下单成功")
					} else {
						//请求失败
						uni.$showMsg()
					}
					// 下单完成后清空购物车和地址信息
					// this.addToCart('')
					// this.updateAddress({})
				}
			},
			// 延时导航到my页面
			delayNavigate() {
				this.seconds = 3

				this.showTip(this.seconds)
				this.timer = setInterval(() => {
					this.seconds--

					if (this.seconds <= 0) {
						clearInterval(this.timer)

						uni.switchTab({
							url: '/pages/my/my',
							success: () => {
								this.updateRedirectInfo({
									openType: 'switchTab',
									from: '/pages/cart/cart'
								})
							}
						})
						return
					}
					this.showTip(this.seconds)
				}, 1000)
			},
			//展示倒计时的提示信息
			showTip(n) {
				uni.showToast({
					icon: 'none',
					title: '请登录后再结算！' + n + '秒之后自动跳转到登页',
					mask: true,
					duration: 1500,
				})
			}
		}
	}
</script>

<style lang="scss">
	.my-settle-container {
		position: fixed;
		bottom: 0;
		left: 0;
		width: 100%;
		height: 50px;
		background-color: white;
		display: flex;
		justify-content: space-between;
		align-items: center;
		font-size: 14px;
		padding-left: 5px;

		.radio {
			display: flex;
			align-items: center;
		}

		.amount-box {
			.amount {
				color: #c00000;
				font-weight: bold;
			}
		}

		.btn-settle {
			background-color: #c00000;
			height: 50px;
			color: white;
			line-height: 50px;
			padding: 0 10px;
			min-width: 100px;
			text-align: center;
		}

	}
</style>
