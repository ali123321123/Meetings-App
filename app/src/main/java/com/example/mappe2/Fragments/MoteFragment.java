package com.example.mappe2.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mappe2.Adapters.MoteRvAdapter;
import com.example.mappe2.MainActivity;
import com.example.mappe2.Modul.Mote;
import com.example.mappe2.Modul.Person;
import com.example.mappe2.R;
import java.util.ArrayList;

public class MoteFragment extends Fragment {

    private RecyclerView recyclerView;
    private View v;

    public MoteFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.fragment_mote, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView =v.findViewById(R.id.rv_mote);

        ArrayList<Mote> moter = new ArrayList<>();

        moter.add(new Mote("Møte1","info","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte2","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte3","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte4","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte5","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte5","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte6","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte7","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte8","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte9","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte10","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte11","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte12","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte13","Generel","10/10/2020","Sandvika", R.drawable.mote));

        final MoteRvAdapter adapter = new MoteRvAdapter(getContext() , moter);

        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MoteRvAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });





    }
}