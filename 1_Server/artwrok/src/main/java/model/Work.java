package model;

/**
 * Created by vivi on 2018/3/24.
 */
public class Work {
    private WorkEntity workEntity;
    private UserEntity userEntity;

    public Work(WorkEntity workEntity, UserEntity userEntity){
        this.workEntity = workEntity;
        this.userEntity = userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public void setWorkEntity(WorkEntity workEntity) {
        this.workEntity = workEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public WorkEntity getWorkEntity() {
        return workEntity;
    }
}
