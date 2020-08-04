package com.kasiopec.contactsprofile.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.kasiopec.contactsprofile.Contract;
import com.kasiopec.contactsprofile.R;
import com.kasiopec.contactsprofile.presenter.Presenter;
import com.kasiopec.contactsprofile.retrofit.UserData;

import java.util.List;


public class MainActivity extends AppCompatActivity implements Contract.View {
    RecyclerView rvUsers;
    UsersListAdapter usersListAdapter;
    Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter(this);
        presenter.getData();

        rvUsers = findViewById(R.id.rvUsers);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public void displayUserInfo(List<UserData> list) {
        usersListAdapter = new UsersListAdapter(this, list);
        rvUsers.setAdapter(usersListAdapter);
    }
}

