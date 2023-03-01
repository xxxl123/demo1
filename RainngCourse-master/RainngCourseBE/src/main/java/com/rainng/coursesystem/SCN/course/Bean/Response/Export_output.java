package com.rainng.coursesystem.SCN.course.Bean.Response;

import lombok.Data;

@Data
public class Export_output {
    private String courseName;
    private String studentName;
    private String className;
    private Integer dailyScore;
    private Integer examScore;
    private Integer score;
    private String studentNumber;
    private Integer classGrade;
}
