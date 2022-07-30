package com.example.notes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.notes.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = new MainFragment();



        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,mainFragment)//удаляем предыдущий дбавляем новый
                    .commit();

        }
        else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, mainFragment)//удаляем предыдущий дбавляем новый
                    .commit();
        }
//
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment_container_frame_layout, mainFragment)
//                .commit();
    }

    /*
    data class - name date body
    lanscape orientation - 2 fragments

     */
}