package com.example.tutorready.student;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutorready.MainActivity5;
import com.example.tutorready.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class AcceptFragment extends Fragment {


    String token, tutor_id, student_id, course_id;

    public AcceptFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accept, container, false);

        MainActivity5 activity = (MainActivity5) getActivity();
        token = activity.getToken();

        getRegisterCourseRequests(token);



        return view;
    }

    private void getRegisterCourseRequests(String token) {

        String url_register_course_requests ="https://tutorready.herokuapp.com/api/user/course/student/registerRequests";

        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.GET, url_register_course_requests, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    //Toast.makeText(getActivity(), "YEAH" , Toast.LENGTH_LONG).show();
                    JSONObject jsonObject = new JSONObject(response);
                    String registerCourseRequests = (String) jsonObject.getString("registerCourseRequests");
                    JSONArray jsonArray = new JSONArray(registerCourseRequests);
                    for(int i =0; i<jsonArray.length();i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id_registerCourseRequests = object.getString("_id");
                        String id_tutor_registerCourseRequests = object.getString("tutor_id");
                        String id_student_registerCourseRequests = object.getString("student_id");
                        String id_course_registerCourseRequests = object.getString("course_id");


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