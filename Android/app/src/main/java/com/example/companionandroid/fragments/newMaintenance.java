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
import com.example.companionandroid.MainActivity;
import com.example.companionandroid.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class newMaintenance extends Fragment {
    EditText newtitleEditText;
    EditText newdateEditText;
    EditText newshopNameEditText;
    EditText newplaceEditText;
    EditText newodometerEditText;
    EditText newpriceEditText;
    EditText newmaintenanceDescriptionEditText;
    Button addNewMaintenanceRecordButton;
    public static final String url = "http://10.0.2.2:5500/maintenance";
    // public static final String url = "http://138.68.61.175:5500/notes";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_maintenance,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.newtitleEditText = (EditText) view.findViewById(R.id.newtitleEditText);
        this.newdateEditText = (EditText) view.findViewById(R.id.newdateEditText);
        this.newshopNameEditText = (EditText) view.findViewById(R.id.newshopNameEditText);
        this.newplaceEditText = (EditText) view.findViewById(R.id.newplaceEditText);
        this.newodometerEditText = (EditText) view.findViewById(R.id.newodometerEditText);
        this.newpriceEditText = (EditText) view.findViewById(R.id.newpriceEditText);
        this.newmaintenanceDescriptionEditText = (EditText) view.findViewById(R.id.newmaintenanceDescriptionEditText);
        this.addNewMaintenanceRecordButton = (Button) view.findViewById(R.id.addNewMaintenanceRecordButton);

        this.addNewMaintenanceRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    submitRequest(newtitleEditText.getText().toString(),newdateEditText.getText().toString(),
                            newshopNameEditText.getText().toString(), newplaceEditText.getText().toString(),
                            Double.parseDouble(newodometerEditText.getText().toString()),
                            Double.parseDouble(newpriceEditText.getText().toString()),
                            newmaintenanceDescriptionEditText.getText().toString());
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void submitRequest(final String title, String date, String shopName,String place,
                               Double odometer, Double price,
                               String description) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("date",date);
        jsonObject.put("title",title);
        jsonObject.put("description",description);
        jsonObject.put("shopName", shopName);
        jsonObject.put("price",price);
        jsonObject.put("odometer",odometer);
        jsonObject.put("place",place);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("title").equals(title)){
                        Toast.makeText(getContext(),"Record Added", Toast.LENGTH_SHORT).show();
                    };
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}