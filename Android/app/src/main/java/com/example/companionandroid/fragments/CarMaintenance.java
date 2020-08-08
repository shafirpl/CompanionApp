package com.example.companionandroid.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.companionandroid.MainActivity;
import com.example.companionandroid.R;
import com.example.companionandroid.adapters.MaintenanceRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CarMaintenance extends Fragment {
    private ArrayList<String> maintenanceId = new ArrayList<String>();
    private ArrayList<String> maintenanceTitle = new ArrayList<String>();
    private ArrayList <Double> odometer = new ArrayList<Double>();
    private ArrayList<String> date = new ArrayList<String>();
    private ArrayList<String> description = new ArrayList<String>();
    private ArrayList<String> place = new ArrayList<String>();
    private ArrayList<Double> price = new ArrayList<Double>();
    private ArrayList <String> shopName = new ArrayList<String>();
    private FloatingActionButton addMaintenanceButton;
    // use 10.0.2.2:portNumber when using emulator
    // final String url = "http://10.0.2.2:5500/maintenance";
    final String url = "http://138.68.61.175:5500/maintenance";
    private View onViewCreatedView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_maintenance,container,false);
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        this.onViewCreatedView = view;
        this.addMaintenanceButton = view.findViewById(R.id.addMaintenanceButton);
        this.addMaintenanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNewRecord();
            }
        });
        fetchRecords();
    }

    private void goToNewRecord(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment fragment = new newMaintenance();
        fragmentTransaction.replace(R.id.fragment_layout,fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    private void fetchRecords(){
        this.maintenanceId.clear();
        this.maintenanceTitle.clear();
        this.odometer.clear();
        this.date.clear();
        this.description.clear();
        this.place.clear();
        this.price.clear();
        this.shopName.clear();

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, this.url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i< response.length(); i++){
                        JSONObject maintenance = response.getJSONObject(i);
                        maintenanceId.add(maintenance.getString("_id"));
                        maintenanceTitle.add(maintenance.getString("title"));
                        odometer.add(maintenance.getDouble("odometer"));
                        date.add(maintenance.getString("date"));
                        description.add(maintenance.getString("description"));
                        place.add(maintenance.getString("place"));
                        price.add(maintenance.getDouble("price"));
                        shopName.add(maintenance.getString("shopName"));

                    }
                    initRecyclerView(onViewCreatedView);

                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.maintenanceRecyclerView);
        MaintenanceRecyclerViewAdapter maintenanceRecyclerViewAdapter = new MaintenanceRecyclerViewAdapter(this.maintenanceId, this.maintenanceTitle,
                this.odometer,this.date,this.description,this.place,this.price,this.shopName,getContext());
        recyclerView.setAdapter(maintenanceRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // this enables the swipe functionality that we need for swipe to delete functionality
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);
    }
    // this is the code for swipe to delete functionality
    //https://www.youtube.com/watch?v=M1XEqqo6Ktg&t=80s
    ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
            new AlertDialog.Builder(getContext())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Are You Sure!")
                    .setMessage("Record will be permanently deleted")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        // this function will run if the user clicks the yes or positive button
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            deleteRecord(viewHolder.getAdapterPosition());
                            //Toast.makeText(getContext(),"Note Deleted", Toast.LENGTH_SHORT).show();
                        }
                    })
                    // null means we won't run any code if the user clicks the no/negative button
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            fetchRecords();
                        }
                    })
                    .show();
        }
    };

    private void deleteRecord(int position){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String urlBuilder = this.url + "/" + maintenanceId.get(position);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, urlBuilder, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getInt("done") == 1){
                        Toast.makeText(getContext(),"Record Deleted",Toast.LENGTH_SHORT).show();
                        fetchRecords();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }


}