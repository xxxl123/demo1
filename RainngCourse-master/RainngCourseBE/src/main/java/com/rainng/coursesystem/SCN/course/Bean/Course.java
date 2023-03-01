package com.rainng.coursesystem.SCN.course.Bean;

import lombok.Data;

import java.util.Date;

@Data
public class Course {
    private int course_id;//id
    private int course_teacher_id;//授课教师id
    private String course_name;//课程名称
    private int course_grade;//授课年级
    private String course_time;//上课时间
    private String course_location;//上课地点
    private int course_credit;//学分
    private int course_selected_count;//已选择数量
    private int course_max_size;//最大容量
    private Date course_exam_date;//考试时间
    private String course_exam_location;//考试地址
    private int flag;//表示删除
}
