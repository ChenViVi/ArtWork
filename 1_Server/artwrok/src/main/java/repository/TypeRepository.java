package repository;


import model.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, Integer> {
    List<TypeEntity> findById(int id);
}