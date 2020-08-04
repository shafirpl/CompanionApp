package com.example.companionandroid.adapters;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

// we need to import this manually
import com.example.companionandroid.R;

public class MaintenanceViewHolder extends RecyclerView.ViewHolder {
    LinearLayout maintenanceItemLinearLayout;
    CardView maintenanceItemCardView;
    TextView maintenanceTitle;
    TextView maintenanceDate;
    TextView maintenanceOdometer;

    public MaintenanceViewHolder(@NonNull View itemView) {
        super(itemView);
        this.maintenanceItemLinearLayout = (LinearLayout) itemView.findViewById(R.id.maintenanceItemLinearLayout);
        this.maintenanceItemCardView = (CardView) itemView.findViewById(R.id.maintenanceItemCardView);
        this.maintenanceDate = (TextView) itemView.findViewById(R.id.maintenanceDate);
        this.maintenanceOdometer = (TextView) itemView.findViewById(R.id.maintenanceOdometer);
        this.maintenanceTitle = (TextView) itemView.findViewById(R.id.maintenanceTitle);

    }
}
