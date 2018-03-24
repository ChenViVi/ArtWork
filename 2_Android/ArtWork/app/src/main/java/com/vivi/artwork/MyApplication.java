package com.vivi.artwork;

import com.chenyuwei.basematerial.BaseApplication;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;

/**
 * Created by vivi on 2016/8/31.
 */

public class MyApplication extends BaseApplication {
    public static int PERMISSION_STORAGE = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        TIMManager.getInstance().init(this,  new TIMSdkConfig(1400077891));
    }
}