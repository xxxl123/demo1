package com.rainng.coursesystem.SCN.course.Controller.Request;


import lombok.Data;

@Data
public class StudentcoursepageRequest extends BaseRquest{
    private String className;
    private String studentName;
    private String courseName;
}
