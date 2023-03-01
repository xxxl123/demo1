package com.rainng.coursesystem.SCN.course.Controller.Request;

import lombok.Data;

@Data
public class BaseRquest {
    private Integer pageNum=1;
    private Integer pageSize=5;
}
