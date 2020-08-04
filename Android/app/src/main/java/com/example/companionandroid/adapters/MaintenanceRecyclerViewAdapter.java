package com.example.companionandroid.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.companionandroid.MainActivity;
import com.example.companionandroid.R;
import com.example.companionandroid.fragments.Maintenance_item_detail;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class MaintenanceRecyclerViewAdapter extends RecyclerView.Adapter<MaintenanceViewHolder> {
    private ArrayList<String> maintenanceId = new ArrayList<String>();
    private ArrayList<String> maintenanceTitle = new ArrayList<String>();
    private ArrayList <Double> odometer = new ArrayList<Double>();
    private ArrayList<String> date = new ArrayList<String>();
    private ArrayList<String> description = new ArrayList<String>();
    private ArrayList<String> place = new ArrayList<String>();
    private ArrayList<Double> price = new ArrayList<Double>();
    private ArrayList <String> shopName = new ArrayList<String>();
    private Context context;

    public MaintenanceRecyclerViewAdapter(
    ArrayList<String> maintenanceId,
    ArrayList<String> maintenanceTitle,
    ArrayList <Double> odometer,
    ArrayList<String> date,
    ArrayList<String> description,
    ArrayList<String> place,
    ArrayList<Double> price,
    ArrayList <String> shopName,
    Context context){

        this.maintenanceId = maintenanceId;
        this.maintenanceTitle = maintenanceTitle;
        this.odometer = odometer;
        this.description = description;
        this.date = date;
        this.place = place;
        this.price = price;
        this.shopName = shopName;
        this.context = context;
    }


    @NonNull
    @Override
    public MaintenanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.maintenance_item,parent,false);
        MaintenanceViewHolder viewHolder = new MaintenanceViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MaintenanceViewHolder holder, final int position) {
        String odometerText = this.odometer.get(position) + " KM";
        holder.maintenanceTitle.setText(this.maintenanceTitle.get(position));
        holder.maintenanceOdometer.setText(odometerText);
        holder.maintenanceDate.setText(this.date.get(position));
        // for some reason when we were trying to set them inside onclick, it was showing
        // error, or was not recogning the class variables such as odometer and others
        // so I had to declare them outside onClick, but for that
        // they need to be declared as final. Weird
        final String id = this.maintenanceId.get(position);
        final String title = this.maintenanceTitle.get(position);
        final Double odometer = this.odometer.get(position);
        final String description = this.description.get(position);
        final String date = this.date.get(position);
        final String place = this.place.get(position);
        final Double price = this.price.get(position);
        final String shopName = this.shopName.get(position);


        holder.maintenanceItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                 * Passing data between fragments
                 * https://stackoverflow.com/questions/24555417/how-to-send-data-from-one-fragment-to-another-fragment
                 */
                Fragment maintenance_item_detail = new Maintenance_item_detail();
                Bundle args = new Bundle();

                args.putString("title",title);
                args.putString("id",id);
                args.putDouble("odometer",odometer);
                args.putString("description",description);
                args.putString("date",date);
                args.putString("place",place);
                args.putDouble("price",price);
                args.putString("shopName",shopName);


                maintenance_item_detail.setArguments(args);

                /*
                 * I got the idea to commit transaction from this link
                 * https://stackoverflow.com/questions/34527115/start-fragment-from-recycleview-adapter-onclick
                 * The last one
                 */
                FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout,maintenance_item_detail).addToBackStack(null).commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return maintenanceId.size();
    }
}
