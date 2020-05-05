package com.example.companionandroid.adapters;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.companionandroid.R;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView noteTitleTextView;
    ConstraintLayout noteHolderConstraintLayout;
    LinearLayout noteHolderLinearLayout;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        noteTitleTextView = itemView.findViewById(R.id.noteTitleTextView);
        noteHolderConstraintLayout = itemView.findViewById(R.id.noteHolderConstraintLayout);
        noteHolderLinearLayout = itemView.findViewById(R.id.noteHolderLinearLayout);
//        noteTitleTextView = itemView.findViewById(R)
    }
}
