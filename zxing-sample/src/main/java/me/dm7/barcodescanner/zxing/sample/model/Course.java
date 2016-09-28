package me.dm7.barcodescanner.zxing.sample.model;

import java.util.Date;

/**
 * Created by CarlosEmilio on 28/09/2016.
 */

public class Course {
    private String nameCourse;
    private Date dateCourse;

    public Course(String nameCourse) {
        this.nameCourse = nameCourse;
        dateCourse = new Date();

    }

    public Course(String nameCourse, Date dateCourse) {
        this.nameCourse = nameCourse;
        this.dateCourse = dateCourse;
    }


    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public Date getDateCourse() {
        return dateCourse;
    }

    public void setDateCourse(Date dateCourse) {
        this.dateCourse = dateCourse;
    }
}
