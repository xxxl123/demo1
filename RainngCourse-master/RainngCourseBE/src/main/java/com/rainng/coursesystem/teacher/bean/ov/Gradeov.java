package com.rainng.coursesystem.teacher.bean.ov;

public class Gradeov {
    private int sId,dScore,eScore,score,tId;//sId是cId
    private String cName,stName;

    public Gradeov() {
    }

    public Gradeov(int sId, int dScore, int eScore, int score, int tId, String cName, String stName) {
        this.sId = sId;
        this.dScore = dScore;
        this.eScore = eScore;
        this.score = score;
        this.tId = tId;
        this.cName = cName;
        this.stName = stName;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int getdScore() {
        return dScore;
    }

    public void setdScore(int dScore) {
        this.dScore = dScore;
    }

    public int geteScore() {
        return eScore;
    }

    public void seteScore(int eScore) {
        this.eScore = eScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    @Override
    public String toString() {
        return "Gradeov{" +
                "sId=" + sId +
                ", dScore=" + dScore +
                ", eScore=" + eScore +
                ", score=" + score +
                ", tId=" + tId +
                ", cName='" + cName + '\'' +
                ", stName='" + stName + '\'' +
                '}';
    }
}
