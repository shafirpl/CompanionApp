package com.example.companionandroid.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.companionandroid.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<String> noteTitle = new ArrayList<>();
    private ArrayList<String> noteDescription = new ArrayList<>();
    private ArrayList<String> noteId = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(ArrayList<String> noteTitle, ArrayList<String> noteDescription, Context context) {
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.context = context;
        Log.i("Re noteTitle", noteTitle.toString());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noteitem_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i("Bind","OnViewBindHolder called");
        Log.i("Bind Size",Integer.toString(noteTitle.size()));
        holder.noteTitleTextView.setText(noteTitle.get(position));

        holder.noteItemCardView .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i("Note Size", Integer.toString(noteTitle.size()));
        return noteTitle.size();
    }
}
