package com.vivi.artwork.fragment;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chenyuwei.basematerial.fragment.BaseDrawerFragment;
import com.chenyuwei.loadimageview.LoadImageView;
import com.chenyuwei.loadimageview.Options;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;
import com.vivi.artwork.R;
import com.vivi.artwork.activity.ProfileActivity;
import com.vivi.artwork.activity.UserWorkActivity;
import com.vivi.artwork.activity.WelcomeActivity;


/**
 * Created by vivi on 2016/9/2.
 */
public class DrawFragment extends BaseDrawerFragment {

    private LoadImageView ivAvatar;
    private TextView tvName;

    @Override
    protected int onBindView() {
        return R.layout.fragment_drawer;
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        findViewById(R.id.llProfile);
        findViewById(R.id.llWork);
        findViewById(R.id.llLogout);
        tvName = (TextView) findViewById(R.id.tvName);
        ivAvatar = (LoadImageView) findViewById(R.id.ivAvatar);
        tvName.setText(preferences.getString("name",""));
        ivAvatar.setShaple(Options.Shape.CIRCLE).load(preferences.getString("avatar",""));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.llProfile:
                startActivity(ProfileActivity.class);
                break;
            case R.id.llWork:
                startActivity(UserWorkActivity.class);
                break;
            case R.id.llLogout:
                TIMManager.getInstance().logout(new TIMCallBack() {
                    @Override
                    public void onError(int code, String desc) {
                        Log.e("fuck", "logout failed. code: " + code + " errmsg: " + desc);
                        toast("注销失败");
                    }

                    @Override
                    public void onSuccess() {
                        //登出成功
                        Log.e("fuck", "登出成功");
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putLong("uid", -1);
                        editor.apply();
                        startActivity(WelcomeActivity.class);
                        activity.finish();
                    }
                });
                break;
        }
    }
}
