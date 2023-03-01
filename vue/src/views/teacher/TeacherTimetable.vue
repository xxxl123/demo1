<template>
	<div class="timetable-wrap">
		<div class="crumbs">
			<el-breadcrumb separator="/">
				<el-breadcrumb-item>
					<i class="el-icon-fa fa-table"></i> 教师课表
				</el-breadcrumb-item>
			</el-breadcrumb>
		</div>

		<div class="container">
			<!-- <time-table ref="timeTable"></time-table> -->
			<table class="timetable">
				<thead>
					<tr>
						<th>时间</th>
						<th> 周一 </th>
						<th> 周二 </th>
						<th> 周三 </th>
						<th> 周四 </th>
						<th> 周五 </th>
						<th> 周六 </th>
						<th> 周日 </th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="(xxx, courseIndex) in tableData[0]['children']" :key="courseIndex">
						<td><span style="font-weight: bold;">第{{list[courseIndex]}}节</span></td>
						<td  v-for="(item, Index) in tableData" :key="Index">
							<span style="font-size: 16px; font-weight: bold;">{{item['children'][courseIndex].courseName}}</span>
							<br>
							<span>{{item['children'][courseIndex].courseLocation}}</span>
						</td>
						
					</tr>
					

				</tbody>
			</table>
		</div>
	</div>
</template>

<script>
	import axios from 'axios'
	export default {
		data() {
			return {
				tableData: [{
					title: "周一",
					children: []
				},
				{
					title: "周二",
					children: []
				},
				{
					title: "周三",
					children: []
				},
				{
					title: "周四",
					children: []
				},
				{
					title: "周五",
					children: []
				},
				{
					title: "周六",
					children: []
				},
				{
					title: "周日",
					children: []
				},
				
				
				],
				list:["一","二","三","四","五","六","七","八","九","十"]
			}
		},
		methods:{
			getList() {
			  var that = this;
			  var tNum = JSON.parse(localStorage.getItem("username"));
			  axios.get("http://localhost:8085/teacher/course/list?tNum="+tNum).then(function (response){
					console.log(response.data);
					if(response.data.code==0){
						console.log(response.data.data);
						var data = response.data.data;
						that.tableData=[];
						for(var j=0;j<7;j++){
							that.tableData.push({"children":[],"title":""});
							for(var k=0;k<10;k++){
								that.tableData[j]["children"].push({"courseName":"","courseLocation":""});
							}
						}
						
						for(var i=0;i<data.length;i++){
							const time1 = data[i].courseTime;
							const time1str = time1.split("-");
							let data1 = parseInt(time1str[0]);
							let data2 = parseInt(time1str[1]);
							let data3 = parseInt(time1str[2]);
							console.log(data1);
							console.log(data2);
							console.log(data3);
							that.tableData[data1-1]["children"][data2-1]["courseName"]=data[i]["courseName"];
							that.tableData[data1-1]["children"][data2-1]["courseLocation"]=data[i]["courseLocation"];
							if(data3==2){
								that.tableData[data1-1]["children"][data2]["courseName"]=data[i]["courseName"];
								that.tableData[data1-1]["children"][data2]["courseLocation"]=data[i]["courseLocation"];
							}
							if(data3==3){
								that.tableData[data1-1]["children"][data2]["courseName"]=data[i]["courseName"];
								that.tableData[data1-1]["children"][data2]["courseLocation"]=data[i]["courseLocation"];
								that.tableData[data1-1]["children"][data2+1]["courseName"]=data[i]["courseName"];
								that.tableData[data1-1]["children"][data2+1]["courseLocation"]=data[i]["courseLocation"];
							}
								
							if(data3==4){
								that.tableData[data1-1]["children"][data2]["courseName"]=data[i]["courseName"];
								that.tableData[data1-1]["children"][data2]["courseLocation"]=data[i]["courseLocation"];
								that.tableData[data1-1]["children"][data2+1]["courseName"]=data[i]["courseName"];
								that.tableData[data1-1]["children"][data2+1]["courseLocation"]=data[i]["courseLocation"];
								that.tableData[data1-1]["children"][data2+2]["courseName"]=data[i]["courseName"];
								that.tableData[data1-1]["children"][data2+2]["courseLocation"]=data[i]["courseLocation"];
							}
							
							
						}
						console.log(that.tableData);
					}else{
						alert("查询错误")
					}
				});
			}
		},
		created() {
		  this.getList();
		},
	}
</script>


<style scoped>
table, td, th {  
  border: 3px solid #fff;
  text-align: center;
}
thead{
	background-color: #67a1ff;
	
}
th{
	color: #fff;
	line-height: 17px;
	font-weight: normal;
}
table {
  border-collapse: collapse;
  width: 100%;
}

tbody{
	background-color: #eaf2ff;
}
td {
  color: #677998;
  height: 60px; 
  
}	
th,td{
	width: 60px;
	padding: 2px 2px;
	font-size: 12px;
}

</style>
