<template>
	<div>
		<div class="crumbs">
		  <el-breadcrumb separator="/">
		    <el-breadcrumb-item>
		      <i class="el-icon-fa fa-user-plus"></i> 选课控制
		    </el-breadcrumb-item>
		  </el-breadcrumb>
		</div>
		<el-button @click="openChoseCourse" icon="el-icon-plus">开启选课</el-button>
		<el-button @click="closeChoseCourse" icon="el-icon-minus">关闭选课</el-button>
		<br />
		<br />
	
		<el-button @click="openReturnCourse" icon="el-icon-plus">开启退课</el-button>
		<el-button @click="closeReturnCourse" icon="el-icon-minus">关闭退课</el-button>
	</div>
</template>

<script>
	
	import * as api from "../../api/admin/admin";
	export default {
		name: 'AdminChoseCourse',
		data(){
			return{}
		},
		methods:{
			openChoseCourse(){
				api.openChoseCourse().then(()=>{
					this.$message.success("开启选课成功!");
				}).catch(()=>{
					this.$message.success("开启选课失败!!!");
				});
			},
			closeChoseCourse(){
				api.cancelChoseCourse().then(()=>{
					this.$message.success("关闭选课成功!");
				}).catch(()=>{
					this.$message.success("关闭选课失败!!!");
				});
			},
			
			openReturnCourse(){
				api.openReturnCourse().then(()=>{
					this.$message.success("开启退课成功!");
				}).catch(()=>{
					this.$message.success("开启退课失败!!!");
				});
			},
			closeReturnCourse(){
				api.cancelReturnCourse().then(()=>{
					this.$message.success("关闭退课成功!");
				}).catch(()=>{
					this.$message.success("关闭退课失败!!!");
				});
			}
			
		}
		
	}
</script>

<style>
</style>