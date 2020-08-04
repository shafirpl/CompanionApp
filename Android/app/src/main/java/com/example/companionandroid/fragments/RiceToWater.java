package com.example.companionandroid.fragments;

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

public class RiceToWater extends Fragment {
    private String selectedRice;
    EditText editTextTextRiceCups;
    TextView riceWaterCupsTextView;
    public static final String basmati = "Basmati Rice";
    public static final String jasemine = "Jasemine Rice";
    public static final String chinigura = "Chinigura Rice";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rice_to_water,container,false);
        this.selectedRice = basmati;
        return view;
    }

    private Double riceToWaterCalculator(String riceType, int cups){
        Double result = 0.0;
        Double conversionRate = 0.0;
        switch (riceType){
            case chinigura:
                conversionRate = 2.0;
                break;
            case basmati:
            case jasemine:
                conversionRate = 1.5;
                break;
            // without adding break, the default value will be used, because it
            // https://www.quora.com/What-is-the-use-of-a-switch-case-and-break-statement-in-the-C-language-and-what-are-some-examples#:~:text=In%20switch%20case%2C%20the%20break,brace%20'%7D'%20is%20reached.
            // basically without break, it will go through all the code until it reaches the ending braces, so our default value will be used
            default:
                conversionRate = 2.0;

        }

        result = conversionRate * cups;
        // Log.i("Result",result.toString());
        return result;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.editTextTextRiceCups = (EditText) view.findViewById(R.id.editTextTextRiceCups);
        this.riceWaterCupsTextView = (TextView) view.findViewById(R.id.riceWaterCupsTextView);

        // spinner code
        final Spinner spinner = (Spinner) view.findViewById(R.id.riceSelector);
        List<String> riceList = new ArrayList<String>();
        riceList.add(basmati);
        riceList.add(jasemine);
        riceList.add(chinigura);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,riceList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedRice = spinner.getSelectedItem().toString();
                riceWaterCupsTextView.setText("0 cups of water");
                editTextTextRiceCups.setText("");
                // Toast.makeText(getContext(),selectedRice.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        this.editTextTextRiceCups.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!editTextTextRiceCups.getText().toString().isEmpty()){
                    Double result = riceToWaterCalculator(selectedRice,Integer.parseInt(editTextTextRiceCups.getText().toString()));
                    String resultString = new DecimalFormat("#.#").format(result);
                    resultString += " cups of water";
                    // Toast.makeText(getContext(),selectedRice.toString(),Toast.LENGTH_SHORT).show();
                    riceWaterCupsTextView.setText(resultString);
                }
                else{
                    riceWaterCupsTextView.setText("0 cups of water");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}