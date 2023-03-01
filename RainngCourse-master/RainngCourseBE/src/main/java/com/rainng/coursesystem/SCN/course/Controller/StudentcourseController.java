package com.rainng.coursesystem.SCN.course.Controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.rainng.coursesystem.SCN.course.Bean.Response.Admin_output;
import com.rainng.coursesystem.SCN.course.Bean.Response.Export_output;
import com.rainng.coursesystem.SCN.course.Bean.Response.Studentcourse_output;
import com.rainng.coursesystem.SCN.course.Controller.Request.StudentcoursepageRequest;
import com.rainng.coursesystem.SCN.course.Service.StudentcourseService;
import com.rainng.coursesystem.SCN.course.utils.JsonResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
public class StudentcourseController {
    @Autowired
    private StudentcourseService studentcourseService;
    /**
     * 分页及搜索查询
     * */
    @GetMapping("/page")
    public JsonResult Page(StudentcoursepageRequest studentcoursepageRequest){
        PageHelper.startPage(studentcoursepageRequest.getPageNum(), studentcoursepageRequest.getPageSize());
        List<Studentcourse_output> list=studentcourseService.page(studentcoursepageRequest);
        PageInfo<Studentcourse_output> pageInfo =new PageInfo<>(list);
        System.out.println(pageInfo);
        return new JsonResult(pageInfo);
    }
    /**
     * 通过id获取对应信息
     * */
    @PostMapping("/findStudentCourse")
    public JsonResult findStudentCourse(@RequestBody Integer id){
        List<Studentcourse_output> list=studentcourseService.findStudentCourse(id);
        return new JsonResult(list);
    }
    /**
     * 判断管理员身份
     */
    @PostMapping("/findAdmin")
    public JsonResult findAdmin(@RequestBody Admin_output admin_output){
        int i=studentcourseService.findAdmin(admin_output);
        if(i>0){
            return new JsonResult(1,"验证成功");
        }
        return new JsonResult(-1,"验证失败");
    }
    /**
     * 更新
     * */
    @PostMapping("/updStudentCourse")
    public JsonResult updStudentCourse(@RequestBody Studentcourse_output studentcourse_output){
        int i=studentcourseService.updStudentCourse(studentcourse_output);
        if(i>0){
            return new JsonResult(1,"修改成功");
        }
        return new JsonResult(-1,"修改失败");
    }
    /**
     * 删除
     * */
    @PostMapping("/delStudentCourse")
    public JsonResult delStudentCourse(@RequestBody Integer id){
        int i=studentcourseService.delStudentCourse(id);
        if(i>0){
            return new JsonResult(1,"删除成功");
        }
        return new JsonResult(-1,"删除失败");
    }
    /**
     * 批量删除
     * */
    @PostMapping("/delCourse")
    public JsonResult delCourse(@RequestBody Integer[] id){
        int i=studentcourseService.delCourse(id);
        if(i>0){
            return new JsonResult(1,"删除成功");
        }
        return new JsonResult(-1,"删除失败");
    }
    /***
     * 文件下载
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<Export_output> list=studentcourseService.list();
        ExcelWriter writer= ExcelUtil.getWriter(true);
        writer.addHeaderAlias("studentNumber","学号");
        writer.addHeaderAlias("classGrade","年级");
        writer.addHeaderAlias("studentName","学生姓名");
        writer.addHeaderAlias("className","班级");
        writer.addHeaderAlias("courseName","课程名称");
        writer.addHeaderAlias("dailyScore","平时分");
        writer.addHeaderAlias("examScore","期末分");
        writer.addHeaderAlias("score","总分");
        writer.write(list,true);

        //设置浏览器格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("学生成绩表","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");

        ServletOutputStream out=response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
    }
}
