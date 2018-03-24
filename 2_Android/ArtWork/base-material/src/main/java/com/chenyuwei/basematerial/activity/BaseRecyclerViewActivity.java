package com.chenyuwei.basematerial.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chenyuwei.basematerial.R;
import com.chenyuwei.basematerial.adapter.BaseRecyclerViewAdapter;
import com.chenyuwei.basematerial.fragment.BaseRecyclerViewFragment;
import com.superrecycleview.superlibrary.adapter.SuperBaseAdapter;
import com.superrecycleview.superlibrary.recycleview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivi on 2016/9/3.
 */

public abstract class BaseRecyclerViewActivity<Item, Adapter extends SuperBaseAdapter> extends BaseActivity implements SuperRecyclerView.LoadingListener {

    protected ArrayList<Item> data = new ArrayList<>();
    private Adapter adapter;
    private SuperRecyclerView recyclerView;
    private Toolbar toolbar;

    @Override
    protected int onBindView() {
        return R.layout.base_activity_recycle_view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = (SuperRecyclerView) findViewById(R.id.recyclerView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adapter = setAdapter();
        recyclerView.setLayoutManager(setLayoutManager());
        recyclerView.setAdapter(adapter);
        setPullLoadEnable(false);
        setPullRefreshEnable(false);
        recyclerView.setLoadingListener(this);
        clearItems();
        adapter.setOnItemClickListener(new SuperBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {
                Item i = (Item) item;
                BaseRecyclerViewActivity.this.onItemClick(view,  position,i);
            }
        });
    }

    protected abstract RecyclerView.LayoutManager setLayoutManager();

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    protected void setPullLoadEnable(boolean enable) {
        recyclerView.setLoadMoreEnabled(enable);
    }

    protected void setPullRefreshEnable(boolean enable) {
        recyclerView.setRefreshEnabled(enable);
    }

    protected void stopRefresh() {
        recyclerView.completeRefresh();
    }

    protected void stopLoadMore() {
        recyclerView.completeLoadMore();
    }

    protected void notifyDataSetChanged(){
        adapter.notifyDataSetChanged();
    }

    protected void clearItems() {
        if (data.size() > 0) {
            data.clear();
            adapter.notifyDataSetChanged();
        }
    }

    protected abstract Adapter setAdapter();

    protected void onItemClick(View view, int position, Item item){

    }
}
