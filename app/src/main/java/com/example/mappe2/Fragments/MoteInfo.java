package com.example.mappe2.Fragments;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mappe2.R;
import com.google.android.material.textfield.TextInputEditText;


public class MoteInfo extends Fragment {

    private TextInputEditText navn, type, sted, dato;
    private Bundle extras;
    View v;

    public MoteInfo() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_mote_info, container, false);

        navn = v.findViewById(R.id.navn1);
        type = v.findViewById(R.id.type1);
        sted = v.findViewById(R.id.sted1);
        dato = v.findViewById(R.id.dato1);


        /*extras = getIntent().getExtras();
        if(extras != null){
            navn.setText(extras.getString("navn"));
            type.setText(extras.getString("type"));
            sted.setText(extras.getString("sted"));
            dato.setText(extras.getString("dato"));
        }*/

        return v;
    }

}