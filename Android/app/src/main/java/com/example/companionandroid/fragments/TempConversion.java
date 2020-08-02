package com.example.companionandroid.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.companionandroid.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class TempConversion extends Fragment {

    private boolean celsiusFlag = true;
    EditText tempEditText;
    TextView tempTextView;
    private static final String Celsius = "\u2103";
    private static final String Fahrenheit = "\u2109";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temp_conversion, container, false);
        return view;
    }
    
    private double conversion(double temp){
        double result = 0.0;

        if(celsiusFlag){
            result = ((temp*9)/5)+32;
        }
        else{
            result = (5*(temp-32)) / 9;
        }

        return result;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.tempEditText = (EditText) view.findViewById(R.id.tempEditText);
        this.tempTextView = (TextView) view.findViewById(R.id.tempTextView);


        /* Spinner code */
        List<String> tempList = new ArrayList<String>();
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        // celsius
        tempList.add(Celsius);
        // fahrenhite
        tempList.add(Fahrenheit);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, tempList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        // Specify the layout to use when the list of choices appears
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = spinner.getSelectedItem().toString();
                if(item.equals(Fahrenheit)){
                    celsiusFlag = false;
                    tempTextView.setText(Celsius);
                }
                else{
                    celsiusFlag = true;
                    tempTextView.setText(Fahrenheit);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /* conversion code */
        tempEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!tempEditText.getText().toString().isEmpty()){
                    Double result = conversion(Double.parseDouble(tempEditText.getText().toString()));
                    String resultString = new DecimalFormat("#.#").format(result);
                    resultString += " ";
                    if (celsiusFlag){
                        resultString += Fahrenheit;
                    }
                    else{
                        resultString += Celsius;
                    }
                    tempTextView.setText(resultString);
                }
                /*
                 * We have this part because if we didn't have this part, when the user
                 * erases all the text, there is blank text view because our text view only updates when
                 * there is some text. To fix that issue, this part runs if the editText is empty so we just
                 * set km. To see the effect, comment out this part and see what happens when we erases everything on
                 * the text view
                 * It will throw an error and crashes the app if we don't have this part and the check
                 */
                else{
                    if(celsiusFlag){
                        tempTextView.setText(Fahrenheit);
                    }
                    else{
                        tempTextView.setText(Celsius);
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}