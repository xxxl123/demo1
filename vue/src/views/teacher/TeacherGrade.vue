<template>
	<div>
		<div>
		  <el-breadcrumb separator="/">
			<el-breadcrumb-item>
			  <i class="el-icon-fa fa-edit"></i> 成绩录入
			</el-breadcrumb-item>
		  </el-breadcrumb>
		 </div>
		
		<div class="container">
			<div class="query-form">
			  <el-row :gutter="20">
				<el-col :offset="15" :span="3">
				  <el-input
					placeholder="课程名"
					v-model="courseName"
				  />
				</el-col>
				<el-col :span="3">
				  <el-input 
					placeholder="学生名"
					v-model="studentName"
				  />
				</el-col>
				<el-col :span="3">
				  <el-button @click="query" icon="el-icon-search" type="primary" >搜索
				  </el-button>
				</el-col>
			  </el-row>
			</div>
			<br />
			<div class="table">
				<el-table :data="tableData" :key="length" stripe>
				  <el-table-column type="index" label="序号" prop="sId" />
				  <el-table-column label="课程名" prop="cName" />
				  <el-table-column label="学生姓名" prop="stName" />
				  <el-table-column label="日常分" prop="dScore" />
				  <el-table-column label="笔试分" prop="eScore" />
				  <el-table-column label="成绩" prop="score" />
				  <el-table-column align="center" label="操作" width="200px">
					<template slot-scope="scope">
					  <el-button
						@click="edit(scope.row.sId)"
						size="mini"
						type="success" >打分
					  </el-button>
					</template>
				  </el-table-column>
				</el-table>
			  </div>
			  
			  
			  
			<el-dialog :visible.sync="editing" title="编辑" width="30%">
				 <el-form label-width="70px" ref="form">
					 <el-form-item label="选课Id" style="display:none">
					   <el-input
					 	
						type="number"
					 	v-model="sId"
					   ></el-input>
					 </el-form-item>
					<el-form-item label="选课名称">
					  <el-input
						disabled
						v-model="cName"
					  ></el-input>
					</el-form-item>
					<el-form-item label="日常成绩">
					  <el-input type="number" v-model="dScore"></el-input>
					</el-form-item>
					<el-form-item label="笔试成绩">
					  <el-input type="number" v-model="eScore"></el-input>
					</el-form-item>
					考试 : 平时
					  <el-select v-model="command" placeholder="请选择" >
						<el-option value="a" label="考试:平时(6:4)">考试:平时(6:4)</el-option>
					    <el-option value="b" label="考试:平时(4:6)">考试:平时(4:6)</el-option>
						<el-option value="c" label="考试:平时(7:3)">考试:平时(7:3)</el-option>
						<el-option value="d" label="考试:平时(3:7)">考试:平时(3:7)</el-option>
						<el-option value="e" label="考试:平时(1:9)">考试:平时(1:9)</el-option>
						<el-option value="f" label="考试:平时(1:9)">考试:平时(1:9)</el-option>
						<el-option value="g" label="考试:平时(5:5)">考试:平时(5:5)</el-option>
					  </el-select>
					
					<br />
					<br />
					
					<el-form-item label="总成绩">
					  <el-input type="number" v-model="score" disabled></el-input>
					</el-form-item>
				  </el-form>
				  <span class="dialog-footer" slot="footer">
					<el-button @click="save(sId)" type="primary">确 定</el-button>
					<el-button @click="editing = false">取 消</el-button>
				  </span>
			</el-dialog>
			<br />
			<el-row justify="center" type="flex">
			<el-pagination
			  background
							@current-change="handleCurrentChange"
							:current-page="currentPage"
			  layout="prev, pager, next"
							:page-size="pagesize"
			  :total="total">
			</el-pagination>
			</el-row>
		</div>
	</div>
</template>

<script>
import axios from "axios"
	export default {
		name: "TeacherGrade",
		  data() {
		    return {
			  courseName:"",
			  studentName:"",
		      tableData: [],
			  //gradeList:[],
			  dScore:0,
			  eScore:0,
			  score:0,
			  cName:"",
			  sId:0,
			  command:"a",
		      //pageSize: api.pageSize,
		      //pageCount: 1,
		      //pageIndex: 1,
		      editing: false,
			  total:0,
			  pagesize:10,
			  currentPage:1,
			  length:0
		    };
		  },
		  methods:{
		      query(){
		  		var that = this;
				var userId=JSON.parse(localStorage.getItem("userId"));
		  		axios.post("http://localhost:8085/teacher/grade/list",{
					cId:userId,
		  			courseName:that.courseName,
		  			studentName:that.studentName
		  		}).then(function (response){
		  			console.log(response.data);
					console.log(response.data.data.length);
					that.total=response.data.data.length;
		  			if(response.data.code==0){
		  				that.tableData = response.data.data;
		  			}else{
		  				alert("查询错误")
		  			}
		  		});
		      },
			  edit(id){
				  console.log(id);
				  /* axios.post("http://localhost:8085/teacher/grade/getStGrade",{
					  cId:1
				  }).then(function(response){
					  alert("成功");
				  }); */
				  var that = this;
				  
				  axios.post("http://localhost:8085/teacher/grade/getStGrade",{
				  	cId:id
				  }).then(function (response){
				  	console.log(response.data.data);
				  	if(response.data.code==0){
						that.editing = true;
						that.dScore = response.data.data.dScore;
						that.eScore = response.data.data.eScore;
						that.score = response.data.data.score;
						that.cName = response.data.data.cName;
						that.sId = response.data.data.sId;
				  	}else{
				  		alert("查询错误")
				  	}
				  });
			  },
			  save(cid){
				  console.log(cid);
				  console.log(this.dScore);
				  console.log(this.eScore);
				  console.log(this.score);
				   
				   var that = this;
				   
				   axios.post("http://localhost:8085/teacher/grade/update",{
				   	scId:cid,
					scDailyScore:this.dScore,
					scExameScore:this.eScore,
					scScore:this.score
				   }).then(function (response){
				   	console.log(response.data);
				   	if(response.data.code==0){
						that.$message({
							showClose: true,
							message:'成功',
							type: 'success',
							center: true,
							duration: 1500
						});
						
						that.query();
						that.editing = false;
				   	}else{
				   		alert("查询错误")
				   	}
				   });
				   
			  },
			  handleCurrentChange(val){
				  this.query(); 
				  setTimeout(()=>{
					  var gradeList = this.tableData;
					  
					  this.currentPage = val;
					  var apage = (val-1)*this.pagesize;
					  var bpage = val*this.pagesize;
					  if(bpage>this.total){
					  					  bpage = this.total;
					  }
					  this.tableData=[];
					  this.tableData = gradeList.slice(apage,bpage)
					  this.length = this.tableData.length;
					  console.log("==========");
					  console.log(this.length);
					  console.log(this.tableData);
					  console.log("==========");
				  },200) 
				  
				  
				  
			  },
			  getUserWork(){
				  var gradeList = this.tableData;
				  
				  var apage = 0;
				  var bpage = this.pagesize;
				  if(bpage>this.total){
				  	 bpage = this.total;
				  }
				  this.tableData=[];
				  this.tableData = gradeList.slice(apage,bpage)
				  console.log(this.tableData);
				  
			  } 
		    },
			created(){
			    this.query();
				setTimeout(()=>{
					this.getUserWork();
				},1000)
				
			},
			watch:{
				dScore: function(){
					if(this.dScore>100 || this.dScore<0){
						this.$message({
							showClose: true,
							message:'分数需要在0-100之间',
							type: 'warning',
							center: true,
							duration: 1500
						});
					}else{
						if(this.command == "a"){
							this.score = Math.trunc(this.eScore*0.6+this.dScore*0.4);
						}
						if(this.command == "b"){
							this.score = Math.trunc(this.eScore*0.4+this.dScore*0.6);
						}
						if(this.command == "c"){
							this.score = Math.trunc(this.eScore*0.7+this.dScore*0.3);
						}
						if(this.command == "d"){
							this.score = Math.trunc(this.eScore*0.3+this.dScore*0.7);
						}
						if(this.command == "e"){
							this.score = Math.trunc(this.eScore*0.1+this.dScore*0.9);
						}
						if(this.command == "f"){
							this.score = Math.trunc(this.eScore*0.9+this.dScore*0.1);
						}
						if(this.command == "g"){
							this.score = Math.trunc(this.eScore*0.5+this.dScore*0.5);
						}			
					}
					
								
				},
				eScore: function(){
					if(this.eScore>100 || this.eScore<0){
						this.$message({
							showClose: true,
							message:'分数需要在0-100之间',
							type: 'warning',
							center: true,
							duration: 1500
						});
					}else{
						if(this.command == "a"){
							this.score = Math.trunc(this.eScore*0.6+this.dScore*0.4);
						}
						if(this.command == "b"){
							this.score = Math.trunc(this.eScore*0.4+this.dScore*0.6);
						}
						if(this.command == "c"){
							this.score = Math.trunc(this.eScore*0.7+this.dScore*0.3);
						}
						if(this.command == "d"){
							this.score = Math.trunc(this.eScore*0.3+this.dScore*0.7);
						}
						if(this.command == "e"){
							this.score = Math.trunc(this.eScore*0.1+this.dScore*0.9);
						}
						if(this.command == "f"){
							this.score = Math.trunc(this.eScore*0.9+this.dScore*0.1);
						}
						if(this.command == "g"){
							this.score = Math.trunc(this.eScore*0.5+this.dScore*0.5);
						}			
					}
				},
				command: function(){
					if(this.command == "a"){
						this.score = Math.trunc(this.eScore*0.6+this.dScore*0.4);
					}
					if(this.command == "b"){
						this.score = Math.trunc(this.eScore*0.4+this.dScore*0.6);
					}
					if(this.command == "c"){
						this.score = Math.trunc(this.eScore*0.7+this.dScore*0.3);
					}
					if(this.command == "d"){
						this.score = Math.trunc(this.eScore*0.3+this.dScore*0.7);
					}
					if(this.command == "e"){
						this.score = Math.trunc(this.eScore*0.1+this.dScore*0.9);
					}
					if(this.command == "f"){
						this.score = Math.trunc(this.eScore*0.9+this.dScore*0.1);
					}
					if(this.command == "g"){
						this.score = Math.trunc(this.eScore*0.5+this.dScore*0.5);
					}
				}
			},
			
			
	}
</script>

<style scoped>
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>