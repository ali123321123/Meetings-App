package com.example.mappe2.Fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mappe2.Adapters.PersonRvAdapter;
import com.example.mappe2.Modul.Person;
import com.example.mappe2.PersonActivity;
import com.example.mappe2.R;
import com.example.mappe2.RecyclerViewInterface;

import java.util.ArrayList;

public class PersonFragment extends Fragment implements RecyclerViewInterface {

    private View view;
    private RecyclerView recyclerView;
    ArrayList<Person> personer;
    PersonRvAdapter personRvAdapter;

    public PersonFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_person, container, false);
        recyclerView = view.findViewById(R.id.rv_person);
        personer = new ArrayList<>();

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

        personRvAdapter = new PersonRvAdapter(getContext(), personer, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(personRvAdapter);

        return view;
    }

    @Override
    public void onItemClick(int position) {

        Person person = personer.get(position);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

            Intent intent = new Intent(getContext(), PersonActivity.class);

            intent.putExtra("navn1", person.getNavn());
            intent.putExtra("telefonnr1", person.getTelefonnr());

            getContext().startActivity(intent);
        }
        else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

            Bundle bundle = new Bundle();
            bundle.putString("navn2", person.getNavn());
            bundle.putString("telefonnr2", person.getTelefonnr());

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            PersonInfo personInfo = new PersonInfo();
            personInfo.setArguments(bundle);

            fragmentTransaction.replace(R.id.frag, personInfo);
            fragmentTransaction.commit();

        }

    }

    @Override
    public void onLongItemClick(int position) {

        personer.remove(position);
        personRvAdapter.notifyItemRemoved(position);
    }
}