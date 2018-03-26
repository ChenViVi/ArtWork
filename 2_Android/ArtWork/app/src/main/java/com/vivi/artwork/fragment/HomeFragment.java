package com.vivi.artwork.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chenyuwei.basematerial.fragment.BaseRecyclerViewFragment;

import com.vivi.artwork.R;
import com.vivi.artwork.adapter.WorkAdapter;
import com.vivi.artwork.http.RequestMaker;
import com.vivi.artwork.http.ServiceFactory;
import com.vivi.artwork.model.json.Work;


public class HomeFragment extends BaseRecyclerViewFragment<Work.DataBean.WorksBean,WorkAdapter> {

    private int uid;

    @Override
    protected void onCreateView() {
        super.onCreateView();
        uid = preferences.getInt("uid", - 1);
        setPullRefreshEnable(true);
        new RequestMaker<Work>(activity, ServiceFactory.getWorkService().list(-1)){

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
        new RequestMaker<Work>(activity, ServiceFactory.getWorkService().list(-1)){

            @Override
            protected void onSuccess(final Work work) {
                data.clear();
                data.addAll(work.getData().getWorks());
                notifyDataSetChanged();
                stopRefresh();
            }

            @Override
            protected void onFail(int code, String msg) {
                super.onFail(code, msg);
                stopRefresh();
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

