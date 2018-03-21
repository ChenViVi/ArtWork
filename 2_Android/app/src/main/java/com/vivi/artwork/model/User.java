package com.vivi.artwork.model;

import com.vivi.artwork.model.base.BaseModel;

import java.sql.Date;

/**
 * Created by vivi on 2018/3/20.
 */

public class User extends BaseModel{

    /**
     * state : 1
     * code : 200
     * msg : null
     * data : {"user":{"id":1,"name":"vivi","password":"qqqqqq","email":"932942491@qq.com","birth":"1970-01-18","sex":1,"avatar":"http://owtt2jsve.bkt.clouddn.com/def_avatar.png"}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user : {"id":1,"name":"vivi","password":"qqqqqq","email":"932942491@qq.com","birth":"1970-01-18","sex":1,"avatar":"http://owtt2jsve.bkt.clouddn.com/def_avatar.png"}
         */

        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * id : 1
             * name : vivi
             * password : qqqqqq
             * email : 932942491@qq.com
             * birth : 1970-01-18
             * sex : 1
             * avatar : http://owtt2jsve.bkt.clouddn.com/def_avatar.png
             */

            private int id;
            private String name;
            private String password;
            private String email;
            private String birth;
            private int sex;
            private String avatar;

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

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getBirth() {
                return birth;
            }

            public void setBirth(String birth) {
                this.birth = birth;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
    }
}
