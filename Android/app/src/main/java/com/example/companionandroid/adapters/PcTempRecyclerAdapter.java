package com.example.companionandroid.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.companionandroid.MainActivity;
import com.example.companionandroid.R;
import com.example.companionandroid.fragments.EditIpAddress;
import com.example.companionandroid.fragments.FragmentNotFound;
import com.example.companionandroid.fragments.pcTempMonitor;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

// this is being called from pcTempMain
public class PcTempRecyclerAdapter extends RecyclerView.Adapter<IpAddressViewHolder> {
    private ArrayList<String> pcName;
    private ArrayList<String> ipAddress;
    private ArrayList <String> id;
    Context context;

    public  PcTempRecyclerAdapter(ArrayList<String> pcName, ArrayList<String> ipAddress, ArrayList<String> id, Context context){
        this.ipAddress = ipAddress;
        this.pcName = pcName;
        this.id = id;
        this.context = context;
    }
    @NonNull
    @Override
    public IpAddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ip_address_view_holder,parent,false);
        IpAddressViewHolder ipAddressViewHolder = new IpAddressViewHolder(view);
        return ipAddressViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IpAddressViewHolder holder, int position) {
        final String pcNameString = this.pcName.get(position);
        final String ipAddressString = this.ipAddress.get(position);
        holder.pcIpAddressTextView.setText(ipAddressString);
        holder.pcNameTextView.setText(pcNameString);
        final String ipId = this.id.get(position);
        holder.ipAddressCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // https://stackoverflow.com/questions/5428077/android-why-does-long-click-also-trigger-a-normal-click
                // explains what the return value should be
                Bundle args = new Bundle();
                args.putString("pcName",pcNameString);
                args.putString("pcIpAddress",ipAddressString);
                args.putString("id",ipId);

                Fragment fragment = new EditIpAddress();
                fragment.setArguments(args);

                // pcTempMonitor.setArguments(args);

                FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout,fragment).addToBackStack(null).commit();

                return true;
            }
        });

        holder.ipAddressCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("pcName",pcNameString);
                args.putString("pcIpAddress",ipAddressString);

                Fragment pcTempMonitor = new pcTempMonitor();
                pcTempMonitor.setArguments(args);

                FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout,pcTempMonitor).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.pcName.size();
    }
}
