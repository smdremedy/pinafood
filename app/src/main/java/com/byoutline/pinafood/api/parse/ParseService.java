package com.byoutline.pinafood.api.parse;

import com.byoutline.pinafood.api.parse.model.LoggedInResponse;
import com.byoutline.pinafood.api.parse.model.User;
import com.byoutline.pinafood.api.parse.model.Pin;
import com.byoutline.pinafood.api.parse.model.PinsResult;
import com.byoutline.pinafood.api.parse.model.UserCreatedResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

public interface ParseService {

    @Headers("Content-Type: application/json")
    @POST("/users")
    void postUser(@Body User user,
                  Callback<UserCreatedResponse> callback);

    @GET("/login")
    void getLogin(@Query("username")String username,
                  @Query("password")String password,
                  Callback<UserCreatedResponse> callback);


    @POST("/classes/Pin")
    void postNewPin(@Body Pin pin,
                    Callback<Pin> callback);

    @GET("/classes/Pin")
    void getPins(Callback<PinsResult> callback);
}
