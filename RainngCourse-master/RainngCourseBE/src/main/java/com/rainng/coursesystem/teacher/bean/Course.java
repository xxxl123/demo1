package com.rainng.coursesystem.teacher.bean;

import java.util.Date;

public class Course {
    private int courseId,courseTeacherId,courseGrade,courseCredit,courseSelectedCount,courseMaxSize;
    private String courseName,courseTime,courseLocation,courseExameLocation;
    private Date courseExameData;

    public Course() {
    }

    public Course(int courseId, int courseTeacherId, int courseGrade, int courseCredit, int courseSelectedCount, int courseMaxSize, String courseName, String courseTime, String courseLocation, String courseExameLocation, Date courseExameData) {
        this.courseId = courseId;
        this.courseTeacherId = courseTeacherId;
        this.courseGrade = courseGrade;
        this.courseCredit = courseCredit;
        this.courseSelectedCount = courseSelectedCount;
        this.courseMaxSize = courseMaxSize;
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.courseLocation = courseLocation;
        this.courseExameLocation = courseExameLocation;
        this.courseExameData = courseExameData;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseTeacherId() {
        return courseTeacherId;
    }

    public void setCourseTeacherId(int courseTeacherId) {
        this.courseTeacherId = courseTeacherId;
    }

    public int getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(int courseGrade) {
        this.courseGrade = courseGrade;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
    }

    public int getCourseSelectedCount() {
        return courseSelectedCount;
    }

    public void setCourseSelectedCount(int courseSelectedCount) {
        this.courseSelectedCount = courseSelectedCount;
    }

    public int getCourseMaxSize() {
        return courseMaxSize;
    }

    public void setCourseMaxSize(int courseMaxSize) {
        this.courseMaxSize = courseMaxSize;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseLocation() {
        return courseLocation;
    }

    public void setCourseLocation(String courseLocation) {
        this.courseLocation = courseLocation;
    }

    public String getCourseExameLocation() {
        return courseExameLocation;
    }

    public void setCourseExameLocation(String courseExameLocation) {
        this.courseExameLocation = courseExameLocation;
    }

    public Date getCourseExameData() {
        return courseExameData;
    }

    public void setCourseExameData(Date courseExameData) {
        this.courseExameData = courseExameData;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseTeacherId=" + courseTeacherId +
                ", courseGrade=" + courseGrade +
                ", courseCredit=" + courseCredit +
                ", courseSelectedCount=" + courseSelectedCount +
                ", courseMaxSize=" + courseMaxSize +
                ", courseName='" + courseName + '\'' +
                ", courseTime='" + courseTime + '\'' +
                ", courseLocation='" + courseLocation + '\'' +
                ", courseExameLocation='" + courseExameLocation + '\'' +
                ", courseExameData=" + courseExameData +
                '}';
    }
}
