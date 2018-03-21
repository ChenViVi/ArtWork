package com.vivi.artwork.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chenyuwei.basematerial.activity.BaseActivity;
import com.chenyuwei.basematerial.util.Tool;
import com.chenyuwei.basematerial.view.dialog.WaitDialog;
import com.vivi.artwork.R;
import com.vivi.artwork.http.RequestMaker;
import com.vivi.artwork.http.ServiceFactory;
import com.vivi.artwork.model.User;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class RegisterActivity extends BaseActivity {


    private EditText etEmail;
    private EditText etPaswd;

    @Override
    protected int onBindView() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(R.id.toolbar);
        setDisplayHomeAsUpEnabled(true);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPaswd = (EditText) findViewById(R.id.etPassword);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
