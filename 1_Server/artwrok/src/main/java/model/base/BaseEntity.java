package model.base;

public class BaseEntity {

    private int state = 0;
    private int code;
    private String msg;
    private Object data;


    public BaseEntity() {

    }

    public BaseEntity(int state, int code, String msg, Object data) {
        this.state = state;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
