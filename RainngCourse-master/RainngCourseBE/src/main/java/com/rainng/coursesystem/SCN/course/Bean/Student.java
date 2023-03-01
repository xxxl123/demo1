package com.rainng.coursesystem.SCN.course.Bean;

import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private int student_id;//id
    private int student_class_id;//班级id
    private String student_number;//学号
    private String student_name;//姓名
    private String student_password;//密码
    private String student_email;//邮箱
    private Date student_birthday;//生日
    private int student_sex;//性别
    private Date student_last_login_time;//最后一次登录时间
    private int flag;//表示删除
}
