<template>
	<div class="out">
		<div class="btn btn-left" @click="prePic"></div>
		<!-- src属性是根据按钮点击会发生变化，所以绑定属性 -->
		<img :src="pics[num].imgUrl" class="pic">
		<div class="btn btn-right" @click="nextPic"></div>
	</div>
</template>

<script>
	export default {
		name:"Lbt",
		data(){
			return{
				num:0,
				pics:[
					// 在主键中如果要引用资源文件，需要使用require函数
					{imgUrl:require("../../assets/image/a.jpg")},
					{imgUrl:require("../../assets/image/b.jpg")},
					{imgUrl:require("../../assets/image/c.jpg")},
				]
			}
		},
		methods:{
			//上一张图片
			prePic(){
				if(this.num==0){
					this.num=this.pics.length-1;
				}else{
					this.num--;
				}
			},
			//下一张
			nextPic(){
				if(this.num==this.pics.length-1){
					this.num=0;
				}else{
					this.num++;
				}
			},
		}
	}
</script>

<style scoped>
.out{
	width: 565px;
	height: 315px;
	margin-top: 50px;
	margin-left: 30px;
	border-radius: 8px;
	position: relative;
	
}
.pic{
	width: 565px;
	height: 310px;
}
.out .btn{
	width: 30px;
	height: 60px;
	color: white;
	background: rgba(0, 0, 0, 3);
	position: absolute;
	top: calc(50% - 30px);
	line-height: 60px;
	text-align: center;
	font-size: 20px;
	font-family: "宋体";
	cursor: pointer;
	border-radius: 8px;
}
.out .btn-left{
	background-color: transparent;
	left:0;
}
.out .btn-right{
	background-color: transparent;
	right: 0;
}
</style>