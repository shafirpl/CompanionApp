package com.example.companionandroid.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.companionandroid.R;

import org.json.JSONException;
import org.json.JSONObject;


public class pcTempMonitor extends Fragment {
    private Handler handler;
    private String backendResponse;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pc_temp_monitor,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.handler = new Handler();
        /*
        * https://www.youtube.com/watch?v=3pgGVBmSVq0
        * This video explains how we can keep calling the backend to get continious data
         */
        startFetchingData();

    }

    private void startFetchingData(){
        this.handler.postDelayed(contFetchData,1000);
    }

    private void fetchData(){
        // use 10.0.2.2:portNumber when using emulator
//    //final String url = "http://10.0.2.2:5000/notes";
//    final String url = "http://192.168.0.116:5000/notes";
        // we get a crash if we switch from this tab to any other tab, the fix is to check if the getContext is null or not
        // https://stackoverflow.com/questions/41468580/android-attempt-to-invoke-virtual-method-on-a-null-object-reference
        if (getContext() != null){
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            String url = "http://10.0.2.2:4200/socket";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // Log.i("Volley data",response.toString());
                    backendResponse = response.toString();
                    Toast.makeText(getContext(),backendResponse, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            // this sets out the reuqest, without this we won't make the request
            requestQueue.add(jsonObjectRequest);
        }


    }

    private Runnable contFetchData = new Runnable() {
        @Override
        public void run() {
            fetchData();
            handler.postDelayed(this,1000);

        }
    };


}