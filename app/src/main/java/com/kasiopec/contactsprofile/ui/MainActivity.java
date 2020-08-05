package com.kasiopec.contactsprofile.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.kasiopec.contactsprofile.Contract;
import com.kasiopec.contactsprofile.R;
import com.kasiopec.contactsprofile.database.User;
import com.kasiopec.contactsprofile.repository.UserRepository;
import com.kasiopec.contactsprofile.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements Contract.View, ItemClickHandler {
    RecyclerView rvUsers;
    UsersListAdapter usersListAdapter;
    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserRepository repository = new UserRepository(getApplication());
        presenter = new Presenter(this, repository);
        presenter.startFetching();

        rvUsers = findViewById(R.id.rvUsers);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        usersListAdapter = new UsersListAdapter(this, new ArrayList<>(), this);
        rvUsers.setAdapter(usersListAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onActivityDestroy();
    }

    @Override
    public void displayErrorToast(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayUserData(List<User> userList) {
        usersListAdapter.updateData(userList);
    }

    @Override
    public void onItemClicked(User user) {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra("passedUser", user);
        startActivity(intent);
    }
}

