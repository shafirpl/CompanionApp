/*
* For how we are getting the rate, watch this video
* https://www.youtube.com/watch?v=y2xtLqP8dSQ
* I googled to figure out the url required for the url
 */
package com.example.companionandroid.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.companionandroid.MainActivity;
import com.example.companionandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;


public class UsdToCad extends Fragment {
    final String url = "https://www.bankofcanada.ca/valet/observations/FXUSDCAD/json?recent=1";
    Double conversionRate;
    private RequestQueue requestQueue;
    EditText usdEditText;
    TextView cadTextView;
    Double usd;
    Double cad;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usd_to_cad,container,false);
        getRate();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        usdEditText = (EditText) view.findViewById(R.id.usdEditText);
        cadTextView = (TextView) view.findViewById(R.id.cadTextView);

        usdEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!usdEditText.getText().toString().isEmpty()){
                    usd = Double.parseDouble(usdEditText.getText().toString());
                    cad = usd * conversionRate;

                    String cadBuilder = "";
                    // this rounds off the cad into 2 decimal place
                    // https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
                    DecimalFormat df = new DecimalFormat("#.##");
                    cad = Double.valueOf(df.format(cad));
                    cadBuilder += cad.toString();
                    cadBuilder += " CAD";
                    cadTextView.setText(cadBuilder);

                } else{
                    cadTextView.setText("CAD");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void getRate(){
        /*
        * https://www.youtube.com/watch?v=y2xtLqP8dSQ
        * Watch from 6:20 to understand what these functions do
         */

        requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("observations");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            JSONObject FXUSDCAD = jsonObject.getJSONObject("FXUSDCAD");
                            String rate = FXUSDCAD.getString("v");
                            Log.i("rate",rate);
                            conversionRate = Double.parseDouble(rate);
                            Toast.makeText(getContext(),"Today's Bank of Canada's rate is "+ conversionRate,Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("Rate","Issue in json array");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.i("Rate","Cannot get it");
                Toast.makeText(getContext(),"Can't get the rate",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }
}
