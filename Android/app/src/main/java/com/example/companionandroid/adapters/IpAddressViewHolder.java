package com.example.companionandroid.adapters;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.companionandroid.R;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;



public class IpAddressViewHolder extends RecyclerView.ViewHolder {
    CardView ipAddressCardView;
    LinearLayout ipAddressLinearLayout;
    TextView pcNameTextView;
    TextView pcIpAddressTextView;
    public IpAddressViewHolder(@NonNull View itemView) {
        super(itemView);
        this.ipAddressCardView = (CardView) itemView.findViewById(R.id.ipAddressCardView);
        this.ipAddressLinearLayout = (LinearLayout) itemView.findViewById(R.id.ipAddressLinearLayout);
        this.pcNameTextView = (TextView) itemView.findViewById(R.id.pcNameTextView);
        this.pcIpAddressTextView = (TextView) itemView.findViewById(R.id.pcIpAddressTextView);
    }
}
