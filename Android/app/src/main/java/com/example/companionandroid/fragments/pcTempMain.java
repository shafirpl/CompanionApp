package com.example.companionandroid.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.android.volley.toolbox.Volley;
import com.example.companionandroid.MainActivity;
import com.example.companionandroid.R;
import com.example.companionandroid.adapters.PcTempRecyclerAdapter;
import com.example.companionandroid.adapters.RecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class pcTempMain extends Fragment {
    private View onViewCreatedView;
    private ArrayList<String> pcName;
    private ArrayList<String> ipAddress;
    private ArrayList<String> id;
    private FloatingActionButton addIpAddressButton;
    final String url = "http://10.0.2.2:5500/ip";
//    final String url = "http://192.168.0.116:5000/notes";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pc_temp_main,container,false);
        this.pcName = new ArrayList<String>();
        this.ipAddress = new ArrayList<String>();
        this.id = new ArrayList<String>();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.onViewCreatedView = view;
        this.addIpAddressButton = (FloatingActionButton) view.findViewById(R.id.addIpAddressButton);
        this.addIpAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AddNewIpAddress();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout,fragment);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });
        this.fetchAddress();
    }

    private void fetchAddress(){
        this.pcName.clear();
        this.ipAddress.clear();
        this.id.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, this.url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    //Log.i("Json",response.get(0).toString());
                    for (int i = 0; i< response.length(); i++){
                        JSONObject note = response.getJSONObject(i);
                        pcName.add(note.getString("compName"));
                        id.add(note.getString("_id"));
                        ipAddress.add(note.getString("ipAddress"));
                    }
                    initRecyclerView(onViewCreatedView);


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
        requestQueue.add(jsonArrayRequest);
    }
    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.pcTempMainRecycleView);
        // this class is the class we made inside adapter package
        PcTempRecyclerAdapter adapter = new PcTempRecyclerAdapter(this.pcName,this.ipAddress,this.id,getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);
    }
}