package com.rainng.coursesystem.teacher.service.impl;



import com.rainng.coursesystem.teacher.bean.Course;
import com.rainng.coursesystem.teacher.bean.StudentCourse;
import com.rainng.coursesystem.teacher.bean.ov.Gradeov;
import com.rainng.coursesystem.teacher.mapper.TeacherMapperTwo;
import com.rainng.coursesystem.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherImpl implements TeacherService {

    @Autowired
    private TeacherMapperTwo teacherMapper;



    @Override
    public int findTidBytNumber(String tNumber) {
        return teacherMapper.findTidBytNumber(tNumber);
    }

    @Override
    public List<Course> findCoureByTeacher(int tid) {

        return teacherMapper.findCoureByTeacher(tid);
    }

    @Override
    public List<StudentCourse> getGradePageCount(int teacherId, String courseName, String studentName) {
        return teacherMapper.getGradePageCount(teacherId,courseName,studentName);
    }

    @Override
    public Gradeov getStGrade(int cid) {
        return teacherMapper.getStGrade(cid);
    }

    @Override
    public int updateGrade(int scId, int scDailyScore, int scExameScore, int scScore) {
        return teacherMapper.updateGrade(scId,scDailyScore,scExameScore,scScore);
    }


}
