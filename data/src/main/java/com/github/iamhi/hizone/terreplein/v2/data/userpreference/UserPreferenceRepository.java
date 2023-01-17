package com.github.iamhi.hizone.terreplein.v2.data.userpreference;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserPreferenceRepository extends CrudRepository<UserPreferenceEntity, Integer> {

    List<UserPreferenceEntity> findByCreatedBy(String createdBy);
}
