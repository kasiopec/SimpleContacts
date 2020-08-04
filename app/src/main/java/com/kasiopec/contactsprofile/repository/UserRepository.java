package com.kasiopec.contactsprofile.repository;

import android.app.Application;
import android.util.Log;

import com.kasiopec.contactsprofile.Contract;
import com.kasiopec.contactsprofile.database.User;
import com.kasiopec.contactsprofile.database.UserDataDAO;
import com.kasiopec.contactsprofile.database.UserRoomDatabase;
import com.kasiopec.contactsprofile.retrofit.JsonEndpointAPI;
import com.kasiopec.contactsprofile.retrofit.RetrofitClient;
import com.kasiopec.contactsprofile.retrofit.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class UserRepository implements Contract.Repository {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private JsonEndpointAPI endpointAPI;
    private UserDataDAO userDataDAO;

    public UserRepository(Application application){
        UserRoomDatabase db = UserRoomDatabase.getInstance(application);
        userDataDAO = db.getUserDao();
        Retrofit retrofit = RetrofitClient.getInstance();
        endpointAPI = retrofit.create(JsonEndpointAPI.class);
    }

    /**
     * Method that fetches data from the endpoint API server
     * converts response [UserData] object to [User] object
     * and finally adds list of the fetched objects to the Room database
     * Method is repeated every 10 minutes to fetch latest data
     * **/
    @Override
    public void fetchAllUsers() {
        compositeDisposable.add(endpointAPI.getUsers()
                .subscribeOn(Schedulers.io())
                .repeatWhen(completed -> completed.delay(5, TimeUnit.SECONDS))
                .subscribe(response -> {
                    List<User> userList = new ArrayList<>();

                    for(UserData responseItem : response){
                        User user = new User();
                        user.setName(responseItem.getName());
                        user.setId(responseItem.getId());
                        user.setEmail(responseItem.getEmail());
                        user.setCity(responseItem.getAddress().getCity());
                        user.setStreet(responseItem.getAddress().getStreet());
                        user.setImageUrl("");
                        user.setPhoneNumber(responseItem.getPhone());
                        userList.add(user);
                    }
                    Log.d("RESPONSE", "TICK");
                    //Adding users to the database after converting JSON response into DB entity
                    userDataDAO.upsertAllUsers(userList);
                }));
    }

    /**
     * Calls room database in order to return latest data
     * **/
    @Override
    public Flowable<List<User>> getAllUsersFromDb() {
        return userDataDAO.getAllUsers().switchMap(Flowable::just);
    }

    /**
     * Disposes all active observers, to avoid memory leaks.
     * **/
    @Override
    public void disposeDisposable() {
        compositeDisposable.dispose();
    }
}
