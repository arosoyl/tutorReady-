package com.example.tutorready.tutor;

public class Students {

    String username, password, email, fullname, birthday, phone, address,role="STUDENT";

    public Students(String username, String password, String email, String fullname, String birthday, String phone, String address, String role, Boolean gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.gender = gender;
    }

    Boolean gender;



}
