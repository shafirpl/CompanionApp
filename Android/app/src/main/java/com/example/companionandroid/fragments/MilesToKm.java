package com.example.companionandroid.fragments;
/*
* In order to get the text view and other stuff, we have to do this
* https://stackoverflow.com/questions/27351005/how-to-get-text-from-edittext-within-a-fragment-and-not-activity
* SO first we have to run onCreateView, and then onViewCreated and in there we have to initialize our stuff and
* grab and do stuff
 */

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.companionandroid.R;

public class MilesToKm extends Fragment {
    EditText milesEditText;
    TextView kmTextView;
    /*
    * we use Double instead of double because Double is an object, which allows us to have
    * access to class methods such as toString, which we need to convert our double to strings.
    * I am sure there are other ways to acheive this but I tend to do it this way
     */
    Double miles;
    Double kilometers;
    final Double conversionRate = 1.60934;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_miles_to_km,container,false);
        Log.i("Fragment","initiated");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        milesEditText = (EditText) view.findViewById(R.id.milesEditText);
        kmTextView = (TextView) view.findViewById(R.id.kmTextView);

        /*
        * https://stackoverflow.com/questions/20824634/android-on-text-change-listener
        * This explains how we can run our stuff when input is changed on the editText/plain text
         */
        milesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!milesEditText.getText().toString().isEmpty()){
//                    kmTextView.setText("");
                    miles = Double.parseDouble(milesEditText.getText().toString());
                    Log.i("Miles", String.valueOf(miles));
                    kilometers = miles * conversionRate;
                    String kilometerBuilder = "";
                    kilometerBuilder += kilometers.toString();
                    kilometerBuilder += " km";
                    kmTextView.setText(kilometerBuilder);
                } else{
                    /*
                    * We have this part because if we didn't have this part, when the user
                    * erases all the text, there is blank text view because our text view only updates when
                    * there is some text. To fix that issue, this part runs if the editText is empty so we just
                    * set km. To see the effect, comment out this part and see what happens when we erases everything on
                    * the text view
                    * It will throw an error and crashes the app if we don't have this part and the check
                     */
                    kmTextView.setText("Km");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}
