package com.example.mappe2.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mappe2.R;
import com.google.android.material.textfield.TextInputEditText;

public class PersonInfo extends Fragment {

    TextInputEditText navn, telfonnr;
    View v;

    public PersonInfo() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_person_info, container, false);

        navn = v.findViewById(R.id.navn2);
        telfonnr = v.findViewById(R.id.telefonnr2);

        Bundle bundle = this.getArguments();

        if(bundle != null){
            navn.setText(bundle.getString("navn2"));
            telfonnr.setText(bundle.getString("telefonnr2"));
        }
        return v;
    }
}