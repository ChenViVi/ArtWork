package com.vivi.artwork.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chenyuwei.basematerial.fragment.BaseRecyclerViewFragment;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.message.TIMConversationExt;
import com.tencent.imsdk.ext.message.TIMManagerExt;
import com.vivi.artwork.activity.MessageDetailActivity;
import com.vivi.artwork.adapter.MessageAdapter;
import com.vivi.artwork.http.RequestMaker;
import com.vivi.artwork.http.ServiceFactory;
import com.vivi.artwork.model.Message;
import com.vivi.artwork.model.MessageDetail;
import com.vivi.artwork.model.json.User;

import java.util.List;

public class MessageFragment extends BaseRecyclerViewFragment<Message,MessageAdapter> {

    @Override
    protected void onCreateView() {
        super.onCreateView();
        setPullRefreshEnable(true);
        List<TIMConversation> list = TIMManagerExt.getInstance().getConversationList();
        for (TIMConversation con : list){
            TIMConversationExt conExt = new TIMConversationExt(con);
            TIMMessage msg =conExt.getLastMsgs(1).get(0);
            TIMElem elem = msg.getElement(0);
            TIMElemType elemType = elem.getType();
            if (elemType == TIMElemType.Text) {
                final String s = ((TIMTextElem)elem).getText();
                Log.e("fuck","get message is " + s);
                new RequestMaker<User>(activity, ServiceFactory.getProfileService().detail(con.getPeer())){
                    @Override
                    protected void onSuccess(final User user) {
                        data.add(new Message(user.getData().getEmail(),user.getData().getName(),user.getData().getAvatar(),s));
                    }
                };
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        data.clear();
        List<TIMConversation> list = TIMManagerExt.getInstance().getConversationList();
        for (TIMConversation con : list){
            TIMConversationExt conExt = new TIMConversationExt(con);
            TIMMessage msg =conExt.getLastMsgs(1).get(0);
            TIMElem elem = msg.getElement(0);
            TIMElemType elemType = elem.getType();
            if (elemType == TIMElemType.Text) {
                final String s = ((TIMTextElem)elem).getText();
                Log.e("fuck","get message is " + s);
                new RequestMaker<User>(activity, ServiceFactory.getProfileService().detail(msg.getSender())){
                    @Override
                    protected void onSuccess(final User user) {
                        data.add(new Message(user.getData().getEmail(),user.getData().getName(),user.getData().getAvatar(),s));
                    }
                };
            }
            notifyDataSetChanged();
            stopRefresh();
        }
    }

    @Override
    protected void onItemClick(View view, int position, Message message) {
        super.onItemClick(view, position, message);
        Intent intent = new Intent(activity, MessageDetailActivity.class);
        intent.putExtra("email" ,message.getEmail());
        intent.putExtra("name", message.getName());
        intent.putExtra("avatar", message.getAvatar());
        startActivity(intent);
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
