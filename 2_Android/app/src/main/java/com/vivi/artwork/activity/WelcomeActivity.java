package com.vivi.artwork.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;

import com.chenyuwei.basematerial.activity.BaseActivity;
import com.vivi.artwork.R;

public class WelcomeActivity extends BaseActivity {

    public static final int REQUEST_DESTROY = 101;
    public static final int RESULT_DESTROY = 101;

    @Override
    protected int onBindView() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.llLogin);
        findViewById(R.id.llRegister);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.llLogin: startActivity(LoginActivity.class);break;
            case R.id.llRegister: startActivity(RegisterActivity.class);break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_DESTROY && resultCode == RESULT_DESTROY) {
            finish();
        }
    }
}
