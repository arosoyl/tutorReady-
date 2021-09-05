package com.example.tutorready.tutor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutorready.MainActivity2;
import com.example.tutorready.MainActivity5;
import com.example.tutorready.MainActivity6;
import com.example.tutorready.MainActivity7;
import com.example.tutorready.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class TutorInfoFragment extends Fragment {

    EditText edtUsername, edtEmail, edtFullname, edtPhone,edtAddress,edtBirthday;

    RadioGroup radioGroupGender;
    RadioButton male,female;
    Boolean gender, gender1;

    Button btnEdit, btnCancel, btnChooseAva, btnChooseCV;

    String id, token, username,fullname, email,phone,address, img_cv, img_ava, birthday;

    String username1,fullname1, email1,phone1,address1, img_cv1, img_ava1, birthday1;

    ImageView CV,ava;

    public TutorInfoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info_tutor,container,false);

        MainActivity6 activity = (MainActivity6) getActivity();

        Anhxa(view);

        // Ánh xạ



        // Nhận dữ liệu
        id = activity.getId();
        token = activity.getToken();

        username = activity.getUser();
        email = activity.getEmail();
        phone = activity.getPhone();
        address = activity.getAddress();
        fullname = activity.getFullname();
        birthday = activity.getBirthday();

        img_ava = activity.getAva();
        img_cv = activity.getCV();

        //Toast.makeText(activity.getBaseContext(), "Mời bạn nhập đầy đủ thông tin" + username, Toast.LENGTH_SHORT).show();


        // Hiển thị thông tin
        edtUsername.setText(username);
        edtEmail.setText(email);
        edtFullname.setText(fullname);
        edtBirthday.setText(birthday);
        edtAddress.setText(address);
        edtPhone.setText(phone);




        gender = activity.getGender();
        radioGroupGender = view.findViewById(R.id.radioGroup_gender);
        female = view.findViewById(R.id.radio_female);
        male = view.findViewById(R.id.radio_male);


        RequestQueue queue = Volley.newRequestQueue(getContext());

        ImageRequest imageRequest = new ImageRequest(img_ava, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                ava.setImageBitmap(response);

            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ava.setImageResource(R.drawable.avatardefaulticon);

            }
        });
        queue.add(imageRequest);



        // Hiển thị CV


        ImageRequest imageRequest2 = new ImageRequest(img_cv, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                CV.setImageBitmap(response);

            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CV.setImageResource(R.drawable.hinhgiasu);

            }
        });
        queue.add(imageRequest2);


        if (gender) {
            female.setChecked(true);
            //Toast.makeText(activity.getBaseContext(), "Mời bạn nhập đầy đủ thông tin" + gender, Toast.LENGTH_SHORT).show();
        } else {
            male.setChecked(true);
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username1 = edtUsername.getText().toString().trim();
                email1 = edtEmail.getText().toString().trim();
                fullname1 = edtEmail.getText().toString().trim();
                phone1 = edtPhone.getText().toString().trim();
                address1 = edtAddress.getText().toString().trim();
                birthday1 = edtBirthday.getText().toString().trim();

                if (female.isChecked())
                {
                    gender1 = true ;
                }
                else gender1 = false;
                //Toast.makeText(getActivity(), "Mời bạn nhập đầy đủ thông tin" + gender1, Toast.LENGTH_SHORT).show();


                Edit(id, token, gender1, address1, phone1, username1, email1, fullname1, birthday1, img_ava, img_cv);
                //Toast.makeText(getActivity(), "Mời bạn nhập đầy đủ thông tin" + token, Toast.LENGTH_SHORT).show();



            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //showEdit(id,token);

                edtUsername.setText(username);
                edtEmail.setText(email);
                edtFullname.setText(fullname);
                edtBirthday.setText(birthday);
                edtAddress.setText(address);
                edtPhone.setText(phone);
                if (gender) {
                    female.setChecked(true);
                    //Toast.makeText(activity.getBaseContext(), "Mời bạn nhập đầy đủ thông tin" + gender, Toast.LENGTH_SHORT).show();
                } else {
                    male.setChecked(true);
                }

            }
        });



        return view;
    }

    private void showEdit(String id, String token) {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        String url_getUser = "https://tutorready.herokuapp.com/api/user/info" +id; //???

        StringRequest request = new StringRequest(Request.Method.POST, url_getUser, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String users = (String) jsonObject.getString("user");

                    JSONObject jsonObject1 = new JSONObject(users);

                    username = (String) jsonObject1.getString("username");
                    email = (String) jsonObject1.getString("email");
                    fullname = (String) jsonObject1.getString("fullname");
                    birthday = (String) jsonObject1.getString("birthday");
                    phone = (String) jsonObject1.getString("phone");
                    address = (String) jsonObject1.getString("address");

                    gender = (Boolean) jsonObject1.getBoolean("gender");

                    img_ava = (String) jsonObject1.getString("picture");
                    img_cv = (String) jsonObject1.getString("CV");


                } catch (JSONException e) {
                    //Toast.makeText(LoginActivity.this, "NOTTTTTTTTT!!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization","Bearer "+token);
                return headers;
            }

        };
        queue.add(request);


    }

    private void Anhxa(View view) {
        edtUsername = view.findViewById(R.id.edt_username);
        edtEmail = view.findViewById(R.id.edt_email);
        edtFullname = view.findViewById(R.id.edt_fullname);
        edtBirthday = view.findViewById(R.id.edt_birthday);
        edtAddress = view.findViewById(R.id.edt_address);
        edtPhone = view.findViewById(R.id.edt_phone);
        male = view.findViewById(R.id.radio_male);
        female = view.findViewById(R.id.radio_female);
        CV = view.findViewById(R.id.imageViewCV);
        ava = view.findViewById(R.id.imageView);
        btnChooseAva = view.findViewById(R.id.btn_avatar);
        btnChooseCV = view.findViewById(R.id.btn_cv);

        btnEdit = view.findViewById(R.id.btn_update);
        btnCancel = view.findViewById(R.id.btn_cancel);
    }

    private void Edit(String id, String token, Boolean gender, String address,String phone,String username, String email, String fullname, String birthday, String img_ava, String img_cv ) {

        RequestQueue queue = Volley.newRequestQueue(getContext());

        String url_edit = "https://tutorready.herokuapp.com/api/user/" +id; //???

        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("isAdmin", false);
            jsonObject.put("gender", gender);

            jsonObject.put("address", address);
            jsonObject.put("phone", phone);
            jsonObject.put("username", username);
            jsonObject.put("email", email);
            jsonObject.put("fullname", fullname);
            jsonObject.put("birthday", birthday);

            jsonObject.put("picture","img_ava.jpg");
            jsonObject.put("CV", "img_cv.jpg");


        } catch (JSONException e) {
            // handle exception
        }

        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, url_edit, jsonObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Toast.makeText(getContext(), "Cập nhật thông tin thành công  !!!"  , Toast.LENGTH_LONG).show();
                        username1 = edtUsername.getText().toString().trim();
                        email1 = edtEmail.getText().toString().trim();
                        fullname1 = edtEmail.getText().toString().trim();
                        phone1 = edtPhone.getText().toString().trim();
                        address1 = edtAddress.getText().toString().trim();
                        birthday1 = edtBirthday.getText().toString().trim();

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getContext(), "Cập nhật thông tin không thành công !!!" +error.getMessage(), Toast.LENGTH_LONG).show();
                        Toast.makeText(getContext(), "Error: " +error.toString(), Toast.LENGTH_LONG).show();

                    }
                }
        ) {

            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization","Bearer "+token);
                return headers;
            }

            @Override
            public byte[] getBody() {
                try {
                    Log.i("json", jsonObject.toString());
                    return jsonObject.toString().getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        queue.add(putRequest);
    }
}