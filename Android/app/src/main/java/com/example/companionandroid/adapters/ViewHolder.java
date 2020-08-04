package com.example.companionandroid.adapters;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

// we need to import this manually
import com.example.companionandroid.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView noteTitleTextView;
    //ConstraintLayout noteHolderConstraintLayout;
    LinearLayout noteHolderLinearLayout;
    CardView noteItemCardView;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        noteTitleTextView = itemView.findViewById(R.id.noteTitleTextView);
        //noteHolderConstraintLayout = itemView.findViewById(R.id.noteHolderConstraintLayout);
        noteHolderLinearLayout = itemView.findViewById(R.id.noteHolderLinearLayout);
        noteItemCardView = itemView.findViewById(R.id.noteItemCardView);
//        noteTitleTextView = itemView.findViewById(R)
    }
}
