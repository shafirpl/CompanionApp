package com.example.companionandroid.adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.companionandroid.MainActivity;
import com.example.companionandroid.R;
import com.example.companionandroid.fragments.NoteItemDescription;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<String> noteTitle = new ArrayList<>();
    private ArrayList<String> noteDescription = new ArrayList<>();
    private ArrayList<String> noteId = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(ArrayList<String> noteId,ArrayList<String> noteTitle, ArrayList<String> noteDescription, Context context) {
        this.noteId = noteId;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.i("Bind","OnViewBindHolder called");
        Log.i("Bind Size",Integer.toString(noteTitle.size()));
        holder.noteTitleTextView.setText(noteTitle.get(position));


        holder.noteItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = noteTitle.get(position);
                String description = noteDescription.get(position);
                String id = noteId.get(position);
                String builder = "note id:"+id+" title: "+title+ " description:"+description;
                Log.i("Passed info:",builder);
                /*
                * Passing data between fragments
                * https://stackoverflow.com/questions/24555417/how-to-send-data-from-one-fragment-to-another-fragment
                 */
                Fragment noteItemDescriptionFragment = new NoteItemDescription();
                Bundle args = new Bundle();
                args.putString("noteId", id);
                args.putString("description", description);
                args.putString("title",title);
                noteItemDescriptionFragment.setArguments(args);
                /*
                * I got the idea to commit transaction from this link
                * https://stackoverflow.com/questions/34527115/start-fragment-from-recycleview-adapter-onclick
                * The last one
                 */
                FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout,noteItemDescriptionFragment).addToBackStack(null).commit();


            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i("Note Size", Integer.toString(noteTitle.size()));
        return noteTitle.size();
    }
}
