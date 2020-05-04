package com.example.companionandroid.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.companionandroid.R;


public class KgToLbs extends Fragment {
    EditText kgEditText;
    TextView lbsTextView;
    /*
    * Look at MilesToKm.java file to see why I went with Double instead of double
     */
    Double kg;
    Double lbs;
    final Double conversionRate = 2.20462;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kg_to_lbs,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        kgEditText = (EditText) view.findViewById(R.id.kgEditText);
        lbsTextView = (TextView) view.findViewById(R.id.lbsTextView);
        /*
         * https://stackoverflow.com/questions/20824634/android-on-text-change-listener
         * This explains how we can run our stuff when input is changed on the editText/plain text
         */
        kgEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!kgEditText.getText().toString().isEmpty()){
                    kg = Double.parseDouble(kgEditText.getText().toString());
                    lbs = kg * conversionRate;
                    String lbsBuilder = "";
                    lbsBuilder += lbs.toString();
                    lbsBuilder += " lbs";
                    lbsTextView.setText(lbsBuilder);
                } else{
                    /*
                    * Look at the MilesToKm.java file to see why we have this else part
                     */
                    lbsTextView.setText("lbs");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
