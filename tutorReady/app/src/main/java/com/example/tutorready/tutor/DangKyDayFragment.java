package com.example.tutorready.tutor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutorready.MainActivity6;
import com.example.tutorready.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DangKyDayFragment extends Fragment {


    String token,fullname,address,nameClass,time;
    String idGrade, nameGrade, idSubject,nameSubject;

    Students[] listStudent;
    Schedule[] listSchedule;

    EditText edtNameClass, edtTime;
    Spinner spinnerGrade, spinnerSubject;

    Button btnOpenClass, btnCancel;


    public DangKyDayFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dang_ky_day,container,false);

        MainActivity6 activity = (MainActivity6) getActivity();
        token = activity.getToken();
        fullname = activity.getFullname();
        address = activity.getAddress();

        edtNameClass = view.findViewById(R.id.edt_classname);
        edtTime = view.findViewById(R.id.edt_time);

        // 

        nameSubject = "Mỹ thuật";
        nameGrade ="Lớp 12";

        spinnerGrade = view.findViewById(R.id.spinner_class);

        ArrayList<String> arrayGrade = new ArrayList<String>();
        arrayGrade.add("Mẫu giáo");
        arrayGrade.add("Luyện thi đại học");
        arrayGrade.add("Người đi làm");
        arrayGrade.add("Lớp 1");
        arrayGrade.add("Lớp 2");
        arrayGrade.add("Lớp 3");
        arrayGrade.add("Lớp 4");
        arrayGrade.add("Lớp 5");
        arrayGrade.add("Lớp 6");
        arrayGrade.add("Lớp 7");
        arrayGrade.add("Lớp 8");
        arrayGrade.add("Lớp 9");
        arrayGrade.add("Lớp 10");
        arrayGrade.add("Lớp 11");
        arrayGrade.add("Lớp 12");
        ArrayAdapter arrayAdapterGrade = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,arrayGrade);
        arrayAdapterGrade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrade.setAdapter(arrayAdapterGrade);

        spinnerGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Bạn chọn lớp "+ arrayGrade.get(position) , Toast.LENGTH_LONG).show();
                nameGrade = arrayGrade.get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSubject = view.findViewById(R.id.spinner_subject);

        ArrayList<String> arraySubject = new ArrayList<String>();
        arraySubject.add("Thanh nhạc");
        arraySubject.add("Toán Học");
        arraySubject.add("Vật Lý");
        arraySubject.add("Hoá Học");
        arraySubject.add("Sinh Học");
        arraySubject.add("Tin Học");
        arraySubject.add("Ngữ Văn");
        arraySubject.add("Tiếng Việt");
        arraySubject.add("Lịch Sử");
        arraySubject.add("Địa Lý");
        arraySubject.add("Luyện Chữ Đẹp");
        arraySubject.add("Tiếng Anh");
        arraySubject.add("Tiếng Hàn");
        arraySubject.add("Tiếng Nhật");
        arraySubject.add("Tiếng Pháp");
        arraySubject.add("Tiếng Trung");
        arraySubject.add("Tiếng Tây Ban Nha");
        arraySubject.add("Ngoại ngữ khác");
        arraySubject.add("Piano");
        arraySubject.add("Guita");
        arraySubject.add("Organ");
        arraySubject.add("Mỹ thuật");
        ArrayAdapter arrayAdapterSubject = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,arraySubject);
        arrayAdapterSubject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubject.setAdapter(arrayAdapterSubject);


        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Bạn chọn lớp "+ arraySubject.get(position) , Toast.LENGTH_LONG).show();
                nameSubject = arraySubject.get(position).toString();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (nameSubject.equals("Thanh nhạc")) idSubject = "61244d391bf73d0015da9ea9";
        if (nameSubject.equals("Toán Học")) idSubject = "61244d461bf73d0015da9eaa";
        if (nameSubject.equals("Vật Lý")) idSubject = "61244d511bf73d0015da9eab";
        if (nameSubject.equals("Hóa Học")) idSubject = "61244d661bf73d0015da9eac";
        if (nameSubject.equals("Sinh Học")) idSubject = "61244d661bf73d0015da9ead";
        if (nameSubject.equals("Tin Học")) idSubject = "61244d721bf73d0015da9eae";
        if (nameSubject.equals("Ngữ Văn")) idSubject = "61244d841bf73d0015da9eaf";
        if (nameSubject.equals("Tiếng Việt")) idSubject = "61244d911bf73d0015da9eb0";
        if (nameSubject.equals("Lịch Sử")) idSubject = "61244da11bf73d0015da9eb1";
        if (nameSubject.equals("Địa Lý")) idSubject = "61244dac1bf73d0015da9eb2";
        if (nameSubject.equals("Luyện Chữ Đẹp")) idSubject = "61244db81bf73d0015da9eb3";
        if (nameSubject.equals("Tiếng Anh")) idSubject = "61244dc21bf73d0015da9eb4";
        if (nameSubject.equals("Tiếng Nhật")) idSubject = "61244dce1bf73d0015da9eb5";
        if (nameSubject.equals("Tiếng Hàn")) idSubject = "61244dda1bf73d0015da9eb6";
        if (nameSubject.equals("Tiếng Pháp")) idSubject = "61244de51bf73d0015da9eb7";
        if (nameSubject.equals("Tiếng Trung")) idSubject = "61244df11bf73d0015da9eb8";
        if (nameSubject.equals("Tiếng Tây Ban Nha")) idSubject = "61244dfe1bf73d0015da9eb9";
        if (nameSubject.equals("Ngoại ngữ khác")) idSubject = "61244e091bf73d0015da9eba";
        if (nameSubject.equals("Piano")) idSubject = "61244e131bf73d0015da9ebb";
        if (nameSubject.equals("Guita")) idSubject = "61244e201bf73d0015da9ebc";
        if (nameSubject.equals("Organ")) idSubject = "61244e281bf73d0015da9ebd";
        if (nameSubject.equals("Mỹ thuật")) idSubject = "61244e301bf73d0015da9ebe";

        if (nameGrade.equals("Mẫu giáo")) idGrade = "61244c4a1bf73d0015da9ea6";
        if (nameGrade.equals("Luyện thi đại học")) idGrade = "61244c4a1bf73d0015da9ea7";
        if (nameGrade.equals("Người đi làm")) idGrade ="61244c4a1bf73d0015da9ea8";
        if (nameGrade.equals("Lớp 1")) idGrade = "61244c4a1bf73d0015da9ea9";
        if (nameGrade.equals("Lớp 2")) idGrade = "6124647a1bf73d0015da9eea";
        if (nameGrade.equals("Lớp 3")) idGrade = "612464801bf73d0015da9eeb";
        if (nameGrade.equals("Lớp 4")) idGrade = "612464831bf73d0015da9eec";
        if (nameGrade.equals("Lớp 5")) idGrade = "612464871bf73d0015da9eed";
        if (nameGrade.equals("Lớp 6")) idGrade = "6124648a1bf73d0015da9eee";
        if (nameGrade.equals("Lớp 7")) idGrade = "6124648e1bf73d0015da9eef";
        if (nameGrade.equals("Lớp 8")) idGrade = "6124649c1bf73d0015da9ef0";
        if (nameGrade.equals("Lớp 9")) idGrade = "6124649c1bf73d0015da9ef1";
        if (nameGrade.equals("Lớp 10")) idGrade = "6124649c1bf73d0015da9ef2";
        if (nameGrade.equals("Lớp 11")) idGrade = "6124649c1bf73d0015da9ef3";
        if (nameGrade.equals("Lớp 12")) idGrade = "6124649f1bf73d0015da9ef4";

        btnCancel = view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNameClass.setText("");
                edtTime.setText("");
                Toast.makeText(getContext(), "Mở lớp thành công !!!"  , Toast.LENGTH_LONG).show();
            }
        });


        btnOpenClass = view.findViewById(R.id.btn_openClass);
        btnOpenClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameClass = edtNameClass.getText().toString();
                time = edtTime.getText().toString();

                if (nameClass.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập tên lớp !!!"  , Toast.LENGTH_LONG).show();
                } else if (time.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập giờ !!!"  , Toast.LENGTH_LONG).show();
                }
                else Dangkyday(token, fullname, address, nameClass ,idSubject, nameSubject,idGrade, nameGrade, listStudent,listSchedule);
                //Dangkyday(token, fullname, address, nameClass ,idSubject, "Lớp 12","6124649f1bf73d0015da9ef4", nameGrade, listStudent,listSchedule);
            }
        });

        return view;
    }

    private void Dangkyday(String token, String fullname, String address,String nameClass ,String idSubject, String nameSubject, String idGrade, String nameGrade,
                           Students[] listStudent,Schedule[] listSchedule) {

        RequestQueue queue = Volley.newRequestQueue(getContext());

        String url_dangkyday = "https://tutorready.herokuapp.com/api/user/course/new"; //???

        JSONObject js = new JSONObject();
        try {

            js.put("tutorName",fullname);
            js.put("name",nameClass);
            js.put("address",address);
            js.put("students", listStudent);
            js.put("schedules",listSchedule);

            JSONObject subject = new JSONObject();
            subject.put("_id",idSubject);
            subject.put("name",nameSubject);



            JSONObject grade = new JSONObject();
            grade.put("_id",idGrade);
            grade.put("name",nameGrade);


            js.put("subject", subject);
            js.put("grade", grade);


        }catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,url_dangkyday, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getContext(), "Mở lớp thành công !!!"  , Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getContext(), "Cập nhật thông tin không thành công !!!" +error.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(), "Error: " +error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {

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
                    Log.i("json", js.toString());
                    return js.toString().getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        queue.add(jsonObjReq);
    }
}