package com.kasiopec.contactsprofile.model;

import android.util.Log;

import com.kasiopec.contactsprofile.presenter.Presenter;
import com.kasiopec.contactsprofile.retrofit.JsonEndpointAPI;
import com.kasiopec.contactsprofile.retrofit.RetrofitClient;
import com.kasiopec.contactsprofile.retrofit.UserData;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class UserModel {
    private Presenter presenter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    JsonEndpointAPI endpointAPI;
    Retrofit retrofit;

    public UserModel(Presenter presenter) {
        this.presenter = presenter;
        retrofit = RetrofitClient.getInstance();
        endpointAPI = retrofit.create(JsonEndpointAPI.class);
    }


    public void fetchUserData() {
        compositeDisposable.add(endpointAPI.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    presenter.updateUserData(users);
                }));
    }


    public Observable<List<UserData>> getRetrofitObservable() {
        return RetrofitClient.getInstance().create(JsonEndpointAPI.class)
                .getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<UserData> getObserver() {
        return new DisposableObserver<UserData>() {

            @Override
            public void onNext(@NonNull UserData userData) {
                //mvi.displayMovies(movieResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
                //mvi.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
            }
        };
    }
}
