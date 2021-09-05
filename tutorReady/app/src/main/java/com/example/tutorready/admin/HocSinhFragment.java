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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutorready.MainActivity5;
import com.example.tutorready.MainActivity7;
import com.example.tutorready.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HocSinhFragment extends Fragment {

    Spinner spinnerSubject;
    String subject;
    ListView listView;
    Button btnSearch;
    String token,id_student;
    String[] students;

    public HocSinhFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hoc_sinh,container,false);


        MainActivity7 activity = (MainActivity7) getActivity();
        token = activity.getToken();

        spinnerSubject = view.findViewById(R.id.subjects);
        listView = view.findViewById(R.id.lv_hocsinh);
        //ArrayList<String> arrayAdapter = new ArrayList<String>(getActivity(),android.R.layout.simple_list_item_1,);



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

        getListStudent(token);
        deleteStudent(token,id_student);
        return view;
    }

    private void deleteStudent(String token, String id_student) {
        String url_delete_student ="https://tutorready.herokuapp.com/api/user/student/"+id_student;

        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.DELETE, url_delete_student, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(), "Xoá thông tin học sinh thành công !!!" , Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Xoá thông tin học sinh không thành công "+error.toString() , Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);
    }

    private void getListStudent(String token) {

        String url_get_student ="https://tutorready.herokuapp.com/api/user/student/all";

        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.GET, url_get_student, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    //Toast.makeText(getActivity(), "YEAH" , Toast.LENGTH_LONG).show();
                    JSONObject jsonObject = new JSONObject(response);
                    String registerCourseRequests = (String) jsonObject.getString("registerCourseRequests");
                    JSONArray jsonArray = new JSONArray(registerCourseRequests);
                    for(int i =0; i<jsonArray.length();i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id_tutor_registerCourseRequests = object.getString("tutor_id");
                        String id_student_registerCourseRequests = object.getString("student_id");
                        String id_course_registerCourseRequests = object.getString("course_id");
                        String students = "Học sinh thứ"+i+":" + id_course_registerCourseRequests + id_tutor_registerCourseRequests + id_student_registerCourseRequests;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error "+error.toString() , Toast.LENGTH_LONG).show();
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
}