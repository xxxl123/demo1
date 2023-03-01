package com.rainng.coursesystem.teacher.service;


import com.rainng.coursesystem.teacher.bean.Course;
import com.rainng.coursesystem.teacher.bean.StudentCourse;
import com.rainng.coursesystem.teacher.bean.ov.Gradeov;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherService {
    int findTidBytNumber(String tNumber);

    List<Course> findCoureByTeacher(int tid);

    List<StudentCourse> getGradePageCount(int teacherId, String courseName, String studentName);

    Gradeov getStGrade(int cid);

    int updateGrade(@Param("scId") int scId,@Param("scDailyScore") int scDailyScore,@Param("scExameScore") int scExameScore,@Param("scScore") int scScore);

}
