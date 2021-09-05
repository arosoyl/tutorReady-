package com.example.tutorready.student;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutorready.MainActivity5;
import com.example.tutorready.MainActivity7;
import com.example.tutorready.R;
import com.example.tutorready.tutor.Schedule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListCourseFragment extends Fragment {


    // Hiển thị và tìm kiếm lớp học

    Course[] course;
    String token, str_courses;
    TextView edtCourse;
    Button btnSearch;
    List<Course> courses;

    ArrayList<Course> listCourses;
    public ListCourseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_course, container, false);

        MainActivity5 activity = (MainActivity5) getActivity();
        token = activity.getToken();

        edtCourse = view.findViewById(R.id.tv_course);
        ArrayList<Course> courseArrayList;


        btnSearch = view.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courses=getAllCourse(token);
                //Toast.makeText(getActivity(), "YEAH" +courses , Toast.LENGTH_LONG).show();

            }
        });




        return view;
    }


    private List<Course> getAllCourse(String token) {

        String url_getAllCourse ="https://tutorgogo1.herokuapp.com/api/user/course/all";

        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.GET, url_getAllCourse, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {


                    JSONObject jsonObject = new JSONObject(response);
                    str_courses = (String) jsonObject.getString("courses");


                    JSONArray jsonArray = new JSONArray(str_courses);

                    for (int i=0; i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String idCourse = object.getString("_id");
                        String nameCourse = object.getString("name");
                        String schedules = object.getString("schedules");
                        String address = object.getString("address");

                        String info_tutor = object.getString("tutor");
                        JSONObject jsonTutor = new JSONObject(info_tutor);
                        String idTutor = jsonTutor.getString("_id");
                        String nameTutor = jsonTutor.getString("name");
                        Tutor tutor = new Tutor();
                        tutor.setName(nameTutor);
                        tutor.set_id(idTutor);

                        String info_grade = object.getString("grade");
                        JSONObject jsonGrade = new JSONObject(info_grade);
                        String idGrade = jsonGrade.getString("_id");
                        String nameGrade = jsonGrade.getString("name");
                        Grade grade = new Grade();
                        grade.setIdGrade(idGrade);
                        grade.setNameGrade(nameGrade);

                        String info_subject = object.getString("subject");
                        JSONObject jsonSubject = new JSONObject(info_subject);
                        String idSubject = jsonGrade.getString("_id");
                        String nameSubject = jsonGrade.getString("name");
                        Subject subject = new Subject();
                        subject.setIdSubject(idSubject);
                        subject.setIdSubject(nameSubject);

                        String khoahoc = i+"."+" "+nameTutor +"\n "+nameCourse +"\n";

                        Course course = new Course();
                        course.setNameCourse(nameCourse);
                        course.setIdCourse(idCourse);
                        course.setTutor(tutor);
                        course.setAddress(address);
                        course.setSchedule(schedules);
                        course.setAddress(address);
                        course.setSubject(subject);

                        listCourses.add(course);
                        Toast.makeText(getActivity(),course.toString() , Toast.LENGTH_SHORT).show();

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
        return listCourses;
    }

}