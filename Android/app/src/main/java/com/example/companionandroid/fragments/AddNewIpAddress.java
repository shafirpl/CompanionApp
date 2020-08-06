package com.example.companionandroid.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.example.companionandroid.R;

import org.json.JSONException;
import org.json.JSONObject;


public class AddNewIpAddress extends Fragment {

    EditText newPcNameEditText;
    EditText newIpAddressEditText;
    Button addNewPcButton;
    private final String url = "http://138.68.61.175:5500/ip";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_ip_address,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.newPcNameEditText = (EditText) view.findViewById(R.id.newPcNameEditText);
        this.newIpAddressEditText = (EditText) view.findViewById(R.id.newIpAddressEditText);
        this.addNewPcButton = (Button) view.findViewById(R.id.addNewPcButton);

        this.addNewPcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    JSONObject jsonObject = new JSONObject();
                    final String compName = newPcNameEditText.getText().toString();
                    jsonObject.put("compName",compName);
                    jsonObject.put("ipAddress", newIpAddressEditText.getText().toString());

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getString("compName").equals(compName)){
                                    Toast.makeText(getContext(),"PC Added",Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getContext(),"Server Error",Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    requestQueue.add(jsonObjectRequest);

                } catch (JSONException e){
                    e.printStackTrace();
                }

            }
        });


    }
}