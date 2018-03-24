package model;

import javax.persistence.*;

/**
 * Created by vivi on 2018/3/24.
 */
@Entity
@Table(name = "msg", schema = "artwork", catalog = "")
public class MsgEntity {
    private int code;
    private String msg;

    @Id
    @Column(name = "code")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Basic
    @Column(name = "msg")
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MsgEntity msgEntity = (MsgEntity) o;

        if (code != msgEntity.code) return false;
        if (msg != null ? !msg.equals(msgEntity.msg) : msgEntity.msg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        return result;
    }
}
