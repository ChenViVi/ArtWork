package model;

import javax.persistence.*;

/**
 * Created by vivi on 2018/3/24.
 */
@Entity
@Table(name = "work", schema = "artwork", catalog = "")
public class WorkEntity {
    private int id;
    private int uid;
    private int typeId;
    private String url;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "type_id")
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkEntity that = (WorkEntity) o;

        if (id != that.id) return false;
        if (uid != that.uid) return false;
        if (typeId != that.typeId) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + uid;
        result = 31 * result + typeId;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
