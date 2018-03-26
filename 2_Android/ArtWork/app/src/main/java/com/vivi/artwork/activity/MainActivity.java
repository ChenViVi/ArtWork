package com.vivi.artwork.activity;

import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;

import com.chenyuwei.basematerial.activity.BaseTabBottomActivity;
import com.chenyuwei.basematerial.fragment.BaseDrawerFragment;
import com.vivi.artwork.R;
import com.vivi.artwork.fragment.HomeFragment;
import com.vivi.artwork.fragment.MessageFragment;
import com.vivi.artwork.fragment.TypeFragment;

public class MainActivity extends BaseTabBottomActivity {

    private BaseDrawerFragment drawerFragment;

    @Override
    protected int onBindView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(R.id.toolbar);
        drawerFragment = (BaseDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        addFragment(new HomeFragment(),"首页", R.drawable.ic_tab_home);
        addFragment(new TypeFragment(),"发现",R.drawable.ic_tab_find);
        addFragment(new MessageFragment(),"消息",R.drawable.ic_tab_message);
        initialise();
    }
}
