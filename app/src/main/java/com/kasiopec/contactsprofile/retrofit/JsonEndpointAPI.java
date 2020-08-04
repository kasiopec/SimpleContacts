package com.kasiopec.contactsprofile.retrofit;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface JsonEndpointAPI {
    @GET("users")
    Observable<List<UserData>> getUsers();
}
