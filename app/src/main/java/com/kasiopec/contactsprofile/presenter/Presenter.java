package com.kasiopec.contactsprofile.presenter;

import com.kasiopec.contactsprofile.Contract;
import com.kasiopec.contactsprofile.database.User;
import com.kasiopec.contactsprofile.repository.UserRepository;
import java.util.List;
import io.reactivex.Flowable;

public class Presenter implements Contract.Presenter {
    UserRepository repository;
    Contract.View view;
    public Presenter(Contract.View view, UserRepository repository) {
        this.view = view;
        this.repository = repository;
    }


    @Override
    public Flowable<List<User>> getDbDataFromRepository() {
        return repository.getAllUsersFromDb();
    }

    @Override
    public void getData() {
        repository.fetchAllUsers();
    }

    @Override
    public void onActivityDestroy() {
        repository.disposeDisposable();
    }

}
