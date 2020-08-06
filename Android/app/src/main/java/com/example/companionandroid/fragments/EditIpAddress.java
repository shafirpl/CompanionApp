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


public class EditIpAddress extends Fragment {

    EditText editPcNameEditText;
    EditText editIpAddressEditText;
    Button editPcButton;
    private final String partialUrl = "http://138.68.61.175:5500/ip";
    private String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_ip_address,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.editPcNameEditText = (EditText) view.findViewById(R.id.editPcNameEditText);
        this.editIpAddressEditText = (EditText) view.findViewById(R.id.editIpAddressEditText);
        this.editPcButton = (Button) view.findViewById(R.id.editPcButton);

        this.editPcNameEditText.setText(getArguments().getString("pcName"));
        this.editIpAddressEditText.setText(getArguments().getString("pcIpAddress"));
        this.id = getArguments().getString("id");

        final String url = partialUrl+"/"+this.id;

        this.editPcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    JSONObject jsonObject = new JSONObject();
                    final String compName = editPcNameEditText.getText().toString();
                    jsonObject.put("compName",compName);
                    jsonObject.put("ipAddress", editIpAddressEditText.getText().toString());

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if(response.getInt("done") == 1){
                                    Toast.makeText(getContext(),"Record Edited",Toast.LENGTH_SHORT).show();
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