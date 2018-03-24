package com.vivi.artwork.model;

/**
 * Created by vivi on 2018/3/24.
 */

public class Message {
    private String name;
    private String content;
    private String avatar;

    public Message(String name, String avatar , String content){
        this.name = name;
        this.avatar = avatar;
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getContent() {
        return content;
    }
}
