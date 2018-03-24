package repository;

import model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    List<UserEntity> findById(int id);

    List<UserEntity> findByEmail(String email);

    List<UserEntity> findByName(String email);
}