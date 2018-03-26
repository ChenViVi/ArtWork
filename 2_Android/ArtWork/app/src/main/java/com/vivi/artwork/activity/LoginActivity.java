package com.vivi.artwork.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chenyuwei.basematerial.activity.BaseActivity;
import com.chenyuwei.basematerial.util.Tool;
import com.chenyuwei.basematerial.view.dialog.WaitDialog;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;
import com.vivi.artwork.R;
import com.vivi.artwork.http.RequestMaker;
import com.vivi.artwork.http.ServiceFactory;
import com.vivi.artwork.model.json.User;

import java.util.concurrent.TimeUnit;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseActivity {

    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected int onBindView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(R.id.toolbar);
        setDisplayHomeAsUpEnabled(true);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.btnLogin:
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();
                if (Tool.isEmail(email)){
                    toast("邮箱格式错误");
                }
                else if (password.length() < 6){
                    toast("密码不能小于6位");
                }
                else if (password.length() > 18){
                    toast("密码不能大于18位");
                }
                else {
                    btnLogin.setEnabled(false);
                    final WaitDialog dialog = new WaitDialog(activity);
                    dialog.show();
                    new RequestMaker<User>(activity, ServiceFactory.getUserService().login(email, password)){

                        @Override
                        protected void onSuccess(final User user) {
                            Observable.timer(1, TimeUnit.SECONDS)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Consumer<Long>() {
                                        @Override
                                        public void accept(Long aLong) throws Exception {
                                            SharedPreferences.Editor editor = preferences.edit();
                                            editor.putInt("uid",user.getData().getId());
                                            editor.putString("name",user.getData().getName());
                                            editor.putString("email",user.getData().getEmail());
                                            editor.putString("birth",user.getData().getBirth());
                                            editor.putInt("sex",user.getData().getSex());
                                            editor.putString("avatar",user.getData().getAvatar());
                                            editor.putString("qqSign",user.getData().getQqSign());
                                            editor.apply();
                                            TIMManager.getInstance().login(user.getData().getEmail(), user.getData().getQqSign()
                                                    , new TIMCallBack() {
                                                @Override
                                                public void onError(int code, String desc) {
                                                    Log.e("fuck", "user1 login failed. code: " + code + " errmsg: " + desc);
                                                }

                                                @Override
                                                public void onSuccess() {
                                                    dialog.dismiss();
                                                    startActivity(MainActivity.class);
                                                    setResult(WelcomeActivity.RESULT_DESTROY);
                                                    finish();
                                                }
                                            });
                                        }
                                    }) ;
                        }

                        @Override
                        protected void onFail(int code, String msg) {
                            super.onFail(code, msg);
                            dialog.dismiss();
                            btnLogin.setEnabled(true);
                        }
                    };
                }
                break;
        }
    }
}
