package com.rainng.coursesystem.teacher.bean.ov;

public class postGradeov {
    private int cId;
    private String courseName,StudentName;

    public postGradeov() {
    }

    public postGradeov(int cId, String courseName, String studentName) {
        this.cId = cId;
        this.courseName = courseName;
        StudentName = studentName;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    @Override
    public String toString() {
        return "postGradeov{" +
                "cId=" + cId +
                ", courseName='" + courseName + '\'' +
                ", StudentName='" + StudentName + '\'' +
                '}';
    }
}
