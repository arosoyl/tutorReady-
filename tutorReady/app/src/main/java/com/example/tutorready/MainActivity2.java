package com.example.tutorready;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    EditText edt_username, edt_password;
    Button btn_login;
    String data_name, data_pass;

    ImageView img;
    CheckBox checkBox_save_pass;

    public static final String username = "username";
    public static final String password = "password";

    String id, role, token,birthday,address,picture,CV,email,user, phone, fullname,ava;
    Boolean gender;


    //Login
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initControl();

        img = findViewById(R.id.imageView);
        img.setImageResource(R.drawable.logo);


        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);

        checkBox_save_pass = findViewById(R.id.save_pass);

        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                data_name = edt_username.getText().toString().trim();
                data_pass = edt_password.getText().toString().trim();

                if ( data_name.length() == 0|| data_pass.length() == 0){
                    Toast.makeText(MainActivity2.this, "Mời bạn nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Toast.makeText(MainActivity2.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    /*Intent intent = new Intent(MainActivity2.this, ActivityStudent.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("token_user" ,token);
                    bundle.putString("id_user" ,id);
                    intent.putExtras(bundle);
                    startActivity(intent);*/
                    Login(data_name,data_pass);
                }
            }
        });

    }

    private void Login(String data_name, String data_pass) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Toast.makeText(MainActivity2.this, "Thông tin đã nhập " + data_name + data_pass, Toast.LENGTH_SHORT).show();

        final String url = "https://tutorready.herokuapp.com/api/auth/login";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity2.this, "Đăng nhập thành công!!!", Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    token = (String) jsonObject.getString("token");
                    String users = (String) jsonObject.getString("user");

                    JSONObject jsonObject1 = new JSONObject(users);
                    id = (String) jsonObject1.getString("_id");
                    role = (String) jsonObject1.getString("role");

                    user = (String) jsonObject1.getString("username");
                    email = (String) jsonObject1.getString("email");
                    fullname = (String) jsonObject1.getString("fullname");
                    birthday = (String) jsonObject1.getString("birthday");
                    phone = (String) jsonObject1.getString("phone");
                    address = (String) jsonObject1.getString("address");

                    gender = (Boolean) jsonObject1.getBoolean("gender");

                    picture = (String) jsonObject1.getString("picture");

                    if (role.equals("TUTOR"))
                    {
                        CV = (String) jsonObject1.getString("CV");
                    }

                    //Toast.makeText(MainActivity2.this, "Đăng !", Toast.LENGTH_SHORT).show();


                        /*
                        Toast.makeText(MainActivity2.this, "VẬY LÀ : " + id + role, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity2.this, ActivityStudent.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("token_user", token);
                        bundle.putString("id_user", id);

                        bundle.putString("username", user);
                        bundle.putString("email_user", email);
                        bundle.putString("fullname", fullname);
                        bundle.putString("birthday", birthday);
                        bundle.putString("phone", phone);
                        bundle.putString("address", address);
                        bundle.putBoolean("gender", gender);

                        //bundle.putString("");
                        // lấy ảnh về ntn
                        intent.putExtras(bundle);

                        startActivity(intent);


                        */
                    if (role.equals("STUDENT")) {

                        Intent intent = new Intent(MainActivity2.this, MainActivity5.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("token_user", token);
                        bundle.putString("id_user", id);

                        bundle.putString("username", user);
                        bundle.putString("email_user", email);
                        bundle.putString("fullname", fullname);
                        bundle.putString("birthday", birthday);
                        bundle.putString("phone", phone);
                        bundle.putString("address", address);
                        bundle.putBoolean("gender", gender);

                        bundle.putString("picture",picture);
                        //Toast.makeText(MainActivity2.this, "Mời bạn nhập đầy đủ thông tin" + picture, Toast.LENGTH_SHORT).show();

                        intent.putExtras(bundle);
                        startActivity(intent);


                            /*
                            Bundle bundle = new Bundle();

                            bundle.putString("token_user", token);
                            bundle.putString("id_user", id);
                            bundle.putString("username", user);
                            bundle.putString("email_user", email);
                            bundle.putString("fullname", fullname);
                            bundle.putString("birthday", birthday);
                            bundle.putString("phone", phone);
                            bundle.putString("address", address);
                            bundle.putBoolean("gender", gender);
                            bundle.putString("picture",ava);




                            //bundle.putSerializable("obj_tutor", tutor);


                            intent.putExtras(bundle);


                             */

                        startActivity(intent);
                    }
                    if (role.equals("ADMIN")) {
                        Intent intent = new Intent(MainActivity2.this, MainActivity7.class);

                        Bundle bundle = new Bundle();
                        bundle.putString("token_user", token);
                        bundle.putString("id_user", id);

                        bundle.putString("username", user);
                        bundle.putString("email_user", email);
                        bundle.putString("fullname", fullname);
                        bundle.putString("birthday", birthday);
                        bundle.putString("phone", phone);
                        bundle.putString("address", address);
                        bundle.putBoolean("gender", gender);
                        bundle.putString("picture",ava);
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                    if (role.equals("TUTOR")) {
                        Intent intent = new Intent(MainActivity2.this, MainActivity6.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("token_user", token);
                        bundle.putString("id_user", id);

                        bundle.putString("username", user);
                        bundle.putString("email_user", email);
                        bundle.putString("fullname", fullname);
                        bundle.putString("birthday", birthday);
                        bundle.putString("phone", phone);
                        bundle.putString("address", address);
                        bundle.putBoolean("gender", gender);

                        bundle.putString("picture",picture);
                        bundle.putString("CV",CV);
                        //Toast.makeText(MainActivity2.this, "Mời bạn nhập đầy đủ thông tin" + picture, Toast.LENGTH_SHORT).show();

                        intent.putExtras(bundle);
                        startActivity(intent);
                    }



                    //Toast.makeText(LoginActivity.this, "Đăng nhập thành công!!!" + user, Toast.LENGTH_LONG).show();
                    //Toast.makeText(LoginActivity.this, "Đăng nhập thành công!!!" + role, Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    //Toast.makeText(LoginActivity.this, "NOTTTTTTTTT!!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(MainActivity2.this, "Đăng nhập thất bại !!!" +error.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity2.this, "Mật khẩu không chính xác hoặc tài khoản chưa được đăng ký !!!", Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", data_name);
                params.put("password",data_pass);
                return params;
            }
        };
        requestQueue.add(request);
    }

    private void initControl() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.login);

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