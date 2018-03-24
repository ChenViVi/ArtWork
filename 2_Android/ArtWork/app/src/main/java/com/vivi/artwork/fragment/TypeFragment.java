package com.vivi.artwork.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chenyuwei.basematerial.fragment.BaseRecyclerViewFragment;
import com.vivi.artwork.adapter.TypeAdapter;
import com.vivi.artwork.http.RequestMaker;
import com.vivi.artwork.http.ServiceFactory;
import com.vivi.artwork.model.json.Type;


public class TypeFragment extends BaseRecyclerViewFragment<Type.DataBean,TypeAdapter> {

    @Override
    protected void onCreateView() {
        super.onCreateView();
        setPullRefreshEnable(true);
        new RequestMaker<Type>(activity, ServiceFactory.getWorkService().type()){

            @Override
            protected void onSuccess(final Type type) {
                data.addAll(type.getData());
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        new RequestMaker<Type>(activity, ServiceFactory.getWorkService().type()){

            @Override
            protected void onSuccess(final Type type) {
                data.addAll(type.getData());
                notifyDataSetChanged();
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
    protected TypeAdapter setAdapter() {
        return new TypeAdapter(activity, data);
    }
}

