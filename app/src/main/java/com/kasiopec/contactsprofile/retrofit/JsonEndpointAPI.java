package com.kasiopec.contactsprofile.retrofit;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Retrofit method that will return List UserData wrapped in Flowable
 * **/
public interface JsonEndpointAPI {
    @GET("users")
    Flowable<List<UserData>> getUsers();
}
