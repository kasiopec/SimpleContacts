package com.kasiopec.contactsprofile.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kasiopec.contactsprofile.R;
import com.kasiopec.contactsprofile.presenter.Presenter;


public class MainActivity extends AppCompatActivity {

    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter();
        presenter.getData();
    }



}

