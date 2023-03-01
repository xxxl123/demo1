package com.rainng.coursesystem.teacher.bean;

public class StudentCourse {
    private int scId,scStudentId,scCourseId,scDailyScore,scExameScore,scScore;

    public StudentCourse() {
    }

    public StudentCourse(int scId, int scStudentId, int scCourseId, int scDailyScore, int scExameScore, int scScore) {
        this.scId = scId;
        this.scStudentId = scStudentId;
        this.scCourseId = scCourseId;
        this.scDailyScore = scDailyScore;
        this.scExameScore = scExameScore;
        this.scScore = scScore;
    }

    public int getScId() {
        return scId;
    }

    public void setScId(int scId) {
        this.scId = scId;
    }

    public int getScStudentId() {
        return scStudentId;
    }

    public void setScStudentId(int scStudentId) {
        this.scStudentId = scStudentId;
    }

    public int getScCourseId() {
        return scCourseId;
    }

    public void setScCourseId(int scCourseId) {
        this.scCourseId = scCourseId;
    }

    public int getScDailyScore() {
        return scDailyScore;
    }

    public void setScDailyScore(int scDailyScore) {
        this.scDailyScore = scDailyScore;
    }

    public int getScExameScore() {
        return scExameScore;
    }

    public void setScExameScore(int scExameScore) {
        this.scExameScore = scExameScore;
    }

    public int getScScore() {
        return scScore;
    }

    public void setScScore(int scScore) {
        this.scScore = scScore;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "scId=" + scId +
                ", scStudentId=" + scStudentId +
                ", scCourseId=" + scCourseId +
                ", scDailyScore=" + scDailyScore +
                ", scExameScore=" + scExameScore +
                ", scScore=" + scScore +
                '}';
    }
}
