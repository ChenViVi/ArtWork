package com.vivi.artwork.adapter;

import android.app.Activity;
import android.preference.PreferenceManager;

import com.superrecycleview.superlibrary.adapter.BaseViewHolder;
import com.superrecycleview.superlibrary.adapter.SuperBaseAdapter;
import com.vivi.artwork.R;
import com.vivi.artwork.model.Message;
import com.vivi.artwork.model.MessageDetail;

import java.util.List;

/**
 * Created by vivi on 2018/3/24.
 */

public class MessageAdapter extends SuperBaseAdapter<Message> {

    public MessageAdapter(Activity activity, List<Message> data) {
        super(activity, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder, Message item, final int position) {

        holder.setText(R.id.tvName,item.getName());
        holder.setText(R.id.tvContent,item.getContent());
        holder.setImageURI(R.id.ivAvatar,item.getAvatar());
    }

    @Override
    protected int getItemViewLayoutId(int position, Message item) {
        return R.layout.item_message;
    }
}