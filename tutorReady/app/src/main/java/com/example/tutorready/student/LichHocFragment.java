package com.example.tutorready.student;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutorready.MainActivity5;
import com.example.tutorready.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LichHocFragment extends Fragment {

    String id, token;
    Course[] courses;

    public LichHocFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lich_hoc,container,false);

        MainActivity5 activity = (MainActivity5) getActivity();
        id = activity.getId();
        token = activity.getToken();

        getMyCourse(token,id);


        return view;
    }

    private void getMyCourse(String token, String id) {

        String url_getMyCourse ="https://tutorgogo1.herokuapp.com/api/user/course/"+id;

        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.GET, url_getMyCourse, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String courses = (String) jsonObject.getString("courses");
                    JSONArray jsonArray = new JSONArray(courses);

                    for (int i=0; i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);

                        String info_tutor = object.getString("tutor");
                        JSONObject jsonTutor = new JSONObject(info_tutor);
                        String idTutor = jsonTutor.getString("_id");
                        String nameTutor = jsonTutor.getString("name");

                        String idCourse = object.getString("_id");
                        String nameCourse = object.getString("name");
                        String schedules = object.getString("schedules");
                        String address = object.getString("address");

                        String info_grade = object.getString("grade");
                        JSONObject jsonGrade = new JSONObject(info_grade);
                        String idGrade = jsonGrade.getString("_id");
                        String nameGrade = jsonGrade.getString("name");

                        String info_subject = object.getString("subject");
                        JSONObject jsonSubject = new JSONObject(info_subject);
                        String idSubject = jsonGrade.getString("_id");
                        String nameSubject = jsonGrade.getString("name");

                        //String khoahoc = i+"."+" "+nameTutor +"\n "+nameCourse +"\n";

                        //Toast.makeText(getActivity(), i+"."+" "+nameTutor +"\n "+nameCourse +"\n" , Toast.LENGTH_SHORT).show();

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