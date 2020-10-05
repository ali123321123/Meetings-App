package com.example.mappe2.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mappe2.Adapters.PersonRvAdapter;
import com.example.mappe2.Modul.Person;
import com.example.mappe2.R;
import java.util.ArrayList;

public class PersonFragment extends Fragment {

    private View v;
    private RecyclerView recyclerView;

    public PersonFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_person, container, false);
        recyclerView = v.findViewById(R.id.rv_person);

        ArrayList<Person> personer = new ArrayList<>();

        personer.add(new Person("person1","34434556", R.drawable.person));
        personer.add(new Person("person2","34434556", R.drawable.person));
        personer.add(new Person("person3","34434556", R.drawable.person));
        personer.add(new Person("person4","34434556", R.drawable.person));
        personer.add(new Person("person5","34434556", R.drawable.person));
        personer.add(new Person("person6","34434556", R.drawable.person));
        personer.add(new Person("person7","34434556", R.drawable.person));
        personer.add(new Person("person8","34434556", R.drawable.person));
        personer.add(new Person("person9","34434556", R.drawable.person));
        personer.add(new Person("person10","34434556", R.drawable.person));
        personer.add(new Person("person11","34434556", R.drawable.person));
        personer.add(new Person("person12","34434556", R.drawable.person));
        personer.add(new Person("person13","34434556", R.drawable.person));
        personer.add(new Person("person14","34434556", R.drawable.person));
        personer.add(new Person("person15","34434556", R.drawable.person));

        PersonRvAdapter personRvAdapter = new PersonRvAdapter(getContext(), personer);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(personRvAdapter);

        return v;
    }

}