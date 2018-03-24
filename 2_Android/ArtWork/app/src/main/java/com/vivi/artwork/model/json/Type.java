package com.vivi.artwork.model.json;

import com.vivi.artwork.model.json.base.BaseModel;

import java.util.List;

/**
 * Created by vivi on 2018/3/25.
 */

public class Type extends BaseModel{

    /**
     * code : 200
     * msg : null
     * data : [{"id":1,"name":"手绘"},{"id":2,"name":"风景"},{"id":3,"name":"街拍"},{"id":4,"name":"动漫"},{"id":5,"name":"简约"},{"id":6,"name":"自然"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 手绘
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
