package com.example.tutorready.student;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutorready.MainActivity5;
import com.example.tutorready.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListTutorFragment extends Fragment {


    String token;
    ListView listViewTutor;
    ArrayList<Tutor> tutorArrayList;
    TutorAdapter adapter;

    // Hiển thị và tìm kiếm gia sư
    public ListTutorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_tutor, container, false);

        MainActivity5 activity = (MainActivity5) getActivity();
        token = activity.getToken();

        listViewTutor = (ListView) view.findViewById(R.id.lv_tutor);
        tutorArrayList = new ArrayList<>();

        adapter = new TutorAdapter(getContext(),R.layout.item_tutor,tutorArrayList);
        listViewTutor.setAdapter(adapter);

        getAllTutor(token);

        return view;
    }

    private void getAllTutor(String token) {

            String url_getAllTutor = "https://tutorgogo1.herokuapp.com/api/user/tutor/all";

            RequestQueue queue = Volley.newRequestQueue(getContext());

            StringRequest request = new StringRequest(Request.Method.GET, url_getAllTutor, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {


                        JSONObject jsonObject = new JSONObject(response);
                        String str_tutor = (String) jsonObject.getString("tutors");

                        JSONArray jsonArray = new JSONArray(str_tutor);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String phone = object.getString("phone");
                            String address = object.getString("address");
                            String picture = object.getString("picture");
                            String id = object.getString("_id");
                            String fullname = object.getString("fullname");
                            Boolean gender = object.getBoolean("gender");
                            String birthday = object.getString("birthday");
                            String email = object.getString("email");
                            String username = object.getString("username");
                            tutorArrayList.add(new Tutor(object.getString("fullname"), object.getString("phone"),object.getString("address")));
                            Toast.makeText(getActivity(), " " + fullname + phone + address, Toast.LENGTH_LONG).show();

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity(), "Error " + error.toString(), Toast.LENGTH_LONG).show();
                }

            }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    headers.put("Authorization", "Bearer " + token);
                    return headers;
                }
            };
            queue.add(request);
        }
}