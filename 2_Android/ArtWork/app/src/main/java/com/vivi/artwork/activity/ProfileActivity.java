package com.vivi.artwork.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.chenyuwei.basematerial.activity.BaseActivity;
import com.chenyuwei.loadimageview.LoadImageView;
import com.vivi.artwork.R;

public class ProfileActivity extends BaseActivity {

    private LoadImageView ivAvatar;
    private TextView tvName;
    private TextView tvBirth;
    private TextView tvSex;

    @Override
    protected int onBindView() {
        return R.layout.activity_profile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(R.id.toolbar);
        setDisplayHomeAsUpEnabled(true);
        ivAvatar = (LoadImageView) findViewById(R.id.ivAvatar);
        tvName = (TextView) findViewById(R.id.tvName);
        tvBirth = (TextView) findViewById(R.id.tvBirth);
        tvSex = (TextView) findViewById(R.id.tvSex);
        ivAvatar.load((preferences.getString("avatar","")));
        tvName.setText(("姓名：" + preferences.getString("name","")));
        tvBirth.setText("生日：" + preferences.getString("birth",""));
        if (preferences.getInt("sex",-1) != 0){
            tvSex.setText("性别：女");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ivAvatar.load((preferences.getString("avatar","")));
        tvName.setText(("姓名：" + preferences.getString("name","")));
        tvBirth.setText("生日：" + preferences.getString("birth",""));
        if (preferences.getInt("sex",-1) != 0){
            tvSex.setText("性别：女");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                startActivity(EditProfileActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
