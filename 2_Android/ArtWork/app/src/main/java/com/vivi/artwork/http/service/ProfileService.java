package com.vivi.artwork.http.service;

import com.vivi.artwork.model.json.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by p_yuweichen on 2017/12/21.
 */

public interface ProfileService {

    @GET("profile/detail")
    Call<User> detail(@Query("uid") int uid);

    @GET("profile/edit")
    Call<User> edit(@Query("uid") int uid, @Query("name") String name,
                        @Query("avatar") String avatar, @Query("birth") long birth, @Query("sex") int sex);
}
