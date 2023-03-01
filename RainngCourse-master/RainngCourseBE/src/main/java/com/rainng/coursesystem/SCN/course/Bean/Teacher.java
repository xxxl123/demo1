package com.rainng.coursesystem.SCN.course.Bean;

import lombok.Data;

@Data
public class Teacher {
    private int teacher_id;//id
    private int teacher_department_id;//院系id;
    private String teacher_number;//工号
    private String teacher_name;//姓名
    private String teacher_password;//密码
    private int flag;//表示删除
}
