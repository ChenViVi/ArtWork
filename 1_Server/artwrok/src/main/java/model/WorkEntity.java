package model;

import javax.persistence.*;

/**
 * Created by vivi on 2018/3/20.
 */
@Entity
@Table(name = "work", schema = "artwork", catalog = "")
public class WorkEntity {
    private long id;
    private long uid;
    private String url;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uid")
    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
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
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (uid ^ (uid >>> 32));
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
