package com.example.tutorready.student;

public class Grade {

    public Grade() {

    }

    public String getIdGrade() {
        return idGrade;
    }

    public void setIdGrade(String idGrade) {
        this.idGrade = idGrade;
    }

    public String getNameGrade() {
        return nameGrade;
    }

    public void setNameGrade(String nameGrade) {
        this.nameGrade = nameGrade;
    }

    String idGrade;
    String nameGrade;
    public Grade(String idGrade, String nameGrade) {
        this.idGrade = idGrade;
        this.nameGrade = nameGrade;
    }


}
