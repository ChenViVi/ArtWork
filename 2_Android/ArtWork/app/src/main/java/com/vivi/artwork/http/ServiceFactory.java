package com.vivi.artwork.http;

import com.vivi.artwork.http.service.ProfileService;
import com.vivi.artwork.http.service.UserService;
import com.vivi.artwork.http.service.WorkService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vivi on 2016/9/17.
 */
public class ServiceFactory {
    private static String baseUrl = "http://139.199.32.74:8080/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static UserService getUserService() {
        return retrofit.create(UserService.class);
    }

    public static ProfileService getProfileService() {
        return retrofit.create(ProfileService.class);
    }

    public static WorkService getWorkService() {
        return retrofit.create(WorkService.class);
    }
}
