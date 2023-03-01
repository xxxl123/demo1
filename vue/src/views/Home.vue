<template>
  <div>
	  <h2 >欢迎使用课程管理系统</h2>
	  <div>
		  <Lbt></Lbt>
		  <div class="news">
			  <ul>
				  <h3>本周资讯</h3>
				  <li>上半学期补考将安排在开学第一周周六和周日</li>
				  <li>姜春教授本周三下午将于开展“央视春晚审美变迁与思考”文化素质讲座</li>
				  <li>喜报！我校农学院获批省级重点产业学院</li>
				  <li>教学沙龙 || 音乐学院、体育学院将在体育馆开展联合教学沙龙活动</li>
				  <li>戈丽芳教授本周四下午将于开展“面试说课中的教态设计与语言表达”文化素质讲座</li>
			  </ul>
		  </div>
		  <div class="toLogin" >
			  <h4 style="margin-left: 25px;"><a href="http://localhost:8080/login">欢迎使用</a></h4>
		  </div>
	  </div>
	  
  </div>

</template>

<script>
import Lbt from "../views/home/Lbt.vue"

export default {
  name: "Home",
  data() {
    return {
      tableData: [],
    };
  },
   components: {
	   Lbt
   }
};
</script>

<style scoped>
	.news{
		position: absolute;
		margin-left: 650px;
		margin-top: -315px;
		width: 400px;
		line-height: 30px;
	}
	.toLogin{
		width: 150px;
		height: 150px;
		background-color: rgba(200, 223, 241, 0.4);
		animation: move 8s infinite;
		line-height: 140px;
		
	}
	.toLogin:hover {
		animation-play-state: paused;
	}
	@keyframes move{
		0%{
			transform: translate(1000px ,-500px)
		}
	}
</style>
