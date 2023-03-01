<template>
  <div class="course-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-fa fa-book"></i> 授课查询
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">
      <div class="table">
        <el-table :data="tableData" stripe>
          <el-table-column type="index" label="序号" prop="courseId" />
          <el-table-column label="课程名" prop="courseName" />
         <el-table-column label="年级" prop="courseGrade" />
          <el-table-column label="学分" prop="courseCredit" />
          <!-- <el-table-column label="上课时间" prop="courseTime" /> -->
		  <el-table-column
		    label="上课时间">
		    <template slot-scope="scope">
		  	  <el-tooltip :content="scope.row.courseTime" placement="top">
		  	  	<span>{{scope.row.courseTime | dataForm}}</span>
		  	  </el-tooltip>
		    </template>
		  </el-table-column>
		  
		  
          <el-table-column label="上课地点" prop="courseLocation" />
          <el-table-column label="选课人数" prop="courseSelectedCount" />
          <!--<el-table-column label="考试时间" prop="examDate" />-->
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
//import * as api from "../../api/teacher/course";
import axios from 'axios'

export default {
  name: "TeacherCourse",
  data() {
    return {
      tableData:[]
    };
  },
  methods: {
    getList() {
      var that = this;
	  var tNum = JSON.parse(localStorage.getItem("username"));
      axios.get("http://localhost:8085/teacher/course/list?tNum="+tNum).then(function (response){
			console.log(response.data);
			if(response.data.code==0){
				console.log(response.data);
				that.tableData = response.data.data;
			}else{
				alert("查询错误")
			}
		});
    }
  },
  created() {
    this.getList();
  },
  filters:{
  	dataForm(value){
  		const strlist = value.split("-");
		let data1;
		let data2;
		let data3;
		if(strlist[0] == "1"){
			data1 = "星期一";
		}
		if(strlist[0] == "2"){
			data1 = "星期二";
		}
		if(strlist[0] == "3"){
			data1 = "星期三";
		}
		if(strlist[0] == "4"){
			data1 = "星期四";
		}
		if(strlist[0] == "5"){
			data1 = "星期五";
		}
		if(strlist[0] == "6"){
			data1 = "星期六";
		}
		if(strlist[0] == "7"){
			data1 = "星期日";
		}
		
		if(strlist[1] == "1"){
			data2 = 1;
		}
		if(strlist[1] == "2"){
			data2 = 2;
		}
		if(strlist[1] == "3"){
			data2 = 3;
		}
		if(strlist[1] == "4"){
			data2 = 4;
		}
		if(strlist[1] == "2"){
			data2 = 2;
		}
		if(strlist[1] == "3"){
			data2 = 3;
		}
		if(strlist[1] == "4"){
			data2 = 4;
		}
		if(strlist[1] == "5"){
			data2 = 5;
		}
		if(strlist[1] == "6"){
			data2 = 6;
		}
		if(strlist[1] == "7"){
			data1 = 7;
		}
		if(strlist[1] == "8"){
			data2 = 8;
		}
		if(strlist[1] == "9"){
			data2 = 9;		
		}
		
		if(strlist[2] == "2"){
			data3 = data2 + 1;	
		}
		if(strlist[2] == "3"){
			data3 = data2 + 2;			
		}
		if(strlist[2] == "4"){
			data3 = data2 + 3;			
		}
		
  		let data = data1+" 第"+data2+"~"+data3+"节"
		return data;
  	}
  }
};
</script>

<style scoped></style>
