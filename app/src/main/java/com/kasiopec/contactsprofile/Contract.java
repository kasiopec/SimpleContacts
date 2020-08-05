package com.kasiopec.contactsprofile;

import com.kasiopec.contactsprofile.database.User;
import com.kasiopec.contactsprofile.retrofit.UserData;

import java.util.List;

import io.reactivex.Flowable;

public interface Contract {

    interface View{
        void displayErrorToast(String errorMessage);
        void displayUserData(List<User> userList);
    }

    interface Presenter{
        void startFetching();
        void onActivityDestroy();
    }

    interface Repository{
        void fetchAllUsers();
        Flowable<List<User>> getAllUsersFromDb();
        void disposeDisposable();
    }
}
