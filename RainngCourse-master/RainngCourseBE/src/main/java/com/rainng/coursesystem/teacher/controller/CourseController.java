package com.rainng.coursesystem.teacher.controller;


import com.rainng.coursesystem.teacher.bean.Course;
import com.rainng.coursesystem.teacher.service.impl.TeacherImpl;
import com.rainng.coursesystem.teacher.util.JsonResult;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/teacher/course")
@RestController("teacher_courseController")
@MapperScan("com.rainng.coursesystem.teacher.mapper")
public class CourseController{

    @Autowired
    private TeacherImpl service;


    @RequestMapping("/list")
    @ResponseBody
    public JsonResult list(String tNum) {
        System.out.println(tNum);
        int tNumber = service.findTidBytNumber(tNum);
        //String username = JSON.parse(localStorage.getItem("username"));

        //System.out.println(tid);
        List<Course> list = service.findCoureByTeacher(tNumber);
        if(list.size()<0){
            return new JsonResult(-1,"请求失败，没有查询到数据");
        }
        return new JsonResult(list,"请求成功");


    }


}
