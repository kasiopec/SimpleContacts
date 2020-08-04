package com.kasiopec.contactsprofile.presenter;

import com.kasiopec.contactsprofile.Contract;
import com.kasiopec.contactsprofile.model.UserModel;


public class Presenter implements Contract.Presenter {
    UserModel userModel = new UserModel(this);

    @Override
    public void getData() {
        userModel.fetchUserData();
    }
}
