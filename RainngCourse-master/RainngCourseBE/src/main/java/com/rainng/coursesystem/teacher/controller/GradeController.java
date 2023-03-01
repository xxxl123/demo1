package com.rainng.coursesystem.teacher.controller;


import com.rainng.coursesystem.teacher.bean.StudentCourse;
import com.rainng.coursesystem.teacher.bean.ov.Gradeov;
import com.rainng.coursesystem.teacher.bean.ov.postGradeov;
import com.rainng.coursesystem.teacher.service.impl.TeacherImpl;
import com.rainng.coursesystem.teacher.util.JsonResult;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/teacher/grade")
@RestController
@MapperScan("com.rainng.coursesystem.teacher.mapper")
public class GradeController {

    @Autowired
    private TeacherImpl service;


    @RequestMapping("/list")
    @ResponseBody
    public JsonResult getPageCount(@RequestBody postGradeov info) {
        //int teacherId = service.findTidBytNumber("201711010001");
        System.out.println(info.getcId());
        List<StudentCourse> list = service.getGradePageCount(info.getcId(),info.getCourseName(),info.getStudentName());
        if(list.size()<0){
            return new JsonResult(-1,"请求失败，没有查询到数据");
        }
        return new JsonResult(list,"请求成功");
    }

    @RequestMapping("/getStGrade")
    @ResponseBody
    public JsonResult getStGrade(@RequestBody postGradeov info) {
        Gradeov stGrade = service.getStGrade(info.getcId());
        return new JsonResult(stGrade);
    }

    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@RequestBody StudentCourse stCourse){
        int i = service.updateGrade(stCourse.getScId(),stCourse.getScDailyScore(),stCourse.getScExameScore(),stCourse.getScScore());
        System.out.println(i);
        if(i>0){
           return new JsonResult();
        }
        else {
            return new JsonResult(-1,"失败");
        }

    }

}
