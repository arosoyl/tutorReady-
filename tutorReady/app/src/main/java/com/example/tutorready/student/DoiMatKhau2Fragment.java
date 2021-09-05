package com.example.tutorready.student;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutorready.MainActivity5;
import com.example.tutorready.MainActivity6;
import com.example.tutorready.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DoiMatKhau2Fragment extends Fragment {

    EditText pass1, pass2, pass3;
    String oldPass, newPass, rePass;
    Button btnUpdate, btnCancel;

    String id,token;


    public DoiMatKhau2Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doi_mat_khau2, container, false);

        MainActivity5 activity = (MainActivity5) getActivity();
        id = activity.getId();
        token = activity.getToken();

        pass1 = view.findViewById(R.id.old_pass);
        pass2 = view.findViewById(R.id.new_pass);
        pass3 = view.findViewById(R.id.re_new_pass);


        btnUpdate = view.findViewById(R.id.btn_update);
        btnCancel = view.findViewById(R.id.btn_cancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pass1.setText("");
                pass2.setText("");
                pass3.setText("");
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                oldPass = pass1.getText().toString().trim();
                newPass = pass2.getText().toString().trim();
                rePass = pass3.getText().toString().trim();

                //Toast.makeText(getContext(), "Giá trị id " + id, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getContext(), "VỪA Nhập:  !!!"  + oldPass, Toast.LENGTH_LONG).show();

                if (oldPass.length() == 0 || newPass.length() == 0 || rePass.length() == 0) {
                    Toast.makeText(getActivity(), "Mời bạn nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (!rePass.equals(newPass)) {
                        Toast.makeText(getContext(), "Mật khẩu xác nhận không chính xác !!!", Toast.LENGTH_SHORT).show();
                    } else {

                        ChangePass(id,token,oldPass,newPass);

                        //Toast.makeText(getContext(), "VỪA Nhập:2  !!!"  + oldPass, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return view;
    }

    private void ChangePass(String id, String token, String oldPass, String newPass) {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        String url_pass = "https://tutorready.herokuapp.com/api/user/" +id+ "/password"; //???

        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("oldPassword", oldPass);
            jsonObject.put("newPassword", newPass);
        } catch (JSONException e) {
            // handle exception
        }

        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.PUT, url_pass, jsonObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Toast.makeText(getContext(), "Đổi mật khẩu thành công  !!!"  , Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Mật khẩu hiện tại không chính xác !!!", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getContext(), "Error: " +error.toString(), Toast.LENGTH_LONG).show();
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