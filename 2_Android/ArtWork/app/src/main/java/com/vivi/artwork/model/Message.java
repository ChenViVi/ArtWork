package com.vivi.artwork.model;

/**
 * Created by vivi on 2018/3/24.
 */

public class Message {
    private String email;
    private String name;
    private String content;
    private String avatar;

    public Message(String email,String name, String avatar , String content){
        this.email = email;
        this.name = name;
        this.avatar = avatar;
        this.content = content;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getEmail() {
        return email;
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
