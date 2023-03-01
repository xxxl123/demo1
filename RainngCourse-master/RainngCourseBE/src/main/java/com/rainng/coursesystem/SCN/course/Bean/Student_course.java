package com.rainng.coursesystem.SCN.course.Bean;

import lombok.Data;

@Data
public class Student_course {
    private int sc_id;//选课id
    private int sc_student_id;//学生id
    private int sc_course_id;//课程id
    private String sc_daily_score;//平时成绩
    private String sc_exam_score;//考试成绩
    private String sc_score;//总成绩
    private int assess_ability;//教学能力
    private int assess_effect;//教学效果
    private int assess_content;//课程内容
    private int flag;//表示删除
}
