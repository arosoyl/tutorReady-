package com.example.tutorready;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Header;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity3<RequestParams, AsyncHttpClient> extends AppCompatActivity {


    ImageView imageView, imageViewCV;

    Button signup, cancel, chooseAvatar, chooseCV ;

    RadioGroup radioGroupGender, radioGroupRole;
    RadioButton radioFemale,radioMale, radioTutor, radioStudent;

    LinearLayout layoutChooseCV;

    EditText edtUsername, edtPass, edtRepass, edtEmail, edtPhone,edtAddress,edtBirthday, edtFullname;

    String username, fullname ,email , phone, pass ,repass, address ,birthday, gender, role,picture,CV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        layoutChooseCV= findViewById(R.id.layout_chooseCV);

        imageViewCV = findViewById(R.id.imageViewCV);

        imageView = findViewById(R.id.imgView);
        imageView.setImageResource(R.drawable.avatardefaulticon);

        edtUsername = findViewById(R.id.edt_username);
        edtEmail = findViewById(R.id.edt_email);
        edtFullname = findViewById(R.id.edt_fullname);
        edtAddress = findViewById(R.id.edt_address);
        edtPhone = findViewById(R.id.edt_phonenumber);
        edtBirthday = findViewById(R.id.edt_birthday);
        edtPass = findViewById(R.id.edt_pass);
        edtRepass = findViewById(R.id.edt_repass);



        initControl();

        radioGroupRole = findViewById(R.id.radioGroup_role);
        radioTutor = findViewById(R.id.radio_tutor);
        radioStudent = findViewById(R.id.radio_student);
        role = "TUTOR";
        gender = "Nu";

        radioGroupRole.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_tutor:
                        role = "TUTOR";
                        Toast.makeText(MainActivity3.this,"Bạn đã chọn " + radioTutor.getText(),Toast.LENGTH_SHORT).show();
                        layoutChooseCV.setVisibility(View.VISIBLE);
                        //selectedCV();
                        break;
                    case R.id.radio_student:
                        role = "STUDENT";
                        Toast.makeText(MainActivity3.this,"Bạn đã chọn " +radioStudent.getText(),Toast.LENGTH_SHORT).show();
                        layoutChooseCV.setVisibility(View.GONE);
                        break;
                }
            }
        });

        radioGroupGender = findViewById(R.id.radioGroup_gender);
        radioFemale = findViewById(R.id.radio_female);
        radioMale = findViewById(R.id.radio_male);

        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radio_female:
                        gender = "Nu";
                        Toast.makeText(MainActivity3.this,"Bạn đã chọn giới tính "+radioFemale.getText(),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_male:
                        gender = "Nam";
                        Toast.makeText(MainActivity3.this,"Bạn đã chọn giới tính "+radioMale.getText(),Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        chooseAvatar = findViewById(R.id.btn_avatar);
        chooseAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity3.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });


        signup = findViewById(R.id.btn_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = edtUsername.getText().toString().trim();
                email = edtEmail.getText().toString().trim();
                fullname = edtFullname.getText().toString().trim();
                address = edtAddress.getText().toString().trim();
                phone = edtPhone.getText().toString().trim();
                birthday = edtBirthday.getText().toString().trim();
                pass = edtPass.getText().toString().trim();
                repass = edtRepass.getText().toString().trim();

                //Toast.makeText(MainActivity3.this,"HEY "+ username,Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity3.this, "Hello", Toast.LENGTH_SHORT).show();

                if (username.equals("")) {
                    Toast.makeText(MainActivity3.this, "Vui lòng không để trống tên đăng nhập ", Toast.LENGTH_SHORT).show();
                } else if (email.equals("")) {
                    Toast.makeText(MainActivity3.this, "Vui lòng không để trống email ", Toast.LENGTH_SHORT).show();
                } else if (fullname.equals("")) {
                    Toast.makeText(MainActivity3.this, "Vui lòng không để trống tên đầy đủ ", Toast.LENGTH_SHORT).show();
                } else if (phone.equals("")) {
                    Toast.makeText(MainActivity3.this, "Vui lòng không để trống số điện thoại", Toast.LENGTH_SHORT).show();
                } else if (birthday.equals("")){
                    Toast.makeText(MainActivity3.this, "Vui lòng không để trống ngày sinh", Toast.LENGTH_SHORT).show();
                } else if(address.equals("")){
                    Toast.makeText(MainActivity3.this, "Vui lòng không để trống địa chỉ", Toast.LENGTH_SHORT).show();
                } else if(pass.equals("")){
                    Toast.makeText(MainActivity3.this, "Vui lòng không để trống mật khẩu ", Toast.LENGTH_SHORT).show();
                }
                else if(repass.equals("")){
                    Toast.makeText(MainActivity3.this, "Vui lòng không để trống nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(repass)){
                    Toast.makeText(MainActivity3.this, "Mật khẩu xác nhận không khớp với mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (role.equals("STUDENT")) {
                    signupStudent(username,pass,email,fullname,birthday,phone,address,picture,gender);
                }
                else {
                    signup(username,pass,email,fullname,birthday,phone,address,picture,gender,role,CV);
                }
            }
        });

        chooseCV = findViewById(R.id.btn_cv);
        chooseCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity3.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });

        cancel = findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtUsername.setText("");
                edtEmail.setText("");
                edtFullname.setText("");
                edtAddress.setText("");
                edtPhone.setText("");
                edtBirthday.setText("");
                edtPass.setText("");
                edtRepass.setText("");
            }
        });





    }

    private void signupStudent(String username,String pass, String email, String fullname, String birthday, String phone, String address, String picture, String gender) {

        Toast.makeText(MainActivity3.this, "HEY: "+username + email+fullname+gender, Toast.LENGTH_SHORT).show();

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        final String url = "https://tutorready.herokuapp.com/api/auth/register";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity3.this, "Đăng ký thành công!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(MainActivity2.this, "Đăng nhập thất bại !!!" +error.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity3.this, "Tên tài khoản hoặc email đã tồn tại. Vui lòng sử dụng tài khoản khác !!!", Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password",pass);
                params.put("email",email);
                params.put("fullname",fullname);
                params.put("birthday",birthday);
                params.put("phone",phone);
                params.put("address",address);
                params.put("gender",gender);
                params.put("picture","http://res.cloudinary.com/dlmrhb1yv/image/upload/v1627850341/niqolp7ajupwoavjg3fm.jpg");
                params.put("role","STUDENT");
                return params;
            }
        };

        requestQueue.add(request);



    }

    private void signup(String username, String pass, String email, String fullname, String birthday, String phone, String address, String picture, String gender, String role, String cv) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        final String url = "https://tutorready.herokuapp.com/api/auth/register";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity3.this, "Đăng ký thành công!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(MainActivity2.this, "Đăng nhập thất bại !!!" +error.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity3.this, "Tên tài khoản hoặc email đã tồn tại. Vui lòng sử dụng tài khoản khác !!!", Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password",pass);
                params.put("email",email);
                params.put("fullname",fullname);
                params.put("birthday",birthday);
                params.put("phone",phone);
                params.put("address",address);
                params.put("gender",gender);
                params.put("picture","http://res.cloudinary.com/dlmrhb1yv/image/upload/v1627850341/niqolp7ajupwoavjg3fm.jpg");
                params.put("role","TUTOR");
                params.put("CV","CV");
                return params;
            }
        };
        requestQueue.add(request);
    }


    private void initControl() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.signup);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.login:
                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.signup:
                        startActivity(new Intent(getApplicationContext(), MainActivity3.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.fee:
                        startActivity(new Intent(getApplicationContext(),MainActivity4.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}