package com.rainng.coursesystem.SCN.course.Bean;

import lombok.Data;

@Data
public class Assess {
    private int assess_id;//课程评价id
    private int assess_course_id;//课程id
    private int assess_student_id;//学生id
    private int assess_teacher_id;//教师id
    private int flag;//删除标识
    private int assess_ability;//教学能力
    private int assess_effect;//教学效果
    private int assess_content;//课程内容

}
