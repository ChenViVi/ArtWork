package com.vivi.artwork.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.chenyuwei.basematerial.activity.BaseRecyclerViewActivity;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;
import com.vivi.artwork.R;
import com.vivi.artwork.adapter.MessageDetailAdapter;
import com.vivi.artwork.model.MessageDetail;

import java.util.List;

public class MessageDetailActivity extends BaseRecyclerViewActivity<MessageDetail,MessageDetailAdapter> {

    private String objectEmail;
    private String objectAvatar;
    private String objectName;
    private EditText etContent;
    private TextView tvSend;
    private String avatar;
    private String name;

    @Override
    protected int onBindView() {
        return R.layout.activity_message_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(R.id.toolbar);
        setTitle(getIntent().getStringExtra("name"));
        setDisplayHomeAsUpEnabled(true);
        tvSend = (TextView) findViewById(R.id.tvSend);
        etContent = (EditText) findViewById(R.id.etContent);
        objectEmail = getIntent().getStringExtra("email");
        objectAvatar = getIntent().getStringExtra("avatar");
        objectName = getIntent().getStringExtra("name");
        avatar = preferences.getString("avatar","");
        name = preferences.getString("name","");
        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {
            @Override
            public boolean onNewMessages(List<TIMMessage> list) {
                Log.e("fuck", "get message success");
                TIMMessage msg = list.get(0);
                for(int i = 0; i < msg.getElementCount(); ++i) {
                    TIMElem elem = msg.getElement(i);
                    TIMElemType elemType = elem.getType();
                    if (elemType == TIMElemType.Text) {
                        String s = ((TIMTextElem)elem).getText();
                        Log.e("fuck","get message is " + s);
                        if (msg.getSender().equals(objectEmail)){
                            data.add(new MessageDetail(msg.getSender(),objectAvatar,s));
                        }
                    }
                }
                notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.tvSend:
                debug();
                TIMConversation conversation = TIMManager.getInstance().getConversation(TIMConversationType.C2C, objectEmail);
                TIMMessage msg = new TIMMessage();
                TIMTextElem elem = new TIMTextElem();
                elem.setText(etContent.getText().toString());
                if(msg.addElement(elem) != 0) {
                    Log.e("fuck", "user2 addElement failed");
                    return;
                }
                conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
                    @Override
                    public void onError(int code, String desc) {//发送消息失败
                        Log.e("fuck", "send message failed. code: " + code + " errmsg: " + desc);
                    }

                    @Override
                    public void onSuccess(TIMMessage msg) {//发送消息成功
                        debug();
                        data.add(new MessageDetail(name,avatar,etContent.getText().toString()));
                        notifyDataSetChanged();
                    }
                });
                break;
        }
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
