package com.vivi.artwork.fragment;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

import com.chenyuwei.basematerial.fragment.BaseDrawerFragment;
import com.chenyuwei.loadimageview.LoadImageView;
import com.vivi.artwork.R;
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
        findViewById(R.id.llLogout);
        tvName = (TextView) findViewById(R.id.tvName);
        ivAvatar = (LoadImageView) findViewById(R.id.ivAvatar);
        tvName.setText(preferences.getString("name",""));
        ivAvatar.setDefaultSrc(R.drawable.default_avatar).load(preferences.getString("avatar",""));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.llLogout:
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong("uid", -1);
                editor.apply();
                startActivity(WelcomeActivity.class);
                activity.finish();
                break;
        }
    }
}
