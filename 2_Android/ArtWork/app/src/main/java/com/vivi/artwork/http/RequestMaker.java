package com.vivi.artwork.http;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vivi.artwork.model.json.base.BaseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vivi on 2016/9/17.
 */
public abstract class RequestMaker<T extends BaseModel> {
    private Call<T> mCall;
    private Context mContext;
    private final int SUCCESS_CODE = 200;
    private final String TAG = "RequestMaker";

    public RequestMaker(Context context, Call<T> call) {
        onStart();
        mCall = call;
        mContext = context;
        Log.d(TAG, mCall.request().url().encodedPath() + "=>"+"url=" + mCall.request().url().toString());
        mCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful() && response.errorBody() == null) {
                    if (response.body().getCode() == SUCCESS_CODE) {
                        Gson gson = new Gson();
                        Log.d(TAG, mCall.request().url().encodedPath() + "=>"+"body=" + gson.toJson(response.body()));
                        onSuccess(response.body());
                    } else {
                        Toast.makeText(mContext, response.body().getMsg(), Toast.LENGTH_LONG).show();
                        onFail(response.body().getCode(),response.body().getMsg());
                    }
                } else {
                    Log.d(TAG, mCall.request().url().encodedPath() + "=>"+"error_code=" + response.code());
                    Log.d(TAG, mCall.request().url().encodedPath() + "=>"+"error_message=" + response.message());
                    Toast.makeText(mContext, "网络请求失败", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(TAG, mCall.request().url().encodedPath() + "=>" + "bug=" + t.getMessage());
                Toast.makeText(mContext, "网络连接失败", Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void onStart(){}

    protected abstract void onSuccess(T response);

    protected void onFail(int code, String msg){
        Log.d(TAG, mCall.request().url().encodedPath() + "=>"+"code=" + code);
        Log.d(TAG, mCall.request().url().encodedPath() + "=>"+"msg=" + msg);
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }
}
