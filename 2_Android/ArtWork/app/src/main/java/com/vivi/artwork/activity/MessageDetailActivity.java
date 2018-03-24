package com.vivi.artwork.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chenyuwei.basematerial.activity.BaseRecyclerViewActivity;
import com.vivi.artwork.R;
import com.vivi.artwork.adapter.MessageDetailAdapter;
import com.vivi.artwork.model.MessageDetail;

public class MessageDetailActivity extends BaseRecyclerViewActivity<MessageDetail,MessageDetailAdapter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(R.id.toolbar);
        //setTitle(getIntent().getStringExtra("name"));
        setDisplayHomeAsUpEnabled(true);
        data.add(new MessageDetail("sender1",preferences.getString("avatar",""),"test1"));
        data.add(new MessageDetail("sender2",preferences.getString("avatar",""),"test2"));
        notifyDataSetChanged();
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    @Override
    protected MessageDetailAdapter setAdapter() {
        return new MessageDetailAdapter(activity, data);
    }
}
