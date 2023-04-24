<template>
	<view>
		<!-- 使用自定义的组件 -->
		<my-search @click="gotoSearch"></my-search>

		<view class="scroll-view-container">
			<!-- 左侧的滑动区域 -->
			<scroll-view class="left-scroll-view" scroll-y="true" :style="{height:wh+'px'}">
				<block v-for="(item, i) in cateList" :key="i">
					<view :class="['left-scroll-view-item',i===active ? 'active':'']" @click=" activeChanged(i)">
						{{item.catName}}
					</view>
				</block>
			</scroll-view>

			<!-- 右侧的滑动区域 -->
			<scroll-view scroll-y="true" :style="{height:wh+'px'}" :scroll-top="scrollTop">
				<view class="cate-lv2" v-for="(item2,i2) in catelevl2" :key="i2">
					<!-- 二级分类的标题 -->
					<view class="cate-lv2-title">/ {{item2.catName}} /</view>
					<!-- 当前二级分类下的三级分类列表 -->
					<view class="cate-lv3-list">
						<!-- 三级分类的item项 -->
						<view class="cate-lv3-item" v-for="(item3,i3) in item2.children" :key="i3"
							@click="gotoGoodsList(item3)">
							<!-- 三级分类的图片 -->
							<image :src="item3.catIcon.replace('dev','web')"></image>
							<!-- 三级分类的文本 -->
							<text>{{item3.catName}}</text>
						</view>
					</view>
				</view>
			</scroll-view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				// 当前设备可用的高度
				wh: 0,
				cateList: [],
				active: 0,
				// 二级分类的列表
				catelevl2: [],
				scrollTop: 0
			};
		},
		onLoad() {
			const sysInfo = uni.getSystemInfoSync()
			this.wh = sysInfo.windowHeight - 50

			this.getCateList()
		},
		methods: {
			// 获取分类列表的数据
			async getCateList() {
				const res = await this.$myRequest({
					url: '/index/withChildren',
				})
				//请求失败
				if (res.statusCode !== 200) return uni.$showMsg()
				this.cateList = res.data.data
				// 二级分类赋值
				this.catelevl2 = res.data.data[0].children
			},
			activeChanged(i) {
				this.active = i

				//重新为二级分类赋值
				this.catelevl2 = this.cateList[i].children

				this.scrollTop = this.scrollTop === 0 ? 1 : 0
			},
			// 跳转到商品列表页面
			gotoGoodsList(item) {
				uni.navigateTo({
					url: '/subpkg/goods_list/goods_list?cid=' + item.catId
				})
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
	.scroll-view-container {
		display: flex;

		.left-scroll-view {
			width: 120px;

			.left-scroll-view-item {
				background-color: #F7F7F7;
				line-height: 60px;
				text-align: center;
				font-size: 12px;

				&.active {
					background-color: #FFFFFF;
					position: relative;

					&::before {
						content: ' ';
						display: block;
						width: 3px;
						height: 30px;
						background-color: #C00000;
						position: absolute;
						top: 50%;
						left: 0;
						transform: translateY(-50%);
					}
				}
			}
		}
	}

	.cate-lv2-title {
		font-size: 12px;
		font-weight: bold;
		text-align: center;
		padding: 15px 0;
	}

	.cate-lv3-list {
		display: flex;
		flex-wrap: wrap;

		.cate-lv3-item {
			width: 33.33%;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			margin-bottom: 10px;

			image {
				width: 60px;
				height: 60px;
			}

			text {
				font-size: 12px;
			}
		}
	}
</style>
