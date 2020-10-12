package com.example.mappe2.Fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mappe2.Adapters.MoteRvAdapter;
import com.example.mappe2.Modul.Mote;
import com.example.mappe2.MoteActivity;
import com.example.mappe2.R;
import com.example.mappe2.RecyclerViewInterface;
import java.util.ArrayList;

public class MoteFragment extends Fragment implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private View view;
    ArrayList<Mote> moter;
    MoteRvAdapter adapter;

    public MoteFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment_mote, container, false);
        recyclerView =view.findViewById(R.id.rv_mote);
        moter = new ArrayList<>();

        moter.add(new Mote("Møte1","info","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte2","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte3","Info","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte4","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte5","Info","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte5","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte6","Info","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte7","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte8","Info","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte9","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte10","Info","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte11","Generel","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte12","Info","10/10/2020","Sandvika", R.drawable.mote));
        moter.add(new Mote("Møte13","Generel","10/10/2020","Sandvika", R.drawable.mote));

        adapter = new MoteRvAdapter(getContext() , moter, this);

        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onItemClick(int position) {

        Mote mote = moter.get(position);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

            Intent intent = new Intent(getContext(), MoteActivity.class);

            intent.putExtra("navn", mote.getNavn());
            intent.putExtra("type", mote.getType());
            intent.putExtra("sted", mote.getSted());
            intent.putExtra("dato", mote.getDato());

            getContext().startActivity(intent);
        }
        else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

            Bundle bundle = new Bundle();
            bundle.putString("navn8", mote.getNavn());
            bundle.putString("type8", mote.getType());
            bundle.putString("sted8", mote.getSted());
            bundle.putString("dato8", mote.getDato());

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            MoteInfo moteInfo = new MoteInfo();
            moteInfo.setArguments(bundle);

            fragmentTransaction.replace(R.id.frag, moteInfo);
            fragmentTransaction.commit();

        }
    }

    @Override
    public void onLongItemClick(int position) {

        moter.remove(position);
        adapter.notifyItemRemoved(position);

    }
}