package com.example.companionandroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.companionandroid.R;

public class CustomFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // View view = inflater.inflate(R.layout.custom_fragment,container,false);
        /* TODO
        * Letter revert back to this view when the custom fragement is done
        * */
        View view = inflater.inflate(R.layout.fragment_not_found,container,false);
        return view;
    }
}
