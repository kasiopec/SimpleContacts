package com.kasiopec.contactsprofile;

import com.kasiopec.contactsprofile.retrofit.UserData;

import java.util.List;

public interface Contract {

    interface View{
        void displayUserInfo(List<UserData> list);
    }

    interface Presenter{
        void updateUserData(List<UserData> userDataList);
        void getData();

    }
}
