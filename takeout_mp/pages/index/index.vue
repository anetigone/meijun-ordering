<template>
	<view>
		<view class="divHead">
			<image src="/static/index_image/beijing_png.png" mode=""></image>
		</view>
		<view class="divTitle">
			<view class="divStatic">
				<image src="../../static/index_image/log.jpg" class="logo"></image>
				<view class="divDesc">
					<view class="divName">美食元素</view>

					<view class="divSend">
						<image src="/static/index_image/dingwei.png" />
						<text>距离1.5km</text>
						<image src="/static/index_image/qiandai.png" />
						<text>配送费2元</text>
						<image src="/static/index_image/shijian.png" />
						<text>预计时长12min</text>
					</view>
				</view>
			</view>
			<view class="divDesc">简介: "有滋有味的生活来自于对美食的品味和享受".</view>
		</view>
		<!-- <u-skeleton :loading="loading" rows="15"></u-skeleton> -->
		<view class="menuBar">
			<me-tabs v-model="tabIndex" :tabs="tabs" :tab-width="130"></me-tabs>
		</view>
		<view class="divBody">
			<view class="divType">
				<scroll-view scroll-y class="ul">
					<view v-for="(item, index) in categoryList" :key="index"
						@click="categoryClick(index, item.id, item.type)"
						:class="['li', activeType === index ? 'active' : '']">
						{{ item.name }}
					</view>
				</scroll-view>
			</view>

			<view class="divMenu">
				<scroll-view scroll-y :scroll-top="scrollTop" :style="{'--height':fixedHeight + 'px'}" class="mescro-uni">
				<!-- <mescroll-uni :fixed="false" ref="mescrollRef" :height="wh"  :down="downOption" :up="upOption"> -->
					
					<view class="divItem"  v-for="(item, index) in dishList" :key="index" @click="dishDetails(item)">
						<u-image width="172rpx" height="172rpx" :src="item.image">
							<image src="../../static/images/noImg.png" />
						</u-image>
						<view>
							<view class="divName">{{ item.name }}</view>
							<view class="divDesc">{{ item.description }}</view>
							<view class="divDesc">{{ '月销' + (item.sales) }}</view>
							<view class="divBottom">
								<text>￥</text>
								<text>{{ item.price / 100 }}</text>
							</view>
							<view class="divNum">
								<view class="divAdd" v-if="item.number >= 1">
									<image src="../../static/images/subtract.png"
										@click.prevent.stop="subtractCart(item)" />
								</view>
								<view class="divDishNum">{{ item.number }}</view>
								<view class="divTypes" v-if="item.flavors && item.flavors.length > 0 && !item.number"
									@click.prevent.stop="chooseFlavorClick(item)">选择规格</view>
								<view class="divAdd" v-else>
									<image src="../../static/images/add.png" @click.prevent.stop="addCart(item,$event)"></image> 
								
								</view>
							</view>
						</view>
						
					</view>
				<!-- </mescroll-uni> -->
				<view class="space_scroll-view">
					
				</view>
				</scroll-view>
			</view>
		</view>
		<view class="divLayer">
			<view class="divLayerLeft"></view>
			<view class="divLayerRight"></view>
		</view>
		<!-- 购物车 -->
		<view  class="divCart" v-if="categoryList.length > 0">
			<view class="imgCart" v-if="!cartData || cartData.length < 1">
				<!-- <image src="/static//index_image/暂无配送.png" style="height: 100%;width: 100%;"></image> -->
			</view>
			<view class="imgCartActive" @click="openCart" v-else>
				<!-- <image src="/static//index_image/配送快递服务.png" style="height: 100%;width: 100%;"></image> -->
			</view>

			<view :class="{ divGoodsNum: 1 === 1, moreGoods: cartData && cartData.length > 99 }"
				v-if="cartData && cartData.length > 0">{{ goodsNum }}</view>
			<view class="divNum">
				<text>￥</text>
				<text>{{ goodsPrice }}</text>
			</view>
			<view class="divPrice"></view>
			<!-- <view class="btnSubmit" v-if="cartData.length<1">去结算</view> -->
			<button class="btnSubmit" v-if="cartData.length < 1">去结算</button>
			<!-- <view  class="btnSubmitActive" @click="toAddOrderPage" v-else>去结算</view> -->
			<button class="btnSubmitActive" @click="toAddOrderPage" v-else>去结算</button>
			<!-- <u-button color="#feca50" shape="circle" openType="getphonenumber">去结算</u-button> -->
		</view>

		<view class="floating-cart" @click="openCart">
			<text style="font-size: 48rpx;">🛒</text>
			<view v-if="goodsNum > 0" class="cart-badge">{{ goodsNum }}</view>
		</view>

		<!-- 口味选项弹框 -->
		<u-popup ref="flavorDialog" @close="flavorclose" :closeOnClickOverlay="true" :round="10" mode="center"
			:show="dialogFlavor.show" v-model="dialogFlavor.show">
			<view class="dialogFlavor">
				<view class="dialogTitle">{{ dialogFlavor.name }}</view>
				<view class="divContent">
					<view v-for="(flavor, index) in dialogFlavor.flavors" :key="flavor.id">
						<view class="divFlavorTitle">{{ flavor.name }}</view>
						<text v-for="item in JSON.parse(flavor.value)" :key="item" @click="flavorClick(index, item)"
							:class="{ spanActive: flavor.dishFlavor === item }">
							{{ item }}
						</text>
					</view>
				</view>
				<view class="divBottom">
					<view>
						<text class="spanMoney">￥</text>
						{{ dialogFlavor.price / 100 }}
					</view>

					<view @click="dialogFlavorAddCart($event)">加入购物车</view>
				</view>
			</view>
		</u-popup>
		<u-notify :show="true" message="123" type="warning"></u-notify>
		<!-- 购物车弹出 -->

		<u-popup v-model="cartDialogShow" mode="bottom" :show="cartDialogShow" @close="cartclose"
			:closeOnClickOverlay="true" :round="10">
			<view class="dialogCart">
				<view class="divCartTitle">
					<view class="title">购物车</view>
					<view class="clear" @click="clearCart">
						<view class="el-icon-delete"></view>
						清空
					</view>
				</view>
				<!-- <scroll-view scroll-y="true" :style="{ height: wh + 'rpx' }"> -->
					<mescroll-uni :fixed="false" :height=wh ref="mescrollRef" :down="downOption" :up="upOption">
					<view class="divCartContent">
						<view v-for="item in cartData" :key="item.id" class="divCartItem">
							<u-image width="128rpx" height="128rpx" :src="item.image"></u-image>
							<view class="divDesc">
								<view class="name">{{ item.name }}</view>
								<view class="price">
									<text class="spanMoney">￥</text>
									{{ item.amount }}
								</view>
							</view>
							<view class="divNum">
								<view class="divSubtract">
									<image src="../../static/images/subtract.png" @click="cartNumberSubtract(item)" />
								</view>
								<view class="divDishNum" style="font-size: 28rpx;font-weight: bold">{{ item.number }}
								</view>
								<view class="divAdd">
									<image src="../../static/images/add.png" @click="cartNumAdd(item)" />
								</view>
							</view>
							<view class="divSplit"></view>
						</view>
					</view>
				<!-- </scroll-view> -->
				</mescroll-uni>
			</view>
		</u-popup>

		<!-- 菜品详情弹窗 -->
		<u-popup @close="dishclose" :closeOnClickOverlay="true" :round="10" mode="center" :show="detailsDialog.show"
			 ref="detailsDialog">
			<view class="detailsDialog">
				<view class="divContainer">
					<u-image :src="detailsDialog.item.image">
						<view slot="error" class="image-slot">
							<image src="../../static/images/noImg.png" />
						</view>
					</u-image>
					<view class="title">{{ detailsDialog.item.name }}</view>
					<view class="content">{{ detailsDialog.item.description }}</view>
				</view>
				<view class="divNum">
					<view class="left">
						<text>￥</text>
						<text>{{ detailsDialog.item.price / 100 }}</text>
					</view>
					<view class="right">
						<view class="divSubtract" v-if="detailsDialog.item.number > 0">
							<image src="../../static/images/subtract.png" @click="subtractCart(detailsDialog.item)" />
						</view>
						<view class="divDishNum">{{detailsDialog.item.number == null ? "" : detailsDialog.item.number}}</view>
						<view class="divTypes"
							v-if="detailsDialog.item.flavors && detailsDialog.item.flavors.length > 0 && !detailsDialog.item.number"
							@click="chooseFlavorClick(detailsDialog.item)">
							选择规格
						</view>
						<view class="divAdd" v-else>
							<image src="../../static/images/add.png" @click="addCart(detailsDialog.item,$event)" />
						</view>
					</view>
				</view>
				<!-- <view class="detailsDialogClose" @click="detailsDialog.show = false">
					            <image  src="../../static/images/close.png"/>
					          </view> -->
			</view>

			<!-- </u-modal> -->
		</u-popup>
		<!-- 套餐详情弹窗 -->
		<u-popup @close="setMealclose" :closeOnClickOverlay="true" :round="10" mode="center" :show="setMealDialog.show"
			v-model="setMealDialog.show" v-if="setMealDialog.show" ref="setMealDetailsDialogd">
			<view class="setMealDetailsDialog">
				<view class="divContainer">
					
					
					<u-image :src="setMealDialog.item.image" >
						<view slot="error" class="image-slot">
							<image src="../../static/images/noImg.png" />
						</view>
					</u-image>
					<!-- <view class="image-container" :style="{ backgroundImage: `url(${setMealDialog.item.image})` }">
						
					</view> -->

					<view class="title">{{ setMealDialog.item.name }}</view>
					<view class="content">{{ setMealDialog.item.description }}</view>
					<view class="item" v-for="(item, index) in setMealDialog.item.list" :key="index">
						<!-- <u-image :src="item.image">
							<view slot="error" class="image-slot">
								<image src="../../static/images/noImg.png" />
							</view>
						</u-image> -->
						<view class="divSubTitle">
							<text class="ellipsis">
								{{ item.name + '(' + item.copies + '份)' }}
							</text>
							
							<view class="divPrice">
								<text>￥</text>
								<text>{{ item.price / 100 }}</text>
							</view>
						</view>
						
					</view>
				</view>
				<view class="divNum">
					<view class="left">
						<text>￥</text>
						<text>{{ setMealDialog.item.price / 100 }}</text>
					</view>
					<view class="right">
						<view class="divSubtract" v-if="setMealDialog.item.number > 0">
							<image src="../../static/images/subtract.png" @click="subtractCart(setMealDialog.item)" />
						</view>
						<view class="divDishNum">{{ setMealDialog.item.number }}</view>
						<view class="divAdd" v-if="setMealDialog.item.number">
							<image src="../../static/images/add.png" @click="addCart(setMealDialog.item)" />
						</view>
						<view class="addCart" @click="addCart(setMealDialog.item)" v-if="!setMealDialog.item.number">
							加入购物车</view>
					</view>
				</view>
			</view>
		</u-popup>
		
		
	</view>
</template>
<style>
	@import url('./index.css');
	.divBody .divMenu .mescro-uni{
		 height: calc(100vh - var(--height));
	 }
	 .floating-cart {
		 position: fixed;
		 right: 40rpx;
		 bottom: 80rpx;
		 width: 80rpx;
		 height: 80rpx;
		 background: #fff;
		 border-radius: 50%;
		 box-shadow: 0 2rpx 8rpx rgba(0,0,0,0.15);
		 display: flex;
		 align-items: center;
		 justify-content: center;
		 z-index: 9999;
	 }
	 .cart-badge {
		 position: absolute;
		 top: 8rpx;
		 right: 8rpx;
		 background: #f00;
		 color: #fff;
		 border-radius: 50%;
		 padding: 2rpx 8rpx;
		 font-size: 22rpx;
		 min-width: 28rpx;
		 text-align: center;
	 }
</style>
<script>
	
	import MescrollMixin from "@/uni_modules/mescroll-uni/components/mescroll-uni/mescroll-mixins.js";
	import regeneratorRuntime, {
		async
	} from '../../lib/runtime/runtime';
	import {
		cartListApi,
		categoryListApi,
		dishListApi,
		setmealListApi,
		clearCartApi,
		updateCartApi,
		setMealDishDetailsApi,
		addCartApi
	} from '../../api/index';

	export default {
		mixins: [MescrollMixin], // 使用mixin
		data() {
			return {
				downOption:{
					use : false
				},
				upOption : {
					use : false
				},
				fixedHeight: 0,
				tabs: [{name:'点菜'}],
				tabIndex: 0, // 当前tab的下标
				checked: false,
				loading: true,
				phone_info: [],
				flavorNotify: false,
				wh: 0,
				scrollTop: 0,
				active: 0,
				icon: {
					active: '../images/order.png',
					inactive: 'https://img01.yzcdn.cn/vant/user-inactive.png'
				},
				
				//左边菜品类别index
				activeType: 0,
				categoryList: [],
				categoryId: undefined,
				dishList: [
					{
						id: 1,
						name: "红烧肉",
						description: "肥而不腻，入口即化",
						price: 3800, // 单位：分
						image: "https://img.yzcdn.cn/vant/cat.jpeg",
						sales: 120,
						number: 0
					},
					{
						id: 2,
						name: "糖醋排骨",
						description: "酸甜可口，肉质鲜嫩",
						price: 4200,
						image: "https://img.yzcdn.cn/vant/dog.jpeg",
						sales: 98,
						number: 0
					}
				],
				cartData: [],
				dialogFlavor: {
					name: '',
					flavors: [],
					dishId: undefined,
					price: undefined,
					show: false,
					image: ''
				},
				cartDialogShow: false,
				detailsDialog: {
					show: false,
					item: {
						image: '',
						number : undefined
					}
				},
				setMealDialog: {
					show: false,
					item: {}
				},
				busPos:{
				    x:uni.getSystemInfoSync().windowWidth - uni.getSystemInfoSync().windowWidth * 0.91,
				    y:uni.getSystemInfoSync().windowHeight + uni.getSystemInfoSync().windowWidth * 0.1
				}
			};
		},
		components: {},
		onShow() {
			this.initData();
			let token = uni.getStorageSync('token');
			console.log('token', token);
			if (token.length == 0) {
				this.cartData = [];
			}
			this.activeType = 0;
			this.dialogFlavor.show = false;

		},
		computed: {
			goodsNum() {
				let num = 0;
				this.cartData.forEach(item => {
					num += item.number;
				});
				if (num < 99) {
					return num;
				} else {
					return '99+';
				}
			},
			goodsPrice() {
				let price = 0;
				this.cartData.forEach(item => {
					price += item.number * item.amount;
				});
				return price.toFixed(2);
			}
		},
		onReady() {},
		created() {},

		watch: {
			// 'dialogFlavor.show'(flag) {
			// 	const query = uni.createSelectorQuery().in(this);
			// 	if (flag) {
			// 		query.select('.divCart').style.zIndex = 1
			// 		// document.querySelector('.divCart').style.zIndex = 1
			// 	} else {
			// 		query.select('.divCart').style.zIndex = 3000
			// 		// document.querySelector('.divCart').style.zIndex = 3000
			// 	}
			// },
		},
		
		mounted() {
			const sysInfo = uni.getSystemInfoSync();
			this.wh = sysInfo.windowHeight * 0.6
			console.log('wh',this.wh)
			this.cartData = [];
			
			

		},
		onReady(){
			let _this = this
			let menuBarComponentsHeight = 0
			let divHeadComponentsHeight = 0
			let divCartComponentsHeight = 0
			setTimeout(()=>{
				let menuBar = uni.createSelectorQuery().in(this).select(".menuBar");
				let divHead = uni.createSelectorQuery().in(this).select(".divHead");
				let divCart = uni.createSelectorQuery().in(this).select(".divCart");

				divHead.fields({
				size : true,
				scrollOffset: true
				},data =>{
				divHeadComponentsHeight = data.height
				console.log('divHeadComponentsHeight',divHeadComponentsHeight);
				console.log("得到节点信息" + JSON.stringify(data));
				}).exec();
				
				menuBar.fields({
				 size: true,
				 scrollOffset: true
				}, data => {
				menuBarComponentsHeight = data.height
				console.log('menuBarComponentsHeight',menuBarComponentsHeight);
				console.log("得到节点信息" + JSON.stringify(data));
				}).exec();
				
				divCart.fields({
				 size: true,
				 scrollOffset: true
				}, data => {
				divCartComponentsHeight = data.height
				console.log('divCartComponentsHeight',divCartComponentsHeight);
				console.log("得到节点信息" + JSON.stringify(data));
				_this.fixedHeight = menuBarComponentsHeight + divCartComponentsHeight + divHeadComponentsHeight + 33
				console.log('ComponentsHeight',_this.fixedHeight);
				}).exec();
				

			},500)

			},
		methods: {
			cartclose() {
				this.cartDialogShow = false;
			},
			dishclose() {
				this.detailsDialog.show = false;
			},
			setMealclose() {
				this.setMealDialog.show = false;
			},
			flavorclose() {
				this.dialogFlavor.show = false;
			},
			//初始化数据
			async initData() {
				Promise.all([categoryListApi(), cartListApi({})]).then(res => {
					//获取分类数据
					console.log('菜单列表', res);
					
					if (res[0].code === 0) {
						this.categoryList = res[0].data;
						if (Array.isArray(res[0].data) && res[0].data.length > 0) {
							this.categoryId = res[0].data[0].id;
							if (res[0].data[0].type === 1) {
								this.getDishList();
							} else {
								this.getSetmealData();
							}
						}
					} else {
						
						return uni.$showMsg(res.msg == 'token不能为空'? '未登录' : res.msg);
						
					}
					//获取菜品数据
					if (res[1].code === 0) {
						this.cartData = res[1].data;
					} else {
						// return uni.$showMsg("请登录");
					}
					if (this.loading) {
						setTimeout(() => {
							this.loading = false;
						}, 1500);
					}
				});
			},
			//分类点击
			categoryClick(index, id, type) {
				this.scrollTop = this.scrollTop === 0 ? 1 : 0;
				this.activeType = index;
				this.categoryId = id;
				console.log('index',index,'type',type)
				if (type === 1) {
					//菜品
					this.getDishList();
				} else {
					this.getSetmealData();
				}
			},
			//获取菜品数据
			async getDishList() {
				if (!this.categoryId) {
					return;
				}
				const res = await dishListApi({
					categoryId: this.categoryId,
					status: 1
				});
				if (res.code === 0) {
					let dishList = res.data;
					const cartData = this.cartData;
					if (dishList.length > 0 && cartData.length > 0) {
						dishList.forEach(dish => {
							cartData.forEach(cart => {
								if (dish.id === cart.dishId) {
									dish.number = cart.number;
								}
							});
						});
					}
					this.dishList = dishList;
				} else {
					return uni.$showMsg(res.msg == 'token不能为空'? '未登录' : res.msg);;
				}
			},
			//获取套餐数据setmealId
			async getSetmealData() {
				if (!this.categoryId) {
					return;
				}
				const res = await setmealListApi({
					categoryId: this.categoryId,
					status: 1
				});
				if (res.code === 0) {
					let dishList = res.data;
					const cartData = this.cartData;
					if (dishList.length > 0 && cartData.length > 0) {
						dishList.forEach(dish => {
							cartData.forEach(cart => {
								if (dish.id === cart.setmealId) {
									dish.number = cart.number;
								}
							});
						});
					}
					this.dishList = dishList;
				} else {
					
					return uni.$showMsg(res.msg == 'token不能为空'? '未登录' : res.msg);;
				}
			},
			//获取购物车数据
			async getCartData() {
				const res = await cartListApi({});
				if (res.code === 0) {
					this.cartData = res.data;
				} else {
					console.log("-----------获取购物车失败-----------")
					return uni.$showMsg(res.msg == 'token不能为空'? '未登录' : res.msg);;
				}
			},
			//菜单中往购物车中添加商品
			async addCart(item,event) {
				const token = this.getTokenInfo();
				if (!token) return;
				
				let params = {
					amount: item.price / 100, //金额
					dishFlavor: item.dishFlavor, //口味  如果没有传undefined
					dishId: undefined, //菜品id
					setmealId: undefined, //套餐id
					name: item.name,
					image: item.image
				};
				if (Array.isArray(item.flavors)) {
					//表示是菜品
					params.dishId = item.id;
				} else {
					//表示套餐 套餐没有口味
					params.setmealId = item.id;
				}
				const res = await addCartApi(params);
				if (res.code === 0) {
					this.dishList.forEach(dish => {
						if (dish.id === item.id) {
							console.log('数量={}', res.data.number);
							dish.number = res.data.number;
						}
					});
					if (this.setMealDialog.show) {
						console.log(res.data.number);
						item.number = res.data.number;
					}
					this.getCartData();
				} else {
					return uni.$showMsg(res.msg == 'token不能为空'? '未登录' : res.msg);;
				}
			},

			//菜单中减少选中的商品
			async subtractCart(item) {
				const token = this.getTokenInfo();
				if (!token) return;
				let params = {
					dishId: item.id
				};
				if (!Array.isArray(item.flavors)) {
					params = {
						setmealId: item.id
					};
				}
				const res = await updateCartApi(params);
				if (res.code === 0) {
					this.dishList.forEach(dish => {
						if (dish.id === item.id) {
							dish.number = res.data.number === 0 ? undefined : res.data.number;
							if (this.detailsDialog.show && dish.number == undefined){
								this.detailsDialog.show = false
							}
						}
					});
					if (this.setMealDialog.show) {
						item.number = res.data.number === 0 ? undefined : res.data.number;
						if (this.detailsDialog.show && item.number == undefined){
							this.detailsDialog.show = false
						}
					}
					this.getCartData();
				} else {
					return uni.$showMsg(res.msg == 'token不能为空'? '未登录' : res.msg);;
					this.$notify({
						type: 'warning',
						message: res.msg
					});
				}				
			},

			//展开购物车
			openCart() {
				if (this.cartData.length > 0) {
					this.cartDialogShow = true;
				}

			},
			//购物车中增加商品数量
			async cartNumAdd(item) {
				
				const token = this.getTokenInfo();
				if (!token) return;
				let params = {
					amount: item.amount, //金额
					dishFlavor: item.dishFlavor, //口味  如果没有传undefined
					dishId: item.dishId, //菜品id
					setmealId: item.setmealId, //套餐id
					name: item.name,
					image: item.image
				};
				const res = await addCartApi(params);
				if (res.code === 0) {
					this.dishList.forEach(dish => {
						if (dish.id === (item.dishId || item.setmealId)) {
							dish.number = res.data.number;
						}
					});
					console.log(this.dishList);
					this.getCartData();
				} else {
					return uni.$showMsg(res.msg == 'token不能为空'? '未登录' : res.msg);;
				}
			},
			//购物车中减少商品数量
			async cartNumberSubtract(item) {
				const token = this.getTokenInfo();
				if (!token) return;
				let params = {
					dishId: item.dishId,
					setmealId: item.setmealId
				};
				const res = await updateCartApi(params);
				if (res.code === 0) {
					this.dishList.forEach(dish => {
						if (dish.id === (item.dishId || item.setmealId)) {
							dish.number = res.data.number === 0 ? undefined : res.data.number;
							if (dish.number == undefined && this.cartData.length == 1){
								this.cartDialogShow = false
							}
						}
					});
					this.getCartData();
				} else {
					return uni.$showMsg(res.msg == 'token不能为空'? '未登录' : res.msg);;
				}
			},

			//修改商品列表中的数据number
			changeDishList(item) {
				for (let ele of this.dishList) {
					if (ele.id === (item.setmealId || item.dishId)) {
						ele.number = item.number;
					}
				}
			},

			//清空购物车
			async clearCart() {
				const token = this.getTokenInfo();
				if (!token) return;
				const res = await clearCartApi();
				if (res.code === 0) {
					for (let ele of this.dishList) {
						ele.number = undefined;
					}
					// this.updataNums()
					
					this.cartData = [];
					this.cartDialogShow = false;
				} else {
					return uni.$showMsg(res.msg == 'token不能为空'? '未登录' : res.msg);;
				}				
			},
			//点击选择规格
			chooseFlavorClick(item) {
				this.detailsDialog.show = false;
				this.dialogFlavor = {
					name: '',

					flavors: [],
					dishId: undefined,
					price: undefined,
					show: false
				};
				this.dialogFlavor = {
					name: item.name,
					flavors: item.flavors,
					dishId: item.id,
					price: item.price,
					show: true,
					image: item.image
				};
				console.log('口味', this.dialogFlavor.flavors);
			},
			flavorClick(index, item) {
				console.log('index', index);
				this.dialogFlavor.flavors[index].dishFlavor = item;
				// console.log("ok!")
				// console.log("输出flavor",flavor.dishFlavor)
				// flavor.dishFlavor = item

				// this.checked = true
				// console.log("输出flavor",flavor.dishFlavor)
				// console.log("输出flavors",this.dialogFlavor.flavors)
				// console.log("输出item",item)
				// //强制刷新dialog的dom
				this.dialogFlavor.show = false;
				this.dialogFlavor.show = true;
			},
			//选择规格加入购物车
			dialogFlavorAddCart(event) {
			
				const dialogFlavor = this.dialogFlavor;
				let flag = true;
				let dishFlavor = [];
				dialogFlavor.flavors.forEach(item => {
					console.log('选择口味加入购物车', item);
					if (item.dishFlavor) {
						dishFlavor.push(item.dishFlavor);
					} else {
						flag = false;
						console.log('请添加口味');
						uni.showToast({
							title: '请选择' + item.name,
							icon: 'error'
						});
					}
				});
				if (flag) {
					this.addCart({
						price: dialogFlavor.price,
						dishFlavor: dishFlavor.join(','),
						id: dialogFlavor.dishId,
						flavors: [],
						image: dialogFlavor.image,
						name: dialogFlavor.name
					},event);
					this.dialogFlavor.show = false;
				}
			
			},
			//跳转到去结算界面
			toAddOrderPage() {
				const token = this.getTokenInfo();
				if (!token) return;
				if (this.cartData.length > 0) {
					uni.navigateTo({
						url: '/pages/addOrder/addOrder'
					});
				}
			},
			async dishDetails(item) {
				//先清除对象数据，如果不行的话dialog使用v-if
				this.detailsDialog.item = {};
				this.setMealDialog.item = {};
				if (Array.isArray(item.flavors)) {
					this.detailsDialog.item = item;
					console.log('item',item)
					this.detailsDialog.show = true;
				} else {
					//显示套餐的数据
					const res = await setMealDishDetailsApi(item.id);
					if (res.code === 0) {
						this.setMealDialog.item = {
							...item,
							list: res.data.setmealDishes
						};
						this.setMealDialog.show = true;
					} else {
						return uni.$showMsg(res.msg == 'token不能为空'? '未登录' : res.msg);;
					}
				}
			},
			// 获取 Token 信息
			getTokenInfo() {
			    const token = uni.getStorageSync('token');
			    if (!token) {
			        uni.showModal({
			            title: '提示',
			            content: '请登录',
			            success: function(res) {
			                if (res.confirm) {
			                    uni.switchTab({
			                        url: '/pages/my/my'
			                    });
			                } else if (res.cancel) {
			                    console.log('用户点击取消');
			                }
			            }
			        });
			        return;
			    }
			    return token;
			},
			addCart(item) {
				item.number += 1;
				uni.showToast({
					title: '已加入购物车',
					icon: 'success'
				});
			}
		}
	};
</script>
