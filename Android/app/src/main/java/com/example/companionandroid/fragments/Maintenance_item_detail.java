package com.example.companionandroid.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.companionandroid.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Maintenance_item_detail extends Fragment {
    // public static final String url = "http://10.0.2.2:5500/maintenance";
    public static final String url = "http://138.68.61.175:5500/maintenance";
    EditText titleEditText;
    String title;
    EditText dateEditText;
    String date;
    EditText shopNameEditText;
    String shopName;
    EditText placeEditText;
    String place;
    EditText odometerEditText;
    String odometer;
    EditText priceEditText;
    EditText maintenanceDescriptionEditText;
    String price;
    String id;
    Button editMaintenanceRecordButton;
    String urlBuilder;
    @Nullable
    @Override
    /*
     * to grab data
     * https://stackoverflow.com/questions/16036572/how-to-pass-values-between-fragments
     * We can use the getArgument method both in onCreateView and onViewCreated. It doesn't matter where we use
     * it. In other words, we can grab the arguments both in onCreateView and onViewCreated
     */
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maintenance_item_detail,container,false);
        this.title = getArguments().getString("title");
        this.date = getArguments().getString("date");
        this.shopName = getArguments().getString("shopName");
        this.place = getArguments().getString("place");
        this.odometer = getArguments().get("odometer").toString();
        this.price = getArguments().get("price").toString();
        this.id = getArguments().getString("id");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.editMaintenanceRecordButton = (Button) view.findViewById(R.id.editMaintenanceRecordButton);
        this.titleEditText = (EditText) view.findViewById(R.id.titleEditText);
        this.dateEditText = (EditText) view.findViewById(R.id.dateEditText);
        this.shopNameEditText = (EditText) view.findViewById(R.id.shopNameEditText);
        this.placeEditText = (EditText) view.findViewById(R.id.placeEditText);
        this.odometerEditText = (EditText) view.findViewById(R.id.odometerEditText);
        this.priceEditText = (EditText) view.findViewById(R.id.priceEditText);
        this.maintenanceDescriptionEditText = (EditText) view.findViewById(R.id.maintenanceDescriptionEditText);

        this.urlBuilder = url+"/"+this.id;
        Log.i("url", this.urlBuilder);

        this.titleEditText.setText(this.title);
        this.dateEditText.setText(this.date);
        this.shopNameEditText.setText(this.shopName);
        this.placeEditText.setText(this.place);
        this.odometerEditText.setText(this.odometer);
        this.priceEditText.setText(this.price);
        this.maintenanceDescriptionEditText.setText(getArguments().getString("description"));

        this.editMaintenanceRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    updateRecord();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void updateRecord() throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title",this.titleEditText.getText().toString());
        jsonObject.put("date",this.dateEditText.getText().toString());
        jsonObject.put("shopName",this.shopNameEditText.getText().toString());
        jsonObject.put("place",this.placeEditText.getText().toString());
        jsonObject.put("odometer",Double.parseDouble(this.odometerEditText.getText().toString()));
        jsonObject.put("price", Double.parseDouble(this.priceEditText.getText().toString()));
        jsonObject.put("description",this.maintenanceDescriptionEditText.getText());
        Log.i("Json object", jsonObject.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, urlBuilder, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getInt("done") == 1){
                        Toast.makeText(getContext(),"Record Edited",Toast.LENGTH_SHORT).show();
                    }
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