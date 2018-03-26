package com.vivi.artwork.activity;

import android.os.Bundle;

import com.bm.library.PhotoView;

import com.chenyuwei.basematerial.activity.BaseActivity;
import com.chenyuwei.loadimageview.ImageLoader;
import com.vivi.artwork.R;

public class PhotoActivity extends BaseActivity {

    private PhotoView ivPhoto;

    @Override
    protected int onBindView() {
        return R.layout.activity_photo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ivPhoto = (PhotoView) findViewById(R.id.ivPhoto);
        ImageLoader.with(activity,ivPhoto,getIntent().getStringExtra("photo"));
    }
}
