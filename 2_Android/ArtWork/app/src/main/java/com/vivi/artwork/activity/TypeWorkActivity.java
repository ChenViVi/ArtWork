package com.vivi.artwork.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chenyuwei.basematerial.activity.BaseRecyclerViewActivity;
import com.vivi.artwork.R;
import com.vivi.artwork.adapter.WorkAdapter;
import com.vivi.artwork.http.RequestMaker;
import com.vivi.artwork.http.ServiceFactory;
import com.vivi.artwork.model.json.Work;

public class TypeWorkActivity extends BaseRecyclerViewActivity<Work.DataBean.WorksBean,WorkAdapter> {

    private int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(R.id.toolbar);
        setDisplayHomeAsUpEnabled(true);
        setPullRefreshEnable(true);
        id = getIntent().getIntExtra("id",0);
        setTitle(getIntent().getStringExtra("name"));
        new RequestMaker<Work>(activity, ServiceFactory.getWorkService().list(id)){

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
}