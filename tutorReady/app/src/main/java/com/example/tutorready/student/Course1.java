package com.example.tutorready.student;

public class Course1 {

    String tutor;
    String _id;
    String students;
    String schedules;
    String subject;

    public Course1(String tutor, String _id, String students, String schedules, String subject, String grage, String address, String name) {
        this.tutor = tutor;
        this._id = _id;
        this.students = students;
        this.schedules = schedules;
        this.subject = subject;
        this.grage = grage;
        this.address = address;
        this.name = name;
    }

    String grage;
    String address;
    String name;

    public Course1() {

    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }

    public String getSchedules() {
        return schedules;
    }

    public void setSchedules(String schedules) {
        this.schedules = schedules;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrage() {
        return grage;
    }

    public void setGrage(String grage) {
        this.grage = grage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
