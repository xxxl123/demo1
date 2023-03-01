package com.rainng.coursesystem.SCN.course.Bean;

import lombok.Data;

@Data
public class Department {
    private int department_id;//系id
    private String department_name;//系名称
    private int flag;//表示删除
}
