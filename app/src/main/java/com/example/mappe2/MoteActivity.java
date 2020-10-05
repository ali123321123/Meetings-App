package com.example.mappe2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.textfield.TextInputEditText;

public class MoteActivity extends AppCompatActivity {

    private TextInputEditText navn, type, sted, dato;
    private Bundle extras;
    private Toolbar toolbar;
    //private int key = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mote);

        navn = findViewById(R.id.navn1);
        type = findViewById(R.id.type1);
        sted = findViewById(R.id.sted1);
        dato = findViewById(R.id.dato1);

        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));

        extras = getIntent().getExtras();
        if(extras != null){
            navn.setText(extras.getString("navn"));
            type.setText(extras.getString("type"));
            sted.setText(extras.getString("sted"));
            dato.setText(extras.getString("dato"));
        }

        /*Intent intent = getIntent();
        key = intent.getIntExtra(MainActivity.KEY, -1);

        if(key == -1){

        }
        else {

        }*/
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.andre_meny, menu);
        MenuItem lagre = menu.findItem(R.id.meny_lagre);
        MenuItem endre = menu.findItem(R.id.meny_endre);
        MenuItem slette = menu.findItem(R.id.meny_slette);
        /*if(key == -1){
            lagre.setVisible(true);
            endre.setVisible(false);
            slette.setVisible(false);
        }
        else {
            lagre.setVisible(false);
            endre.setVisible(true);
            slette.setVisible(true);
        }*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.meny_lagre:
                return true;
            case R.id.meny_endre:
                return true;
            case R.id.meny_slette:
                return true;
        }
        return false;
    }

    private void disableFelt(){
        navn.setEnabled(false);
        type.setEnabled(false);
        sted.setEnabled(false);
        dato.setEnabled(false);
    }

    private void enableFelt(){
        navn.setEnabled(true);
        type.setEnabled(true);
        sted.setEnabled(true);
        dato.setEnabled(true);
    }

    private void clearFelt(){
        navn.setText("");
        type.setText("");
        sted.setText("");
        dato.setText("");
    }

}