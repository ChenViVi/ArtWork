package com.vivi.artwork.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chenyuwei.basematerial.activity.BaseRecyclerViewActivity;

import com.chenyuwei.loadimageview.LoadImageView;
import com.vivi.artwork.R;
import com.vivi.artwork.adapter.WorkAdapter;
import com.vivi.artwork.http.RequestMaker;
import com.vivi.artwork.http.ServiceFactory;
import com.vivi.artwork.model.json.Work;

public class UserWorkActivity extends BaseRecyclerViewActivity<Work.DataBean.WorksBean,WorkAdapter> {

    private LoadImageView ivAvatar;
    private TextView tvName;

    @Override
    protected int onBindView() {
        return R.layout.activity_user_work;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(R.id.toolbar);
        setDisplayHomeAsUpEnabled(true);
        setPullRefreshEnable(true);
        ivAvatar = (LoadImageView) findViewById(R.id.ivAvatar);
        tvName = (TextView) findViewById(R.id.tvName);
        ivAvatar.load(preferences.getString("avatar",""));
        tvName.setText(preferences.getString("name",""));
        new RequestMaker<Work>(activity, ServiceFactory.getWorkService().user(getUid())){

            @Override
            protected void onSuccess(final Work work) {
                data.addAll(work.getData().getWorks());
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        new RequestMaker<Work>(activity, ServiceFactory.getWorkService().user(getUid())){

            @Override
            protected void onSuccess(final Work work) {
                data.addAll(work.getData().getWorks());
                notifyDataSetChanged();
            }
        };
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new GridLayoutManager(activity,2);
    }

    @Override
    protected WorkAdapter setAdapter() {
        return new WorkAdapter(activity, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_work, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                startActivity(AddWorkActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}