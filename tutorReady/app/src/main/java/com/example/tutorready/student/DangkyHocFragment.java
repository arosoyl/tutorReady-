package com.example.tutorready.student;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutorready.MainActivity5;
import com.example.tutorready.R;
import com.example.tutorready.tutor.Schedule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DangkyHocFragment extends Fragment {

    String token,id, tutor_id,course_id;
    Spinner spinnerSubject,spinnerLevel,spinnerRequest,spinnerArea;
    String subject,level,request,area,course;
    Button btnSearch;
    ListView lvCourse;



    public DangkyHocFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dang_ky_hoc,container,false);

        MainActivity5 activity = (MainActivity5) getActivity();
        token = activity.getToken();
        id = activity.getId();




        spinnerSubject = view.findViewById(R.id.subjects);
        spinnerLevel = view.findViewById(R.id.levels);
        spinnerRequest = view.findViewById(R.id.requests);
        spinnerArea = view.findViewById(R.id.area);

        ArrayList<String> arrayMonhoc = new ArrayList<String>();
        arrayMonhoc.add("Toán học");
        arrayMonhoc.add("Ngữ văn");
        arrayMonhoc.add("Sinh học");
        arrayMonhoc.add("Vật lí");
        arrayMonhoc.add("Hoá học");
        arrayMonhoc.add("Vật lí");
        arrayMonhoc.add("Năng khiếu");
        arrayMonhoc.add("Tiếng anh");
        arrayMonhoc.add("Các môn khác");
        ArrayAdapter arrayAdapterMonhoc = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,arrayMonhoc);
        arrayAdapterMonhoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubject.setAdapter(arrayAdapterMonhoc);

        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Bạn chọn môn "+ arrayMonhoc.get(position) , Toast.LENGTH_LONG).show();
                subject = arrayMonhoc.get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        ArrayList<String> arrayLevel = new ArrayList<String>();
        arrayLevel.add("Mầm non");
        arrayLevel.add("Cấp 1");
        arrayLevel.add("Cấp 2");
        arrayLevel.add("Cấp 3");
        arrayLevel.add("Người đi làm");
        ArrayAdapter arrayAdapterCap = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,arrayLevel);
        arrayAdapterCap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(arrayAdapterCap);

        spinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Bạn chọn trình độ "+ arrayLevel.get(position) , Toast.LENGTH_LONG).show();
                level = arrayLevel.get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        ArrayList<String> arrayRequest = new ArrayList<String>();
        arrayRequest.add("Sinh viên");
        arrayRequest.add("Giáo viên");
        ArrayAdapter arrayAdapterRequest = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,arrayRequest);
        arrayAdapterRequest.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRequest.setAdapter(arrayAdapterRequest);

        spinnerRequest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Bạn chọn môn "+ arrayRequest.get(position) , Toast.LENGTH_LONG).show();
                request = arrayRequest.get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        ArrayList<String> arrayArea = new ArrayList<String>();
        arrayArea.add("Cẩm Lệ");
        arrayArea.add("Hải Châu");
        arrayArea.add("Liên Chiểu");
        arrayArea.add("Ngũ Hành Sơn");
        arrayArea.add("Sơn Trà");
        arrayArea.add("Thanh Khê");
        arrayArea.add("Hoàng Sa");
        arrayArea.add("Hòa Vang");
        arrayArea.add("Hòa Khánh");
        ArrayAdapter arrayAdapterArea = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,arrayArea);
        arrayAdapterArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(arrayAdapterArea);


        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Bạn chọn môn "+ arrayArea.get(position) , Toast.LENGTH_LONG).show();
                area = arrayArea.get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSearch = view.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Bạn chọn môn " , Toast.LENGTH_SHORT).show();
            }
        });


        lvCourse = view.findViewById(R.id.lv_course);

        GetCourseData(token);
        DangKyHoc(token,id,tutor_id,course_id);
        





        return view;
    }

    private void DangKyHoc(String token, String student_id, String tutor_id, String course_id) {

        String url_dangkyCourse = "https://tutorready.herokuapp.com/api/user/course/register";

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.POST, url_dangkyCourse, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getActivity(), "Đăng kí lớp học thành công " , Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Đăng kí không thành công "+error.toString() , Toast.LENGTH_SHORT).show();
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

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tutor_id", tutor_id);
                params.put("student_id",student_id);
                params.put("course_id",course_id);
                return params;
            }
        };
        requestQueue.add(request);
    }

    private void GetCourseData(String token) {

        String url_getCourse = "https://tutorready.herokuapp.com/api/user/course";

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.GET, url_getCourse, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    //Toast.makeText(getActivity(), "YEAH" , Toast.LENGTH_LONG).show();

                    JSONObject jsonObject = new JSONObject(response);
                    course = (String) jsonObject.getString("courses");
                    JSONArray jsonArray = new JSONArray(course);
                    for(int i =0; i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        Course course = new Course();
                        course.setIdCourse(object.getString("_id"));
                        course.setAddress(object.getString("_id"));
                        course.setNameCourse(object.getString("name"));

                        Tutor tutor = new Tutor();
                        String tt = object.getString("tutor");
                        JSONObject jsonTutor = new JSONObject(tt);
                        tutor.set_id(jsonTutor.getString("_id"));
                        tutor.setName(jsonTutor.getString("name"));

                        String shedules = object.getString("shedules");

                        Subject subject = new Subject();
                        String subjects = object.getString("subject");
                        JSONObject jsonSubject = new JSONObject(subjects);
                        subject.setIdSubject(jsonSubject.getString("_id"));
                        subject.setNameSubject(jsonSubject.getString("name"));


                        Grade grade = new Grade();
                        String grades = object.getString("grade");
                        JSONObject jsonGrade = new JSONObject(grades);
                        grade.setIdGrade(jsonGrade.getString("_id"));
                        grade.setNameGrade(jsonGrade.getString("name"));




                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error "+error.toString() , Toast.LENGTH_SHORT).show();
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
        requestQueue.add(request);
    }
}