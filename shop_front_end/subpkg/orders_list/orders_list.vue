<template>
	<view>
		<view class="tab-bar">
			<view class="tab-item" :class="{ active: activeTab === 0 }" @click="switchTab(0)">全部</view>
			<view class="tab-item" :class="{ active: activeTab === 1 }" @click="switchTab(1)">待付款</view>
			<view class="tab-item" :class="{ active: activeTab === 2 }" @click="switchTab(2)">待收货</view>
			<view class="tab-item" :class="{ active: activeTab === 3 }" @click="switchTab(3)">已完成</view>
			<view class="tab-item" :class="{ active: activeTab === 4 }" @click="switchTab(4)">已取消</view>
		</view>
		<view v-show="activeTab === 0 " v-for="(item ,i) in orderList" :key="i">
			<!-- 显示全部订单内容 -->
			<view class="order-detail">
				<view class="order-header">
					<text class="order-number">订单编号：{{item.id}}</text>
					<text class="order-status">{{item.state===0?"等待发货":"交易成功"}}</text>
				</view>

				<view class="order-item">
					<image class="item-image" :src="item.goodsSmallLogo"></image>
					<view class="item-detail">
						<text class="item-name">{{item.goodsName}}</text><br>
						<text class="item-price">￥{{item.orderPrice}}</text>
					</view>
					<text class="item-quantity">X{{item.amount}}</text>
				</view>
				<view class="order-footer">
					<text class="order-amount">已付金额：￥{{item.price}}</text>
					<view class="btn-group">
						<button class="btn-view-logistics">查看物流</button>
						<button class="btn-confirm-receipt">确认收货</button>
					</view>
				</view>
			</view>
		</view>

		<view v-show="activeTab === 2 && item.state===0" v-for="(item ,i) in orderList" :key="i">
			<!-- 显示全部订单内容 -->
			<view class="order-detail">
				<view class="order-header">
					<text class="order-number">订单编号：{{item.id}}</text>
					<text class="order-status">等待发货</text>
				</view>

				<view class="order-item">
					<image class="item-image" :src="item.goodsSmallLogo"></image>
					<view class="item-detail">
						<text class="item-name">{{item.goodsName}}</text><br>
						<text class="item-price">￥{{item.orderPrice}}</text>
					</view>
					<text class="item-quantity">X{{item.amount}}</text>
				</view>
				<view class="order-footer">
					<text class="order-amount">已付金额：￥{{item.price}}</text>
					<view class="btn-group">
						<button class="btn-view-logistics">查看物流</button>
						<button class="btn-confirm-receipt">确认收货</button>
					</view>
				</view>
			</view>
		</view>

		<view v-show="activeTab === 3 && item.state===1" v-for="(item ,i) in orderList" :key="i">
			<!-- 显示全部订单内容 -->
			<view class="order-detail">
				<view class="order-header">
					<text class="order-number">订单编号：{{item.id}}</text>
					<text class="order-status">交易成功</text>
				</view>

				<view class="order-item">
					<image class="item-image" :src="item.goodsSmallLogo"></image>
					<view class="item-detail">
						<text class="item-name">{{item.goodsName}}</text><br>
						<text class="item-price">￥{{item.orderPrice}}</text>
					</view>
					<text class="item-quantity">X{{item.amount}}</text>
				</view>
				<view class="order-footer">
					<text class="order-amount">已付金额：￥{{item.price}}</text>
					<view class="btn-group">
						<button class="btn-view-logistics">评价商品</button>
						<button class="btn-confirm-receipt">再次购买</button>
					</view>
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				activeTab: 0, // 默认显示全部订单
				orderList: [],
			};
		},
		methods: {
			// 定义 switchTab 函数
			switchTab(index) {
				this.activeTab = index
			},
			onLoad() {
				this.getOrderList()
			},
			// 获取分类列表的数据
			async getOrderList() {
				const res = await this.$myRequest({
					url: '/order/getOrderList',
				})
				//请求失败
				if (res.statusCode !== 200) return uni.$showMsg()
				this.orderList = res.data.data
			}
		},
	}
</script>

<style lang="scss">
	.tab-bar {
		display: flex;
		justify-content: space-between;
		padding: 20rpx;
		background-color: #f2f2f2;
	}

	.tab-item {
		font-size: 28rpx;
		color: #666666;
	}

	.tab-item.active {
		color: #ff634d;
		font-weight: bold;
	}

	.order-detail {
		padding: 20rpx;
		border: 1rpx solid #d9d9d9;
		margin-top: 20rpx;
	}

	.order-header {
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin-bottom: 20rpx;
	}

	.order-number {
		font-size: 24rpx;
		color: #666666;
	}

	.order-status {
		font-size: 24rpx;
		color: #ff634d;
	}

	.order-item {
		display: flex;
		align-items: center;
		margin-bottom: 20rpx;
	}

	.item-image {
		width: 200rpx;
		height: 200rpx;
		margin-right: 20rpx;
	}

	.item-detail {
		flex: 1;
	}

	.item-name {
		font-size: 26rpx;
		color: #333333;
		margin-bottom: 10rpx;
	}

	.item-spec {
		font-size: 24rpx;
		color: #999999;
		margin-bottom: 10rpx;
	}

	.item-price {
		font-size: 24rpx;
		color: #ff634d;
	}

	.item-quantity {
		font-size: 28rpx;
		color: #666666;
		margin-left: auto;
	}

	.order-footer {
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin-top: 20rpx;
	}

	.order-amount {
		font-size: 28rpx;
		color: #666666;
	}

	.btn-group {
		display: flex;
		align-items: center;
	}

	.btn-view-logistics {
		font-size: 24rpx;
		color: #666666;
		margin-right: 20rpx;
	}

	.btn-confirm-receipt {
		font-size: 24rpx;
		color: #ff634d;
	}
</style>
