package com.kasiopec.contactsprofile;

import com.kasiopec.contactsprofile.database.User;
import com.kasiopec.contactsprofile.retrofit.UserData;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface Contract {

    interface View{
        void displayUserInfo(List<UserData> list);
    }

    interface Presenter{
        Flowable<List<User>> getDbDataFromRepository();
        void getData();
        void onActivityDestroy();
    }

    interface Repository{
        void fetchAllUsers();
        Flowable<List<User>> getAllUsersFromDb();
        void disposeDisposable();
    }
}
