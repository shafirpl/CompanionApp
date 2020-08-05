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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.companionandroid.R;

import org.json.JSONException;
import org.json.JSONObject;


public class AddNewIpAddress extends Fragment {

    EditText newPcNameEditText;
    EditText newIpAddressEditText;
    Button addNewPcButton;

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
                    jsonObject.put("compName",newPcNameEditText.getText().toString());
                    jsonObject.put("ipAddress", newIpAddressEditText.getText().toString());
                    

                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });


    }
}