package com.example.companionandroid.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.companionandroid.MainActivity;
import com.example.companionandroid.R;

import org.json.JSONException;
import org.json.JSONObject;


public class NoteItemDescription extends Fragment {
    String title;
    String description;
    String noteId;
    EditText titleShowEditText;
    EditText descriptionShowEditText;
    String urlBuilder;
    Button editNoteButton;
    @Nullable
    @Override
    /*
    * to grab data
    * https://stackoverflow.com/questions/16036572/how-to-pass-values-between-fragments
     */
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_item_description,container,false);
        title = getArguments().getString("title");
        description = getArguments().getString("description");
        noteId = getArguments().getString("noteId");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        titleShowEditText = (EditText) view.findViewById(R.id.titleShowEditText);
        descriptionShowEditText = (EditText) view.findViewById(R.id.descriptionShowEditText);
        editNoteButton = (Button) view.findViewById(R.id.editNoteButton);

        titleShowEditText.setText(title);
        descriptionShowEditText.setText(description);

        urlBuilder = MainActivity.url + "/" + noteId;

        editNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    updateNote();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void updateNote() throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title",titleShowEditText.getText());
        jsonObject.put("description",descriptionShowEditText.getText());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, urlBuilder, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getInt("done") == 1){
                        Toast.makeText(getContext(),"Note Edited",Toast.LENGTH_SHORT).show();
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
        requestQueue.add(jsonObjectRequest);
    }
}
