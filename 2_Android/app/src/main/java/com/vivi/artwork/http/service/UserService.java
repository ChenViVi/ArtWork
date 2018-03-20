package com.vivi.artwork.http.service;

import com.vivi.artwork.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by p_yuweichen on 2017/12/21.
 */

public interface UserService {

    @GET("user/login")
    Call<User> login(@Query("email") String email, @Query("password") String password);

    @GET("user/register")
    Call<User> register(@Query("email") String email, @Query("password") String password, String name, String birth, int sex);

    @GET("user/detail")
    Call<User> detail(@Query("uid") String uid);
}
