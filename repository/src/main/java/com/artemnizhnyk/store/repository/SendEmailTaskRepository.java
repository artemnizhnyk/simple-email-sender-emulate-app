package store.repository;

import store.entity.SendEmailTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SendEmailTaskRepository extends JpaRepository<SendEmailTaskEntity, Long> {

    List<SendEmailTaskEntity> findAllByProcessedAtIsNull();
}
