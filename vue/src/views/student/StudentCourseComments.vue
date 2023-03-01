<template>
  <div class="course-select-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-fa fa-fort-awesome"></i> 课程评价
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div id="assess">
      <el-tabs v-model="assess.courseName" @tab-click="handleClick">
        <el-tab-pane :label="item.courseName" v-for="(item,i) in assess" :key="i">
          <div id="content">
            <span>课程名:&nbsp;</span>{{item.courseName}}<br><br>
            <span>教&nbsp;&nbsp;&nbsp;师:&nbsp;</span>{{item.teacherName}}<br><br>
            <span>教学能力:</span><br>
            <el-rate
                v-model="value.v1"
                show-text
                :texts="texts">
            </el-rate><br>
            <span>教学效果:</span><br>
            <el-rate
                v-model="value.v2"
                show-text
                :texts="texts">
            </el-rate><br>
            <span>课程内容:</span><br>
            <el-rate
                v-model="value.v3"
                show-text
                :texts="texts">
            </el-rate><br><br>

            <el-button type="primary" @click="UploadAssess(item.id)" style="margin-left: 50px">确定</el-button>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      value: {
        v1:null,
        v2:null,
        v3:null
      },
      texts:['极差', '失望', '一般', '满意', '非常满意'],
      assess:[],
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      let id=this.$store.state.status.userId
      request({
        method: "POST",
        url: "/GetCourse",
        data: id
      }).then((res) => {
        if(res.code==0){
          console.log(res.data)
          this.assess=res.data
        }
      })
    },
    handleClick() {
      this.value.v1=null
      this.value.v2=null
      this.value.v3=null
    },
    UploadAssess(id){
      if(this.value.v1==null || this.value.v2==null || this.value.v3==null){
        this.$message({
          showClose: true,
          message: "请评价",
          type: 'success',
          duration:1500
        });
        return
      }
      var info={id:id,ability:this.value.v1,effect:this.value.v2,content:this.value.v3}
      request({
        method: "POST",
        url: "/addAssess",
        data: info
      }).then((res) => {
        if(res.code==0){
          this.$message({
            showClose: true,
            message: res.msg,
            type: 'success',
            duration:1500
          });
          let index = this.assess.findIndex(item => {
            if (item.id == id) {
              return true
            }
          })
          this.assess.splice(index,1)
          this.handleClick()
        }
      })

    }
  }
}
</script>

<style scoped>
#assess{
  margin-left: 140px;
  width: 800px;
  height: 550px;
  z-index: 1000;
  border: 1px whitesmoke;
  box-shadow: 0px 5px 8px 8px #e5e5e5;
}
#content{
  margin-left: 50px;
}
#content span{
  font-size: 18px;
}

/deep/.el-rate{
  margin-left: 50px;
}
/deep/#tab-0{
  padding-left: 10px;
}
/deep/.el-tabs__item{
  padding: 0 10px;
}
</style>