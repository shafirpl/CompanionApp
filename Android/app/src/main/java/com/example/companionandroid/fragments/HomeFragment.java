package com.example.companionandroid.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.example.companionandroid.R;
import com.example.companionandroid.adapters.RecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HomeFragment extends Fragment {
    // recall to enable cleartext in android mainfest.xml file
    // android:usesCleartextTraffic="true"

    private FloatingActionButton addNote;
    private FloatingActionButton refrestNotes;
    private ArrayList<String> noteTitle = new ArrayList<>();
    private ArrayList<String> noteDescription = new ArrayList<>();
    private ArrayList<String> noteId = new ArrayList<>();
    private View onViewCreatedView;

    // use 10.0.2.2:portNumber when using emulator
    final String url = "http://10.0.2.2:5000/notes";
    //final String url = "http://192.168.0.116:5000/notes";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.onViewCreatedView = view;
        addNote = view.findViewById(R.id.addNoteButton);
        refrestNotes = view.findViewById(R.id.refreshButton);
        refrestNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Sync Started", Toast.LENGTH_SHORT).show();
                fetchNotes(0);
//                initRecyclerView(onViewCreatedView);
            }
        });
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNote();
            }
        });
        fetchNotes(1);
        // initRecyclerView(onViewCreatedView);

    }

    private void goToNote(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment fragment = new NoteFragment();
        fragmentTransaction.replace(R.id.fragment_layout,fragment);
        fragmentTransaction.commit();
    }

    private void fetchNotes(final int status){
        /*
        * status 0:  show toast message
        * status 1: don't show toast message
         */
        noteId.clear();
        noteDescription.clear();
        noteTitle.clear();

        // we need to make sync request otherwise stuff won't show up
        // https://stackoverflow.com/questions/17608707/volley-http-request-in-blocking-way
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        RequestFuture<JSONArray> requestFuture = RequestFuture.newFuture();
        /*
//        * Since we get a json array, we use jsonarrayrequest instead of jsonobjectrequest
//         */
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                   //Log.i("Json",response.get(0).toString());
                    for (int i = 0; i< response.length(); i++){
                        JSONObject note = response.getJSONObject(i);
                        noteId.add(note.getString("noteId"));
                        noteDescription.add(note.getString("description"));
                        noteTitle.add(note.getString("title"));
                    }
                    initRecyclerView(onViewCreatedView);
                    Log.i("Json Array Title", noteTitle.toString());
                    if(status == 0){
                        Toast.makeText(getContext(),"Sync Finished",Toast.LENGTH_SHORT).show();
                    }
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

        requestQueue.add(jsonArrayRequest);
        //Toast.makeText(getContext(),noteId.toString(),Toast.LENGTH_SHORT).show();

    }
    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recylerView);
        // this class is the class we made inside adapter package
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(noteTitle,noteDescription,getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
