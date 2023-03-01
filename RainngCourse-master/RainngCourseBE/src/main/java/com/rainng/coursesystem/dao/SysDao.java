package com.rainng.coursesystem.dao;

import com.rainng.coursesystem.dao.mapper.SysMapper;
import com.rainng.coursesystem.model.entity.SysEntity;
import org.springframework.stereotype.Repository;

@Repository
public class SysDao {

    private final SysMapper mapper;

    public SysDao(SysMapper mapper) {
        this.mapper = mapper;
    }

    public int update(SysEntity entity) {
        return mapper.updateById(entity);
    }
    public int getChoseCourseState(){
        return mapper.selectById(1).getOpenChoseCourse();
    }

    public int getCloseCourseState(){
        return mapper.selectById(1).getOpenCloseCourse();
    }
}

