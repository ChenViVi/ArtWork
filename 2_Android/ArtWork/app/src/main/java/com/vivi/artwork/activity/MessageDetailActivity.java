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
import com.tencent.imsdk.ext.message.TIMConversationExt;
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
    private TIMConversation conversation;
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
        conversation = TIMManager.getInstance().getConversation(TIMConversationType.C2C, objectEmail);
        TIMConversationExt conExt = new TIMConversationExt(conversation);
        conExt.getMessage(10, null, //不指定从哪条消息开始获取 - 等同于从最新的消息开始往前
                new TIMValueCallBack<List<TIMMessage>>() {//回调接口
                    @Override
                    public void onError(int code, String desc) {//获取消息失败
                        //接口返回了错误码code和错误描述desc，可用于定位请求失败原因
                        //错误码code含义请参见错误码表
                        Log.d("fuck", "get message failed. code: " + code + " errmsg: " + desc);
                    }

                    @Override
                    public void onSuccess(List<TIMMessage> msgs) {//获取消息成功
                        //遍历取得的消息
                        for(TIMMessage msg : msgs) {
                            //可以通过timestamp()获得消息的时间戳, isSelf()是否为自己发送的消息
                            for(int i = 0; i < msg.getElementCount(); ++i) {
                                TIMElem elem = msg.getElement(i);
                                TIMElemType elemType = elem.getType();
                                if (elemType == TIMElemType.Text) {
                                    String s = ((TIMTextElem)elem).getText();
                                    Log.e("fuck","get message is " + s);
                                    if (msg.isSelf()){
                                        data.add(new MessageDetail(name,avatar,s));
                                    }
                                    else {
                                        data.add(new MessageDetail(objectName,objectAvatar,s));
                                    }
                                }
                            }
                        }
                        notifyDataSetChanged();
                        recyclerView.scrollToPosition(data.size() - 1);
                    }
                });
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
                            data.add(new MessageDetail(objectName,objectAvatar,s));
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
                final String content = etContent.getText().toString();
                TIMMessage msg = new TIMMessage();
                TIMTextElem elem = new TIMTextElem();
                elem.setText(content);
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
                        etContent.setText("");
                        data.add(new MessageDetail(name,avatar,content));
                        notifyDataSetChanged();
                        recyclerView.scrollToPosition(data.size() - 1);
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
