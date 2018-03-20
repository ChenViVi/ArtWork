package com.vivi.artwork.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chenyuwei.basematerial.activity.BaseActivity;
import com.vivi.artwork.R;

public class LoginActivity extends BaseActivity {

    @Override
    protected int onBindView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
