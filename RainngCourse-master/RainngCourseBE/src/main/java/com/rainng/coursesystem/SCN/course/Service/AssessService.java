package com.rainng.coursesystem.SCN.course.Service;

import com.rainng.coursesystem.SCN.course.Bean.Response.Assess_output;

import java.util.List;

public interface AssessService {
    List<Assess_output> getCourse(Integer id);

    int addAssess(Assess_output assess_output);
}
