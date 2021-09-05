package com.example.tutorready.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tutorready.R;

public class AdminFragment extends Fragment {


    EditText edtUsername, edtEmail, edtFullname, edtPhone,edtAddress,edtBirthday;

    RadioGroup radioGroupGender;
    RadioButton male,female;
    Boolean gender, gender1;

    Button btnEdit, btnCancel, btnChooseAva, btnChooseCV;

    String id, token, username,fullname, email,phone,address, img_cv, img_ava, birthday;

    String username1,fullname1, email1,phone1,address1, img_cv1, img_ava1, birthday1;

    ImageView ava;

    public AdminFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin,container,false);


        return view;
    }
}