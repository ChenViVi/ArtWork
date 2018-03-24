package com.vivi.timsdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final String userSign1 = "eJxFkF1vgjAUhv8Lt1tcW1oYS0wGzhFU5ohItt2QDooeP6BgUeey-z5sdLt9n-PmnOd8G-Fk1uNSQp5ylZpNbjwYyLjVsThKaETKCyWaLsaMMYLQle5Fs4Oq7ABBmGFiIvQPIRelggJ00TGJQwl18GNd97Jqe5nZwaKD4XA*CKInP9rMlXV3cOKQg1cyryD5ajxuLTV1n6P2Jkn2vmLR4PjpBgsvGMVLeqKvp83Hu4vIqLaHNU-Cl2Q2iSCjy-WbL93DinDUvy7L16nWPIvQ7lDbvnfwBSrYCi1IsG1b5p8Fz7KqLVWqvqTQf-n5BXiZWYY_";
    public static final String userSign2 = "eJxFkF1vgjAUhv8LtyzaFmrbJUsWCTrjtuiQLXpDgBZWWMuHxWjM-vuQYHb7PufNOc*5WrvXYBLXteRRbCKn5dajBayHIRbnWrYiijMj2j6GGGMEwJ2eRHuUle4BAhBD5ADwDyUX2shMDkXCEKYYU-TcNJO0UuPMUeY9fPNDb7X1oC58xR2W2N56rUoNdZgs38v5zxJ*XoJCTB1-1tjsQx22qzzch8nG3lP-m7MXsmHFIgsImp7ZKSjnXepRd4E6dfhy8-LpvoyX0aB5E3H7QwmhDI7QSCUGQQQpJA6ajXmcplWnTWQutRj*8vsHG2RYtw__";
    public static final String user1 = "932942491@qq.com";
    public static final String user2 = "792585582@qq.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*        TIMSdkConfig config = new TIMSdkConfig(1400077891);
        config.enableLogPrint(true)
                .setLogLevel(TIMLogLevel.values()[TIMLogLevel.ERROR.ordinal()]);*/
        /*tls_sigature.GenTLSSignatureResult result = null;
        try {
            result = GenTLSSignatureEx(1400077891, "932942491@qq.com", privStr);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        TIMManager.getInstance().init(this,  new TIMSdkConfig(1400077891));
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
                    }
                }
                return false;
            }
        });
    }

    public void onUser1(View view) {
        TIMManager.getInstance().logout(new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                //错误码code和错误描述desc，可用于定位请求失败原因
                //错误码code列表请参见错误码表
                Log.e("fuck", "user1 logout failed. code: " + code + " errmsg: " + desc);
                user1();
            }

            @Override
            public void onSuccess() {
                //登出成功
                Log.e("fuck", "user1 登出成功");
                user1();
            }
        });
    }

    public void onUser2(View view) {
        TIMManager.getInstance().logout(new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {

                //错误码code和错误描述desc，可用于定位请求失败原因
                //错误码code列表请参见错误码表
                Log.e("fuck", "user2 logout failed. code: " + code + " errmsg: " + desc);
                user2();
            }

            @Override
            public void onSuccess() {
                //登出成功
                Log.e("fuck", "user2 登出成功");
                user2();
            }
        });
    }


    private void user1(){
        TIMManager.getInstance().login(user1, userSign1, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                Log.e("fuck", "user1 login failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess() {
                Log.e("fuck", "user1 login succ");
                TIMConversation conversation = TIMManager.getInstance().getConversation(
                        TIMConversationType.C2C,    //会话类型：单聊
                        user2);
                TIMMessage msg = new TIMMessage();

//添加文本内容
                TIMTextElem elem = new TIMTextElem();
                elem.setText("a new msg from user1");
                if(msg.addElement(elem) != 0) {
                    Log.d("fuck", "user1 addElement failed");
                    return;
                }
                conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
                    @Override
                    public void onError(int code, String desc) {//发送消息失败
                        //错误码code和错误描述desc，可用于定位请求失败原因
                        //错误码code含义请参见错误码表
                        Log.e("fuck", "user1 send message failed. code: " + code + " errmsg: " + desc);
                    }

                    @Override
                    public void onSuccess(TIMMessage msg) {//发送消息成功
                        Log.e("fuck", "user1 SendMsg ok");
                    }
                });
            }
        });
    }

    private void user2(){
        TIMManager.getInstance().login(user2, userSign2, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                Log.e("fuck", "user2 login failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess() {
                Log.e("fuck", "user2 login succ");
                TIMConversation conversation = TIMManager.getInstance().getConversation(
                        TIMConversationType.C2C,    //会话类型：单聊
                        user1);
                TIMMessage msg = new TIMMessage();

//添加文本内容
                TIMTextElem elem = new TIMTextElem();
                elem.setText("a new msg from user2");
                if(msg.addElement(elem) != 0) {
                    Log.d("fuck", "user2 addElement failed");
                    return;
                }
                conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
                    @Override
                    public void onError(int code, String desc) {//发送消息失败
                        //错误码code和错误描述desc，可用于定位请求失败原因
                        //错误码code含义请参见错误码表
                        Log.e("fuck", "user2 send message failed. code: " + code + " errmsg: " + desc);
                    }

                    @Override
                    public void onSuccess(TIMMessage msg) {//发送消息成功
                        Log.e("fuck", "user2 SendMsg ok");
                    }
                });
            }
        });
    }
}
