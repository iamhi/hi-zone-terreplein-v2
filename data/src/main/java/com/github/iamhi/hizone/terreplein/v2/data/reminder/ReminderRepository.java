package com.github.iamhi.hizone.terreplein.v2.data.reminder;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReminderRepository extends CrudRepository<ReminderEntity, Integer> {

    Optional<ReminderEntity> findByUuid(String uuid);

    List<ReminderEntity> findByCreatedBy(String createdBy);

    List<ReminderEntity> findByCreatedByOrderByStatusAscCreatedAtDesc(String createdBy);
}
