<template>
	<view class="my-search-container" :style="{'background-color':bgcolor}" @click="seaarchBoxHander">
		<view class="my-search-box" :style="{'border-radius':radius+'px'}">
			<!-- 这是使用uni-ui提供的图标组件 -->
			<uni-icons type="search" size="17"></uni-icons>
			<text class="placeholder">点击搜索商品信息</text>
		</view>
	</view>
</template>

<script>
	export default {
		name: "my-search",
		data() {
			return {

			};
		},
		props: {
			//背景颜色
			bgcolor: {
				type: String,
				default: '#00868B'
			},
			// 圆角尺寸
			radius: {
				type: Number,
				default: 15 //px
			}
		},
		methods: {
			seaarchBoxHander() {
				console.log('!!!')
				this.$emit('click')
			}

		}
	}
</script>

<style lang="scss">
	.my-search-container {
		height: 50px;
		// background-color: #c00000;
		display: flex;
		align-items: center;
		padding: 0 10px;

		.my-search-box {
			height: 36px;
			background-color: #ffffff;
			// border-radius: 15px;
			width: 100%;
			display: flex;
			justify-content: center;
			align-items: center;

			.placeholder {
				font-size: 15px;
				margin-left: 5px;
				color:#6B6B6B;
			}
		}
	}
</style>
