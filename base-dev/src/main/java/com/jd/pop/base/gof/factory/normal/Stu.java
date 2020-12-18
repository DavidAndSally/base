package com.jd.pop.base.gof.factory.normal;

public class Stu {
    private String stuNo;
    private String stuName;

    public Stu() {
    }

    public Stu(String stuNo, String stuName) {
        this.stuNo = stuNo;
        this.stuName = stuName;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }
}