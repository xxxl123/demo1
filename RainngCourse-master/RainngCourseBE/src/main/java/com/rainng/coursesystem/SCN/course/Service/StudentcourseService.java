package com.rainng.coursesystem.SCN.course.Service;


import com.rainng.coursesystem.SCN.course.Bean.Response.Admin_output;
import com.rainng.coursesystem.SCN.course.Bean.Response.Export_output;
import com.rainng.coursesystem.SCN.course.Bean.Response.Studentcourse_output;
import com.rainng.coursesystem.SCN.course.Controller.Request.StudentcoursepageRequest;

import java.util.List;

public interface StudentcourseService {
    List<Studentcourse_output> page(StudentcoursepageRequest studentcoursepageRequest);

    List<Studentcourse_output> findStudentCourse(Integer id);

    int updStudentCourse(Studentcourse_output studentcourse_output);

    int delStudentCourse(Integer id);

    int delCourse(Integer[] id);

    int findAdmin(Admin_output admin_output);

    List<Export_output> list();
}
