package com.vivi.artwork.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chenyuwei.basematerial.activity.BaseRecyclerViewActivity;

import com.vivi.artwork.adapter.WorkAdapter;
import com.vivi.artwork.http.RequestMaker;
import com.vivi.artwork.http.ServiceFactory;
import com.vivi.artwork.model.json.Work;

public class UserWorkActivity extends BaseRecyclerViewActivity<Work.DataBean.WorksBean,WorkAdapter> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setPullRefreshEnable(true);
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
}
