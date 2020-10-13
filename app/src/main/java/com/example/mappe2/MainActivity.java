package com.example.mappe2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mappe2.Adapters.ViewPagerAdapter;
import com.example.mappe2.Fragments.MoteFragment;
import com.example.mappe2.Fragments.MoteInfo;
import com.example.mappe2.Fragments.PersonFragment;
import com.example.mappe2.Fragments.PersonInfo;
import com.example.mappe2.Modul.Mote;
import com.example.mappe2.Modul.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import Controller.DatabaseHandler;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MoteFragment moteFragment;
    private PersonFragment personFragment;
    private FloatingActionButton fab;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DatabaseHandler(getApplicationContext());
        Mote mote1 = new Mote("leseMøte", "infomøte", "2019/10/5", "Oslo");
        Mote mote2 = new Mote("leseMøte", "infomøte", "2019/10/5", "Oslo");
        Mote mote3 = new Mote("leseMøte", "infomøte", "2019/10/5", "Oslo");
        Mote mote4 = new Mote("leseMøte", "infomøte", "2019/10/5", "Oslo");

        long mote_id1 = db.createMote(mote1);
        long mote_id2 = db.createMote(mote2);
        long mote_id3 = db.createMote(mote3);



        Person person1 = new Person("Adnan", "12345678");
        Person person2 = new Person("Adnan", "12345678");
        Person person3 = new Person("Adnan", "12345678");
        Person person4 = new Person("Adnan", "12345678");
        Person person5 = new Person("Adnan", "12345678");
        Person person6 = new Person("Adnan", "12345678");


        long person_Id1 = db.LeggeTilPerson(person1, new long[]{mote_id1});
        long person_Id2 = db.LeggeTilPerson(person2, new long[]{mote_id1});

        long person_Id3 = db.LeggeTilPerson(person3, new long[]{mote_id2});
        long person_Id4 = db.LeggeTilPerson(person4, new long[]{mote_id2});


        long person_Id5 = db.LeggeTilPerson(person5, new long[]{mote_id3});
        long person_Id6 = db.LeggeTilPerson(person6, new long[]{mote_id3});



        db.createMotePerson(person_Id6,mote_id2);

        Log.d("Get Tags", "Getting All Tags");

        List<Mote> allTags = db.HenteAlleMoter();
        for (Mote tag : allTags) {
            Log.d("Tag Name", tag.getNavn());
        }

        db.closeDB();




        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setElevation(0);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));

        fab = findViewById(R.id.main_fab);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        moteFragment = new MoteFragment();
        personFragment = new PersonFragment();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(moteFragment, "Møter");
        viewPagerAdapter.addFragment(personFragment, "Personer");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            tabLayout.getTabAt(0).setIcon(R.drawable.mote);
            tabLayout.getTabAt(1).setIcon(R.drawable.person);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MoteActivity.class);
                startActivity(intent);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    fab.setEnabled(false);
                    fab.setVisibility(View.GONE);
                    if (tabLayout.getSelectedTabPosition() == 0) {

                        getSupportFragmentManager().beginTransaction().replace(R.id.frag, new MoteInfo()).addToBackStack(null).commit();
                    } else {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag, new PersonInfo()).addToBackStack(null).commit();
                    }
                }
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    if (tabLayout.getSelectedTabPosition() == 1) {
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getBaseContext(), PersonActivity.class);
                                startActivity(intent);
                            }
                        });
                    } else {
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getBaseContext(), MoteActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_meny, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.soke).getActionView();
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}