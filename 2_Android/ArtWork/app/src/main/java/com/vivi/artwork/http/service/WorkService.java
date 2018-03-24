package com.vivi.artwork.http.service;

import com.vivi.artwork.model.json.Type;
import com.vivi.artwork.model.json.Work;
import com.vivi.artwork.model.json.base.BaseModel;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by p_yuweichen on 2017/12/21.
 */

public interface WorkService {

    @GET("work/add")
    Call<BaseModel> add(@Query("uid") int uid, @Query("type_id") int type, @Query("img") String img);

    @GET("work/user")
    Call<Work> user(@Query("uid") int uid);

    @GET("work/list")
    Call<Work> list(@Query("id") int id);

    @GET("work/type")
    Call<Type> type();
}
