package com.rainng.coursesystem.SCN.course.Service.ServiceImpl;

import com.rainng.coursesystem.SCN.course.Bean.Response.Admin_output;
import com.rainng.coursesystem.SCN.course.Bean.Response.Export_output;
import com.rainng.coursesystem.SCN.course.Bean.Response.Studentcourse_output;
import com.rainng.coursesystem.SCN.course.Controller.Request.StudentcoursepageRequest;
import com.rainng.coursesystem.SCN.course.Service.StudentcourseService;
import com.rainng.coursesystem.dao.mapper.SCNStudentCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentcourseServiceImpl implements StudentcourseService {
    @Autowired
    SCNStudentCourseMapper SCNStudentCourseMapper;
    @Override
    public List<Studentcourse_output> page(StudentcoursepageRequest studentcoursepageRequest) {
        return SCNStudentCourseMapper.page(studentcoursepageRequest);
    }

    @Override
    public List<Studentcourse_output> findStudentCourse(Integer id) {
        return SCNStudentCourseMapper.findStudentCourse(id);
    }

    @Override
    public int updStudentCourse(Studentcourse_output studentcourse_output) {
        return SCNStudentCourseMapper.updStudentCourse(studentcourse_output);
    }

    @Override
    public int delStudentCourse(Integer id) {
        return SCNStudentCourseMapper.delStudentCourse(id);
    }

    @Override
    public int delCourse(Integer[] id) {
        return SCNStudentCourseMapper.delCourse(id);
    }

    @Override
    public int findAdmin(Admin_output admin_output) {
        return SCNStudentCourseMapper.findAdmin(admin_output);
    }

    @Override
    public List<Export_output> list() {
        return SCNStudentCourseMapper.list();
    }
}
