package com.vivi.artwork.model.json;

import com.vivi.artwork.model.json.base.BaseModel;

import java.util.List;

/**
 * Created by vivi on 2018/3/25.
 */

public class Work extends BaseModel{

    /**
     * msg : null
     * data : {"works":[{"workEntity":{"id":1,"uid":9,"typeId":1,"url":"http://a.hiphotos.baidu.com/zhidao/pic/item/adaf2edda3cc7cd9f595fcf03d01213fb80e915b.jpg"},"userEntity":{"id":9,"name":"vivi","avatar":"http://139.199.32.74/files/img/null","password":"qqqqqq","email":"932942491@qq.com","birth":"1970-01-18","sex":1,"qqSign":"eJxFkF1vgjAUhv8Lt1tcW1oYS0wGzhFU5ohItt2QDooeP6BgUeey-z5sdLt9n-PmnOd8G-Fk1uNSQp5ylZpNbjwYyLjVsThKaETKCyWaLsaMMYLQle5Fs4Oq7ABBmGFiIvQPIRelggJ00TGJQwl18GNd97Jqe5nZwaKD4XA*CKInP9rMlXV3cOKQg1cyryD5ajxuLTV1n6P2Jkn2vmLR4PjpBgsvGMVLeqKvp83Hu4vIqLaHNU-Cl2Q2iSCjy-WbL93DinDUvy7L16nWPIvQ7lDbvnfwBSrYCi1IsG1b5p8Fz7KqLVWqvqTQf-n5BXiZWYY_"}},{"workEntity":{"id":2,"uid":9,"typeId":1,"url":"http://a.hiphotos.baidu.com/zhidao/pic/item/adaf2edda3cc7cd9f595fcf03d01213fb80e915b.jpg"},"userEntity":{"id":9,"name":"vivi","avatar":"http://139.199.32.74/files/img/null","password":"qqqqqq","email":"932942491@qq.com","birth":"1970-01-18","sex":1,"qqSign":"eJxFkF1vgjAUhv8Lt1tcW1oYS0wGzhFU5ohItt2QDooeP6BgUeey-z5sdLt9n-PmnOd8G-Fk1uNSQp5ylZpNbjwYyLjVsThKaETKCyWaLsaMMYLQle5Fs4Oq7ABBmGFiIvQPIRelggJ00TGJQwl18GNd97Jqe5nZwaKD4XA*CKInP9rMlXV3cOKQg1cyryD5ajxuLTV1n6P2Jkn2vmLR4PjpBgsvGMVLeqKvp83Hu4vIqLaHNU-Cl2Q2iSCjy-WbL93DinDUvy7L16nWPIvQ7lDbvnfwBSrYCi1IsG1b5p8Fz7KqLVWqvqTQf-n5BXiZWYY_"}}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<WorksBean> works;

        public List<WorksBean> getWorks() {
            return works;
        }

        public void setWorks(List<WorksBean> works) {
            this.works = works;
        }

        public static class WorksBean {
            /**
             * workEntity : {"id":1,"uid":9,"typeId":1,"url":"http://a.hiphotos.baidu.com/zhidao/pic/item/adaf2edda3cc7cd9f595fcf03d01213fb80e915b.jpg"}
             * userEntity : {"id":9,"name":"vivi","avatar":"http://139.199.32.74/files/img/null","password":"qqqqqq","email":"932942491@qq.com","birth":"1970-01-18","sex":1,"qqSign":"eJxFkF1vgjAUhv8Lt1tcW1oYS0wGzhFU5ohItt2QDooeP6BgUeey-z5sdLt9n-PmnOd8G-Fk1uNSQp5ylZpNbjwYyLjVsThKaETKCyWaLsaMMYLQle5Fs4Oq7ABBmGFiIvQPIRelggJ00TGJQwl18GNd97Jqe5nZwaKD4XA*CKInP9rMlXV3cOKQg1cyryD5ajxuLTV1n6P2Jkn2vmLR4PjpBgsvGMVLeqKvp83Hu4vIqLaHNU-Cl2Q2iSCjy-WbL93DinDUvy7L16nWPIvQ7lDbvnfwBSrYCi1IsG1b5p8Fz7KqLVWqvqTQf-n5BXiZWYY_"}
             */

            private WorkEntityBean workEntity;
            private UserEntityBean userEntity;

            public WorkEntityBean getWorkEntity() {
                return workEntity;
            }

            public void setWorkEntity(WorkEntityBean workEntity) {
                this.workEntity = workEntity;
            }

            public UserEntityBean getUserEntity() {
                return userEntity;
            }

            public void setUserEntity(UserEntityBean userEntity) {
                this.userEntity = userEntity;
            }

            public static class WorkEntityBean {
                /**
                 * id : 1
                 * uid : 9
                 * typeId : 1
                 * url : http://a.hiphotos.baidu.com/zhidao/pic/item/adaf2edda3cc7cd9f595fcf03d01213fb80e915b.jpg
                 */

                private int id;
                private int uid;
                private int typeId;
                private String url;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }

                public int getTypeId() {
                    return typeId;
                }

                public void setTypeId(int typeId) {
                    this.typeId = typeId;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class UserEntityBean {
                /**
                 * id : 9
                 * name : vivi
                 * avatar : http://139.199.32.74/files/img/null
                 * password : qqqqqq
                 * email : 932942491@qq.com
                 * birth : 1970-01-18
                 * sex : 1
                 * qqSign : eJxFkF1vgjAUhv8Lt1tcW1oYS0wGzhFU5ohItt2QDooeP6BgUeey-z5sdLt9n-PmnOd8G-Fk1uNSQp5ylZpNbjwYyLjVsThKaETKCyWaLsaMMYLQle5Fs4Oq7ABBmGFiIvQPIRelggJ00TGJQwl18GNd97Jqe5nZwaKD4XA*CKInP9rMlXV3cOKQg1cyryD5ajxuLTV1n6P2Jkn2vmLR4PjpBgsvGMVLeqKvp83Hu4vIqLaHNU-Cl2Q2iSCjy-WbL93DinDUvy7L16nWPIvQ7lDbvnfwBSrYCi1IsG1b5p8Fz7KqLVWqvqTQf-n5BXiZWYY_
                 */

                private int id;
                private String name;
                private String avatar;
                private String password;
                private String email;
                private String birth;
                private int sex;
                private String qqSign;

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

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
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

                public String getQqSign() {
                    return qqSign;
                }

                public void setQqSign(String qqSign) {
                    this.qqSign = qqSign;
                }
            }
        }
    }
}
