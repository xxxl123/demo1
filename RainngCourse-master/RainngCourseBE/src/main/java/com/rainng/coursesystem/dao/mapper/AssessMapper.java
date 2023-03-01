package com.rainng.coursesystem.dao.mapper;

import com.rainng.coursesystem.SCN.course.Bean.Response.Assess_output;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssessMapper {
    List<Assess_output> getCourse(Integer id);

    int addAssess(Assess_output assess_output);
}
