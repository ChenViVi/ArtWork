package com.vivi.artwork.adapter;

import android.app.Activity;

import android.preference.PreferenceManager;

import com.superrecycleview.superlibrary.adapter.BaseViewHolder;
import com.superrecycleview.superlibrary.adapter.SuperBaseAdapter;

import com.vivi.artwork.R;
import com.vivi.artwork.model.MessageDetail;



import java.util.List;

/**
 * Created by vivi on 2018/3/24.
 */

public class MessageDetailAdapter extends SuperBaseAdapter<MessageDetail> {

    public MessageDetailAdapter(Activity activity, List<MessageDetail> data) {
        super(activity, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder, MessageDetail item, final int position) {
        holder.setText(R.id.tvContent,item.getContent());
        holder.setImageURI(R.id.ivAvatar,item.getAvatar());
    }

    @Override
    protected int getItemViewLayoutId(int position, MessageDetail item) {
        return R.layout.item_message;
    }
}