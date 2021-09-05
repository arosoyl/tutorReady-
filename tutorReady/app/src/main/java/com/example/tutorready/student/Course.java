package com.example.tutorready.student;

import com.example.tutorready.tutor.Schedule;
import com.example.tutorready.tutor.Students;

public class Course {

    public Course(){}

    Tutor tutor;

    String schedule;
    Students students;
    Grade grade;
    Subject subject;
    String address;
    String idCourse;

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    String nameCourse;

    public Course(Tutor tutor, String schedule, Students students, Grade grade, Subject subject, String address, String idCourse, String nameCourse) {
        this.tutor = tutor;
        this.schedule = schedule;
        this.students = students;
        this.grade = grade;
        this.subject = subject;
        this.address = address;
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
    }










}



