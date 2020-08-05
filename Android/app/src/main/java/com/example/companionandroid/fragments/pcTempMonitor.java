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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    private ProgressBar cpuUsageBar;
    private TextView cpuUsageTextView;
    private TextView cpuFreeTextView;
    private TextView cpuModelTextView;

    private ProgressBar memUsageBar;
    private TextView memUsageTextView;
    private TextView memFreeTextView;
    private TextView memTotalTextView;
    private String partialUrl;
    private String pcNickName;
    final String port = ":4200/socket";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pc_temp_monitor,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.partialUrl = getArguments().getString("pcIpAddress");
        this.pcNickName = getArguments().getString("pcIpAddress");
        this.handler = new Handler();
        this.cpuUsageBar = (ProgressBar) view.findViewById(R.id.cpuUsageBar);
        this.cpuUsageTextView = (TextView) view.findViewById(R.id.cpuUsageTextView);
        this.cpuFreeTextView = (TextView) view.findViewById(R.id.cpuFreeTextView);
        this.cpuModelTextView = (TextView) view.findViewById(R.id.cpuModelTextView);

        this.memFreeTextView = (TextView) view.findViewById(R.id.memFreeTextView);
        this.memUsageTextView = (TextView) view.findViewById(R.id.memUsageTextView);
        this.memUsageBar = (ProgressBar) view.findViewById(R.id.memUsageBar);
        this.memTotalTextView = (TextView) view.findViewById(R.id.memTotalTextView);
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

            String url = "http://"+partialUrl+this.port;

            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // Log.i("Volley data",response.toString());
                    //backendResponse = response.toString();
                    //Toast.makeText(getContext(),backendResponse, Toast.LENGTH_SHORT).show();
                    try {
                        String cpuProgressString = response.getString("cpuUsage");
                        // cpu part
                        /*
                        * the problem is: if we look at our postman request, we will see that the thing we get is like 3.25 %
                        * so a double number followed by a space and then %, we need to remove the space and % in order  to properly
                        * Convert it to a double. And then since the progressbar only takes int, we need to round the double to an int
                        * value
                         */
                        cpuProgressString = cpuProgressString.substring(0, cpuProgressString.length()-1);
                        cpuProgressString = cpuProgressString.substring(0, cpuProgressString.length()-1);
                        Double cpuProgress = Double.parseDouble(cpuProgressString);
                        int value = (int) Math.round(cpuProgress);
                        cpuUsageBar.setProgress(value);

                        cpuUsageTextView.setText(response.getString("cpuUsage").toString());
                        cpuFreeTextView.setText(response.getString("cpuFree").toString());
                        cpuModelTextView.setText(response.getString("cpuModel"));


                        // memory part
                        /*
                        * now memUsage looks like this: "memUsage": "70.85% | 2.75GB",
                        * So what we can see that we can take substring upto % part, since the substring method
                        * gets rid of the last character, we just figure out the index of % and use substring upto that
                        * index
                         */
                        String memProgressString = response.getString("memUsage");
                        int index = memProgressString.indexOf('%');
                        memProgressString = memProgressString.substring(0, index);
                        Double memProgress = Double.parseDouble(memProgressString);
                        int memVal = (int) Math.round(memProgress);
                        memUsageBar.setProgress(memVal);


                        memUsageTextView.setText(response.getString("memUsage").toString());
                        memFreeTextView.setText(response.getString("memFree").toString());
                        memTotalTextView.setText(response.getString("totalMem")+ " GB");
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