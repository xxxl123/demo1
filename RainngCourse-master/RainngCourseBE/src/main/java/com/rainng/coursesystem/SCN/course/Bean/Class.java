package com.rainng.coursesystem.SCN.course.Bean;

import lombok.Data;

@Data
public class Class {
    private int class_id;//班级id
    private int class_major_id;//专业id
    private int class_grade;//年级
    private String class_name;//班级名称
    private int flag;//表示删除
}
