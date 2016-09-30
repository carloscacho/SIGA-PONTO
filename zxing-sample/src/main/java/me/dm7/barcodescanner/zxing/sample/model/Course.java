package me.dm7.barcodescanner.zxing.sample.model;

/**
 * Created by CarlosEmilio on 28/09/2016.
 */

public class Course {
    private int idCourse;
    private String nameCourse;
    private String dateCourse;

    public Course(String nameCourse) {
        this.nameCourse = nameCourse;

    }

    public Course(String nameCourse, String dateCourse) {
        this.nameCourse = nameCourse;
        this.dateCourse = dateCourse;
    }


    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getDateCourse() {
        return dateCourse;
    }

    public void setDateCourse(String dateCourse) {
        this.dateCourse = dateCourse;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }
}
