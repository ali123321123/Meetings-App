package com.example.mappe2.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mappe2.R;
import com.google.android.material.textfield.TextInputEditText;

public class MoteInfo extends Fragment {

    private TextInputEditText navn, type, sted, dato;
    View v;

    public MoteInfo() {
    }

    @SuppressLint("LongLogTag")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_mote_info, container, false);

        navn = v.findViewById(R.id.navn1);
        type = v.findViewById(R.id.type1);
        sted = v.findViewById(R.id.sted1);
        dato = v.findViewById(R.id.dato1);

        Bundle bundle = this.getArguments();

        //Log.e("value Fragment get Argument", "friendsID :" + navn);

        if(bundle != null){
            navn.setText(bundle.get("navn8").toString());
            type.setText(bundle.get("type8").toString());
            sted.setText(bundle.get("sted8").toString());
            dato.setText(bundle.get("dato8").toString());
        }

        return v;
    }

}