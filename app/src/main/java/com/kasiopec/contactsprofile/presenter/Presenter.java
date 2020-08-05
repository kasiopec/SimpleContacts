package com.kasiopec.contactsprofile.presenter;

import com.kasiopec.contactsprofile.Contract;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class Presenter implements Contract.Presenter {
    Contract.Repository repository;
    Contract.View view;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public Presenter(Contract.View view, Contract.Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    /**
     * Method that performs observation of the data in the database
     * When the new data is received it will tell the view to display it
     * In case there is an error, error toast will be displayed
     * **/
    private void observeRoomDatabase() {
        compositeDisposable.add(repository.getAllUsersFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    view.displayUserData(users);
                }, error -> {
                    view.displayErrorToast(error.getMessage());
                }));
    }

    /**
     * Method starts a fetch process inside the repository and receives
     * data from the room database
     * **/
    @Override
    public void startFetching() {
        repository.fetchAllUsers();
        observeRoomDatabase();
    }

    /**
     * Method which disposes all the subscriptions to avoid leaks
     * **/
    @Override
    public void onActivityDestroy() {
        compositeDisposable.dispose();
        repository.disposeDisposable();
    }

}
