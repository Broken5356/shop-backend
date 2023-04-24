<template>
	<view>
		<!-- 搜索组件 -->
		<view class="search-box">
			<my-search @click="gotoSearch"></my-search>
		</view>

		<!-- 轮播图的区域 -->
		<swiper :indicator-dots="true" :autoplay="true" :interval="3000" :duration="1000" :circular="true">
			<swiper-item v-for="(item, i) in swiperList" :key="i">
				<navigator class="swiper-item" :url="'/subpkg/goods_detail/goods_detail?goods_id='+item.goodsId">
					<image :src="item.imageSrc"></image>
				</navigator>
			</swiper-item>
		</swiper>


		<!-- 分类导航区域 -->
		<view class="nav-list">
			<view class="nav-item" v-for="(item, i) in navList" :key="i" @click="navClickHandler(item)">
				<image :src="item.imageSrc" class="nav-img"></image>
			</view>
		</view>

		<!-- 楼层区域 -->
		<!-- 楼层的容器 -->
		<view class="floor-list">
			<!-- 每一个楼层的item项 -->
			<view class="floor-item" v-for="(item, i) in floorList" :key="i">
				<!-- 楼层的标题 -->
				<image :src="item.imageSrc" class="floor-title"></image>
				<!-- 楼层的图片区域 -->
				<view class="	floor-img-box">
					<!-- 左侧大图片的盒子 -->
					<navigator class="left-img-box" :url="item.productVOs[0].url">
						<image :src="item.productVOs[0].imageSrc"
							:style="{width: item.productVOs[0].imageWidth+'rpx'}" mode="widthFix"></image>
					</navigator>
					<!-- 右侧4个小图片的盒子 -->
					<view class="right-img-box">
						<navigator class="right-img-item" v-for="(item2,i2) in item.productVOs" :key="i2"
							v-if="i2!==0" :url="item2.url">
							<image :src="item2.imageSrc" :style="{width: item2.imageWidth+'rpx'}" mode="widthFix">
							</image>
						</navigator>
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
				//这是轮播图的数据列表
				swiperList: [],
				//分类导航的数据列表
				navList: [],
				// 楼层的数据
				floorList: []
			};
		},
		onLoad() {
			//调用方法，获取轮播图的数据
			this.getSwiperList()
			this.getNavList()
			this.getFloorList()
		},
		methods: {
			//请求轮播图的数据
			async getSwiperList() {
				const res = await this.$myRequest({
					url: '/index/getCarouselList',
				})
				//请求失败
				if (res.statusCode != 200) return uni.$showMsg()
				this.swiperList = res.data.data
			},
			//请求分类导航的数据
			async getNavList() {
				const res = await this.$myRequest({
					url: '/index/getNavigationList',
				})
				//请求失败
				if (res.statusCode != 200) return uni.$showMsg()
				this.navList = res.data.data
			},
			navClickHandler(item) {
				if (item.name === '分类') {
					uni.switchTab({
						url: '/pages/cate/cate',
					})
				}
			},
			// 获取首页楼层数据的方法
			async getFloorList() {
				const res = await this.$myRequest({
					url: '/index/listFloors',
				})
				//请求失败
				if (res.statusCode != 200) return uni.$showMsg()
				// 对数据进行处理
				res.data.data.forEach(floor => {
					floor.productVOs.forEach(prod => {
						prod.url = '/subpkg/goods_list/goods_list?' + prod.navigatorUrl.split('?')[1]
					})

				})
				this.floorList = res.data.data
			},
			gotoSearch() {
				uni.navigateTo({
					url: '/subpkg/search/search'
				})
			}
		}
	}
</script>

<style lang="scss">
	swiper {
		height: 330rpx;

		.swiper-item,
		image {
			width: 100%;
			height: 100%;
		}
	}

	.nav-list {
		display: flex;
		justify-content: space-around;
		margin: 15px 0;

		.nav-img {
			width: 128rpx;
			height: 140rpx;

		}
	}

	.floor-title {
		width: 100%;
		height: 60rpx;
	}

	.right-img-box {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-around;
	}

	.floor-img-box {
		display: flex;
		padding-left: 14rpx;
	}

	.search-box {
		// 设置定位效果为“吸顶”
		position: sticky;
		// 吸顶的“位置”
		top: 0;
		// 提高层级，防止被轮播图覆盖
		z-index: 999;
	}
</style>
