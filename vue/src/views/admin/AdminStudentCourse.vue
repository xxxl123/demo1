<template>
  <div class="student-course-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-fa fa-edit"></i> 选课管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
    <div style="margin-bottom: 25px">
      <el-button plain @click="delAllopen()">批量删除</el-button>
      <el-button type="primary" @click="download()">导出<i class="el-icon-upload el-icon--right"></i></el-button>
      <el-input
          @change="load"
          placeholder="请输入搜索内容"
          style="width: 220px;margin-left: 800px"
          v-model="params.studentName"
          clearable>
        <el-button slot="append" icon="el-icon-search"></el-button>
      </el-input>
    </div>
    <el-table
        :data="user"
        style="width: 100%;margin-left: 30px" @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="80">
      </el-table-column>
      <el-table-column
          label="课程名"
          width="180">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.courseName}}</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column
          label="学生名"
          width="170">
        <template slot-scope="scope">
          <span>{{ scope.row.studentName}}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="班级"
          width="170">
        <template slot-scope="scope">
          <span>{{ scope.row.className}}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="平时分"
          width="160">
        <template slot-scope="scope">
          <span v-if="scope.row.dailyScore!=null">{{ scope.row.dailyScore}}</span>
          <span v-else>未打分</span>
        </template>
      </el-table-column>
      <el-table-column
          label="期末分"
          width="160">
        <template slot-scope="scope">
          <span v-if="scope.row.examScore!=null">{{ scope.row.examScore}}</span>
          <span v-else>未打分</span>
        </template>
      </el-table-column>
      <el-table-column
          label="总分"
          width="160">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium" type="success" v-if="scope.row.score>=60">{{ scope.row.score}}</el-tag>
            <el-tag size="medium" type="danger" v-if="scope.row.score<60&&scope.row.score!=null">{{ scope.row.score}}</el-tag>
            <el-tag size="medium" type="info" v-if="scope.row.score==null">0</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="open(scope.row.id,1)"></el-button>
          <el-button
              icon="el-icon-delete"
              circle
              type="danger"
              @click="delopen(scope.row.id,2)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--  分页  -->
    <el-pagination
        background
        :current-page="params.pageNum"
        :page-size="params.pageSize"
        layout="total,prev, pager, next, jumper"
        @current-change="handleCurrentChange"
        :total="total" style="margin-top: 20px;margin-left: 20px">
    </el-pagination>
    <!--  修改模态窗  -->
    <el-dialog title="修改数据" :visible.sync="dialogFormVisible">
      <el-form v-model="form" style="margin-left: 150px">
        <el-form-item label="学生名:" :label-width="formLabelWidth">
          <el-input style="width:200px" v-model="form.studentName" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="课程名:" :label-width="formLabelWidth">
          <el-input style="width:200px" v-model="form.courseName" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="平时分:" :label-width="formLabelWidth">
          <el-input style="width:200px" v-model="form.dailyScore" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="期末分:" :label-width="formLabelWidth">
          <el-input style="width:200px" v-model="form.examScore" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateStudentCourse(form)">确 定</el-button>
      </div>
    </el-dialog>
    <!--  验证管理员身份  -->
    <el-dialog title="身份验证" :visible.sync="dialogFormVisible2">
      <el-form :model="form2">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="form2.username" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input v-model="form2.password" autocomplete="off" type="password" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="findAdmin(form2)">确 定</el-button>
      </div>
    </el-dialog>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "Index",
  data(){
    return{
      courseid:null,
      kinds:null,
      user:null,
      dialogFormVisible:false,
      dialogFormVisible2:false,
      form: {
        id:"",
        studentName: '',
        courseName:"",
        dailyScore:0,
        examScore:0,
        score:0
      },
      form2:{
        id:null,
        username:"",
        password:"",
      },
      formLabelWidth: '120px',
      total:0,
      params:{
        pageNum:1,
        pageSize:6,
        studentName:"",
        className:""
      },
      sels:[]
    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      request.get("/page",{params:this.params}).then((res)=>{
        if(res.code==0){
          this.user=res.data.list
          this.total=res.data.total
        }
      })
    },
    delStudentCourse(id) {
      // 删除前的提示
      this.$confirm("确认删除记录吗?", "提示", {
        type: "warning",
      }).then(() => {
        request({
          method: "POST",
          url: "/delStudentCourse",
          data: id
        }).then((res) => {
          if (res.code == 1) {
            this.$message({
              showClose: true,
              message: res.msg,
              type: 'success',
              duration:1500
            });
            if(this.total<=(this.params.pageNum - 1) * this.params.pageSize){
              this.params.pageNum--
            }
            this.load()
          } else if (res.code == -1) {
            alert(res.msg)
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      })
    },
    delCourse() {
      // 删除前的提示
      this.$confirm("确认删除记录吗?", "提示", {
        type: "warning",
      }).then(() => {
        let ids = this.sels.map((item) => item.id);
        request({
          method: "POST",
          url: "/delCourse",
          data: ids
        }).then((res) => {
          if (res.code == 1) {
            this.$message({
              showClose: true,
              message: res.msg,
              type: 'success',
              duration:1500
            });
            if(this.total<=(this.params.pageNum - 1) * this.params.pageSize){
              this.params.pageNum--
            }
            this.load()
          } else if (res.code == -1) {
            alert(res.msg)
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      })
    },
    delopen(id,kinds){
      this.dialogFormVisible2=true
      this.courseid=id
      this.kinds=kinds
    },
    open(id,kinds) {
      this.dialogFormVisible2=true
      this.courseid=id
      this.kinds=kinds
    },
    delAllopen(){
      this.dialogFormVisible2=true
    },
    findAdmin(form2){
      var id=this.courseid
      request({
        method: "POST",
        url: "/findAdmin",
        data: form2
      }).then((res) => {
        if (res.code == 1) {
          this.dialogFormVisible2=false
          this.form2.id=null
          this.form2.username=""
          this.form2.password=""
          this.$message({
            showClose: true,
            message: res.msg,
            type: 'success',
            duration:700
          })
          setTimeout(()=>{
            if(this.kinds==2){
              this.delStudentCourse(id)
            }else if(this.kinds==1){
              request({
                method: "POST",
                url: "/findStudentCourse",
                data: id
              }).then((res) => {
                console.log(res.data[0])
                this.form = res.data[0]
                this.dialogFormVisible = true
              })
            }
            else{
              this.delCourse()
            }
          },800)
        }else if (res.code == -1) {
          this.$message({
            showClose: true,
            message: res.msg,
            type: 'warning',
          })
        }
      })
    },
    updateStudentCourse(form) {
      form.score=form.dailyScore*0.4+form.examScore*0.6
      console.log(form)
      request({
        method: "POST",
        url: "/updStudentCourse",
        data: form
      }).then((res) => {
        if (res.code == 1) {
          this.$message({
            showClose: true,
            message: res.msg,
            type: 'success',
            duration:1500
          });
          this.dialogFormVisible = false
          this.load()
        } else if (res.code == -1) {
          alert(res.msg)
        }
      })
    },
    download(){
      window.open("http://localhost:8085/export")
    },
    handleCurrentChange(pageNum){
      this.params.pageNum=pageNum
      this.load()
    },
    //获取选中的值
    handleSelectionChange(sels) {
      this.sels = sels;
      console.log("选中的值",sels.map((item) => item.id));
    }
  }
}
</script>

<style scoped>

</style>