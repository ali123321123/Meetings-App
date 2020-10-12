package com.example.mappe2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.textfield.TextInputEditText;

public class PersonActivity extends AppCompatActivity {

    TextInputEditText navn, telfonnr;
    Toolbar toolbar;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        navn = findViewById(R.id.navn2);
        telfonnr = findViewById(R.id.telefonnr2);

        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));

        bundle = getIntent().getExtras();

        if(bundle != null){
            navn.setText(bundle.getString("navn1"));
            telfonnr.setText(bundle.getString("telefonnr1"));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.andre_meny, menu);
        /*MenuItem lagre = menu.findItem(R.id.meny_lagre);
        MenuItem endre = menu.findItem(R.id.meny_endre);
        MenuItem slette = menu.findItem(R.id.meny_slette);
        if(key == -1){
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
        telfonnr.setEnabled(false);
    }

    private void enableFelt(){
        navn.setEnabled(true);
        telfonnr.setEnabled(true);
    }

    private void clearFelt(){
        navn.setText("");
        telfonnr.setText("");
    }
}