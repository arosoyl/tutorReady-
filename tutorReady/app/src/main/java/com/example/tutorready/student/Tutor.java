package com.example.tutorready.student;

public class Tutor {

    public Tutor(String phone, String address, String id, String fullname, String email, String username, String birthday, String picture, Boolean gender) {
        this.phone = phone;
        this.address = address;
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.username = username;
        this.birthday = birthday;
        this.picture = picture;
        this.gender = gender;
    }

    String _id;
    String name;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Tutor(String phone, String address, String fullname) {
        this.phone = phone;
        this.address = address;
        this.fullname = fullname;
    }

    String phone, address,id,fullname, email,username,birthday, picture;
    Boolean gender;

    public Tutor(String _id, String name) {
        this._id = _id;
        this.name = name;
    }



    public Tutor() {

    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
