package com.vivi.artwork.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chenyuwei.basematerial.fragment.BaseRecyclerViewFragment;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.message.TIMConversationExt;
import com.tencent.imsdk.ext.message.TIMManagerExt;
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
            conExt.getLocalMessage(1, //获取此会话最近的10条消息
                    null, //不指定从哪条消息开始获取 - 等同于从最新的消息开始往前
                    new TIMValueCallBack<List<TIMMessage>>() {//回调接口
                        @Override
                        public void onError(int code, String desc) {//获取消息失败
                            //接口返回了错误码code和错误描述desc，可用于定位请求失败原因
                            //错误码code含义请参见错误码表
                            Log.e("fuck", "get message failed. code: " + code + " errmsg: " + desc);
                        }

                        @Override
                        public void onSuccess(List<TIMMessage> msgs) {//获取消息成功
                            //遍历取得的消息
                            final TIMMessage msg = msgs.get(0);
                            TIMElem elem = msg.getElement(0);
                            TIMElemType elemType = elem.getType();
                            if (elemType == TIMElemType.Text) {
                                final String s = ((TIMTextElem)elem).getText();
                                Log.e("fuck","get message is " + s);
                                new RequestMaker<User>(activity, ServiceFactory.getProfileService().detail(msg.getSender())){
                                    @Override
                                    protected void onSuccess(final User user) {
                                        data.add(new Message(user.getData().getName(),user.getData().getAvatar(),s));
                                    }
                                };
                            }
                        }
                    });
            notifyDataSetChanged();
        }

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
