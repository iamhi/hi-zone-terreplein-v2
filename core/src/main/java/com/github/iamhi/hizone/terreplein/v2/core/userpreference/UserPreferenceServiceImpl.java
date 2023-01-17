package com.github.iamhi.hizone.terreplein.v2.core.userpreference;

import com.github.iamhi.hizone.terreplein.v2.data.userpreference.UserPreferenceEntity;
import com.github.iamhi.hizone.terreplein.v2.data.userpreference.UserPreferenceRepository;
import com.github.iamhi.hizone.terreplein.v2.domain.UserContextService;
import com.github.iamhi.hizone.terreplein.v2.domain.UserPreferenceService;
import com.github.iamhi.hizone.terreplein.v2.domain.models.userpreference.UserPreference;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
record UserPreferenceServiceImpl(
    UserPreferenceRepository repository,
    UserContextService userContextService
) implements UserPreferenceService {
    @Override
    public UserPreference getUserPreference() {
        return repository.findByCreatedBy(userContextService.getUuid()).stream().findFirst().map(this::toDto).orElseGet(() ->
           setUserPreference(new UserPreference(
               true,
               false
           ))
        );
    }

    @Override
    public UserPreference setUserPreference(UserPreference userPreference) {
        return toDto(repository.save(repository.findByCreatedBy(userContextService.getUuid())
            .stream().findFirst().map(entity -> {
                entity.setFeedbackActivated(userPreference.feedbackActivated());
                entity.setRemindersActivated(userPreference.remindersActivated());

                return entity;
            }).orElse(new UserPreferenceEntity(
                null,
                UUID.randomUUID().toString(),
                userContextService.getUuid(),
                userPreference.feedbackActivated(),
               userPreference.remindersActivated()
            ))));
    }

    private UserPreference toDto(UserPreferenceEntity entity) {
        return new UserPreference(
            entity.getFeedbackActivated(),
            entity.getRemindersActivated()
        );
    }
}
