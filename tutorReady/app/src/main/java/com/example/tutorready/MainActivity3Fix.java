package com.example.tutorready;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MainActivity3Fix extends AppCompatActivity {

    // ĐĂNG KÝ

    private static final int IMG_REQUEST = 21 ;
    private static final int IMG_REQUEST_CV = 1;

    Bitmap bitmap,bitmapCV;


    ImageView imageView, imageViewCV;

    Button signup, cancel, chooseAvatar, chooseCV ;

    RadioGroup radioGroupGender, radioGroupRole;
    RadioButton radioFemale,radioMale, radioTutor, radioStudent;

    LinearLayout layoutChooseCV, layoutInfo;

    EditText edtUsername, edtPass, edtRepass, edtEmail, edtPhone,edtAddress,edtBirthday, edtFullname;

    String username, fullname ,email , phone, pass ,repass, address ,birthday, gender, role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3fix);

        Truyen();

        initControl();

        selectedAvatar();
        //selectedCV();

        //selectedGender();
        //selectedRole();

        //selectedCancel();




        signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if ( username.equals("") || email.equals("") || fullname.equals("") || address.equals("") || phone.equals("") ||birthday.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(MainActivity3.this,"Mời bạn nhập đầy đủ thông tin cá nhân !!!",Toast.LENGTH_SHORT).show();
                else {
                    SignUp(username,email, fullname,address, phone, birthday, pass, repass,gender);
                }

                 */
                uploadBitmap(bitmap);

            }
        });






    }

    /*
    private void SignUp(String username, String email, String fullname, String address, String phone, String birthday, String pass, String repass, String gender) {
    }
    */

    private void Truyen() {

        layoutChooseCV = findViewById(R.id.layout_chooseCV);

        edtUsername = findViewById(R.id.edt_username);
        edtEmail = findViewById(R.id.edt_email);
        edtFullname = findViewById(R.id.edt_fullname);
        edtAddress = findViewById(R.id.edt_address);
        edtPhone = findViewById(R.id.edt_phonenumber);
        edtBirthday = findViewById(R.id.edt_birthday);
        edtPass = findViewById(R.id.edt_pass);
        edtRepass = findViewById(R.id.edt_repass);

        username = edtUsername.getText().toString().trim();
        email = edtEmail.getText().toString().trim();
        fullname = edtFullname.getText().toString().trim();
        address = edtAddress.getText().toString().trim();
        phone = edtPhone.getText().toString().trim();
        birthday = edtBirthday.getText().toString().trim();
        pass = edtPass.getText().toString().trim();
        repass = edtRepass.getText().toString().trim();


    }


    private void selectedRole() {

        radioGroupRole = findViewById(R.id.radioGroup_role);
        radioTutor = findViewById(R.id.radio_tutor);
        radioStudent = findViewById(R.id.radio_student);

        radioGroupRole.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_tutor:
                        role = "TUTOR";
                        Toast.makeText(MainActivity3Fix.this,"Bạn đã chọn " + radioTutor.getText(),Toast.LENGTH_SHORT).show();
                        layoutChooseCV.setVisibility(View.VISIBLE);
                        selectedCV();
                        break;
                    case R.id.radio_student:
                        role = "STUDENT";
                        Toast.makeText(MainActivity3Fix.this,"Bạn đã chọn " +radioStudent.getText(),Toast.LENGTH_SHORT).show();
                        layoutChooseCV.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    private void selectedGender() {
        radioGroupGender = findViewById(R.id.radioGroup_gender);
        radioFemale = findViewById(R.id.radio_female);
        radioMale = findViewById(R.id.radio_male);

        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radio_female:
                        gender = "Nữ";
                        Toast.makeText(MainActivity3Fix.this,"Bạn đã chọn giới tính nữ",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_male:
                        gender = "Nam";
                        Toast.makeText(MainActivity3Fix.this,"Bạn đã chọn giới tính nam",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void selectedCancel() {
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

    private void selectedCV() {
        imageViewCV = findViewById(R.id.imageViewCV);
        imageViewCV.setImageResource(R.drawable.hinhgiasu);

        chooseCV = findViewById(R.id.btn_cv);

        chooseCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                //startActivityForResult(Intent.createChooser(intent,"Chọn CV: "),IMG_REQUEST_CV);
            }
        });
    }

    private void selectedAvatar() {

        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hinhgiasu);

        chooseAvatar = findViewById(R.id.btn_avatar);

        chooseAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,IMG_REQUEST);
            }
        });
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null)
        {
            Uri path = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(path);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
                //uploadBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        /*
        if (requestCode == IMG_REQUEST_CV && resultCode == RESULT_OK && data != null)
        {
            Uri pathCV = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(pathCV);
                bitmapCV = BitmapFactory.decodeStream(inputStream);
                imageViewCV.setImageBitmap(bitmapCV);
                //uploadBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        */
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadBitmap(final Bitmap bitmap) {
        final String url_avatar = "http://192.168.1.5:5000/api/uploads";

        //our custom volley request
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url_avatar,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        Toast.makeText(getApplicationContext(), "TẢi Ảnh thành công", Toast.LENGTH_SHORT).show();

                        /*try {
                            JSONObject obj = new JSONObject(new String(response.data));

                            Toast.makeText(getApplicationContext(), obj.getString(""), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {


            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("image", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
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
                        startActivity(new Intent(getApplicationContext(), MainActivity3Fix.class));
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