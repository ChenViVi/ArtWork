package com.vivi.artwork.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.chenyuwei.basematerial.fragment.BaseRecyclerViewFragment;
import com.vivi.artwork.adapter.MessageAdapter;
import com.vivi.artwork.model.Message;

public class MessageFragment extends BaseRecyclerViewFragment<Message,MessageAdapter> {

    @Override
    protected void onCreateView() {
        super.onCreateView();
        setPullRefreshEnable(true);
        data.add(new Message("sender1",preferences.getString("avatar",""),"test1"));
        data.add(new Message("sender2",preferences.getString("avatar",""),"test2"));
        notifyDataSetChanged();
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    @Override
    protected MessageAdapter setAdapter() {
        return new MessageAdapter(activity, data);
    }
}
