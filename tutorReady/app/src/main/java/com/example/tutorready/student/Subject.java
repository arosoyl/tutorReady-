package com.example.tutorready.student;

public class Subject {

    public Subject(){}

    public String getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(String idSubject) {
        this.idSubject = idSubject;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public Subject(String idSubject, String nameSubject) {
        this.idSubject = idSubject;
        this.nameSubject = nameSubject;
    }

    String idSubject, nameSubject;
}
