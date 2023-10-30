package com.example.fragmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean isFragmentDisplay=false;
    private int mChoice=2;
    private Button mButtonOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButtonOpen = findViewById(R.id.button_open);
    }

    public void openFragment(View view) {
        if(!isFragmentDisplay) {
            open();
        } else {
            close();
        }
    }

    private void close() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment);
        if(simpleFragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }
        isFragmentDisplay=false;
        mButtonOpen.setText("Open");
    }

    private void open() {
        SimpleFragment simpleFragment = SimpleFragment.newInstance(mChoice);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment, simpleFragment).addToBackStack(null).commit();
        isFragmentDisplay=true;
        mButtonOpen.setText("Close");
    }
}