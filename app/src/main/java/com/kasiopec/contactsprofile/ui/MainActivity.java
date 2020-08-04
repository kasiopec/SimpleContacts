package com.kasiopec.contactsprofile.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.kasiopec.contactsprofile.Contract;
import com.kasiopec.contactsprofile.R;
import com.kasiopec.contactsprofile.database.User;
import com.kasiopec.contactsprofile.repository.UserRepository;
import com.kasiopec.contactsprofile.presenter.Presenter;
import com.kasiopec.contactsprofile.retrofit.UserData;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements Contract.View, ItemClickHandler {
    RecyclerView rvUsers;
    UsersListAdapter usersListAdapter;
    Presenter presenter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserRepository repository = new UserRepository(getApplication());
        presenter = new Presenter(this, repository);
        presenter.getData();

        rvUsers = findViewById(R.id.rvUsers);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        usersListAdapter = new UsersListAdapter(this, new ArrayList<>(), this);
        rvUsers.setAdapter(usersListAdapter);
        compositeDisposable.add(presenter.getDbDataFromRepository()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> usersListAdapter.updateData(users)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
        presenter.onActivityDestroy();
    }

    @Override
    public void displayUserInfo(List<UserData> list) {


    }

    @Override
    public void onItemClicked(User user) {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra("passedUser", user);
        startActivity(intent);
    }
}

