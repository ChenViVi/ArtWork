package com.vivi.artwork.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.superrecycleview.superlibrary.adapter.BaseViewHolder;
import com.superrecycleview.superlibrary.adapter.SuperBaseAdapter;
import com.vivi.artwork.R;
import com.vivi.artwork.activity.OtherUserWorkActivity;
import com.vivi.artwork.activity.PhotoActivity;
import com.vivi.artwork.model.json.Work;

import java.util.List;

/**
 * Created by vivi on 2018/3/24.
 */

public class WorkAdapter extends SuperBaseAdapter<Work.DataBean.WorksBean> {

    public WorkAdapter(Activity activity, List<Work.DataBean.WorksBean> data) {
        super(activity, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final Work.DataBean.WorksBean item, final int position) {
        holder.setText(R.id.tvName,item.getUserEntity().getName());
        holder.setImageURI(R.id.ivWork,item.getWorkEntity().getUrl());
        holder.setImageURI(R.id.ivAvatar,item.getUserEntity().getAvatar());
        holder.setOnClickListener(R.id.ivWork,new OnItemChildClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PhotoActivity.class);
                intent.putExtra("photo",item.getWorkEntity().getUrl());
                activity.startActivity(intent);
            }
        });
        holder.setOnClickListener(R.id.ivAvatar,new OnItemChildClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, OtherUserWorkActivity.class);
                intent.putExtra("id",item.getUserEntity().getId());
                intent.putExtra("name",item.getUserEntity().getName());
                intent.putExtra("avatar",item.getUserEntity().getAvatar());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    protected int getItemViewLayoutId(int position, Work.DataBean.WorksBean item) {
        return R.layout.item_work;
    }
}