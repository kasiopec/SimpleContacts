package com.kasiopec.contactsprofile.presenter;

import com.kasiopec.contactsprofile.Contract;
import com.kasiopec.contactsprofile.model.UserModel;
import com.kasiopec.contactsprofile.retrofit.UserData;
import com.kasiopec.contactsprofile.ui.MainActivity;

import java.util.List;


public class Presenter implements Contract.Presenter {
    UserModel userModel = new UserModel(this);
    Contract.View view;
    public Presenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void getData() {
        userModel.fetchUserData();
    }


    @Override
    public void updateUserData(List<UserData> userDataList) {
        view.displayUserInfo(userDataList);
    }
}
