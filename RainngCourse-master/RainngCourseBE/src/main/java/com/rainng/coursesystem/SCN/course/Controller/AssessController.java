package com.rainng.coursesystem.SCN.course.Controller;

import com.rainng.coursesystem.SCN.course.Bean.Response.Assess_output;
import com.rainng.coursesystem.SCN.course.Service.AssessService;
import com.rainng.coursesystem.SCN.course.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssessController {
    @Autowired
    AssessService assessService;
    @PostMapping("GetCourse")
    public JsonResult getcourse(@RequestBody Integer id){
        List<Assess_output> list=assessService.getCourse(id);
        return new JsonResult(list);
    }
    @PostMapping("addAssess")
    public JsonResult addAssess(@RequestBody Assess_output assess_output){
        int i=assessService.addAssess(assess_output);
        if(i>0){
            return new JsonResult(0,"评价成功");
        }
        return new JsonResult(-1,"评价失败");
    }
}
