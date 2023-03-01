package com.rainng.coursesystem.SCN.course.Service.ServiceImpl;

import com.rainng.coursesystem.SCN.course.Bean.Response.Assess_output;
import com.rainng.coursesystem.SCN.course.Service.AssessService;
import com.rainng.coursesystem.dao.mapper.AssessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssessServiceImpl implements AssessService {
    @Autowired
    AssessMapper assessMapper;
    @Override
    public List<Assess_output> getCourse(Integer id) {
        return assessMapper.getCourse(id);
    }

    @Override
    public int addAssess(Assess_output assess_output) {
        return assessMapper.addAssess(assess_output);
    }
}
