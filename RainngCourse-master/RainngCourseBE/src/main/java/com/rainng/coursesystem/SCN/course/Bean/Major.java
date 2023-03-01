package com.rainng.coursesystem.SCN.course.Bean;

import lombok.Data;

@Data
public class Major {
    private int major_id;//专业id
    private int major_department_id;//系id
    private String major_name;//专业名称
    private int flag;//表示删除
}
