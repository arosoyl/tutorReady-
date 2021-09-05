package com.example.tutorready.student;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutorready.MainActivity5;
import com.example.tutorready.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class StudentInfoFragment extends Fragment {

    EditText edtUsername, edtEmail, edtFullname, edtPhone,edtAddress,edtBirthday;

    RadioGroup radioGroupGender;
    RadioButton male,female;
    Boolean gender, gender1;

    Button btnEdit, btnCancel, btnChooseAva, btnChooseCV;

    String id, token, username,fullname, email,phone,address, img_ava, birthday;

    String username1,fullname1, email1,phone1,address1, img_ava1, birthday1;

    ImageView ava;


    public StudentInfoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_info,container,false);

        MainActivity5 activity = (MainActivity5) getActivity();

        // Ánh xạ
        edtUsername = view.findViewById(R.id.edt_username);
        edtEmail = view.findViewById(R.id.edt_email);
        edtFullname = view.findViewById(R.id.edt_fullname);
        edtBirthday = view.findViewById(R.id.edt_birthday);
        edtAddress = view.findViewById(R.id.edt_address);
        edtPhone = view.findViewById(R.id.edt_phone);
        male = view.findViewById(R.id.radio_male);
        female = view.findViewById(R.id.radio_female);
        ava = view.findViewById(R.id.imageView);
        btnChooseAva = view.findViewById(R.id.btn_avatar);
        btnChooseCV = view.findViewById(R.id.btn_cv);

        btnEdit = view.findViewById(R.id.btn_update);
        btnCancel = view.findViewById(R.id.btn_cancel);


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


                Edit(id, token, gender1, address1, phone1, username1, email1, fullname1, birthday1, img_ava);
                //Toast.makeText(getActivity(), "Mời bạn nhập đầy đủ thông tin" + token, Toast.LENGTH_SHORT).show();



            }
        });


        btnCancel = view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private void Edit(String id, String token, Boolean gender1, String address1, String phone1, String username1, String email1, String fullname1, String birthday1, String img_ava) {
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