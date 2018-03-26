package repository;


import model.WorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
@Repository
public interface WorkRepository extends JpaRepository<WorkEntity, Integer> {
    List<WorkEntity> findByTypeId(int id);
    List<WorkEntity> findByUid(int id);
}