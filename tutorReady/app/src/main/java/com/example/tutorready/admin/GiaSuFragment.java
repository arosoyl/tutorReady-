package com.example.tutorready.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.tutorready.R;

import java.util.ArrayList;


public class GiaSuFragment extends Fragment {


    Spinner spinnerSubject;
    String subject;
    ListView listViewTutor;
    Button btnShow;

    public GiaSuFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gia_su,container,false);

        spinnerSubject = view.findViewById(R.id.choiceTutor);
        listViewTutor = view.findViewById(R.id.lv_tutor);

        ArrayList<String> arrayChoice = new ArrayList<String>();
        arrayChoice.add("Theo môn");
        arrayChoice.add("Theo tuổi");
        arrayChoice.add("Theo giới tính");
        arrayChoice.add("Theo địa chỉ");
        ArrayAdapter arrayAdapterChoice = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,arrayChoice);
        arrayAdapterChoice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubject.setAdapter(arrayAdapterChoice);
        return view;
    }
}