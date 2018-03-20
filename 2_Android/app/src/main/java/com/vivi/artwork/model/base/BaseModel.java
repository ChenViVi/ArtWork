package com.vivi.artwork.model.base;

import java.io.Serializable;

/**
 * Created by vivi on 2016/9/17.
 */
public class BaseModel implements Serializable {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String message) {
        this.msg = msg;
    }
}
