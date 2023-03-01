package com.rainng.coursesystem.SCN.course.Bean.Response;

import lombok.Data;

/**
 * 封装返回输出结果
 */
@Data
public class Studentcourse_output {
    private Integer id;
    private String courseName;
    private String studentName;
    private String className;
    private Integer dailyScore;
    private Integer examScore;
    private Integer score;
    private String studentNumber;
    private Integer classGrade;
}
