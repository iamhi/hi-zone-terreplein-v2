package com.github.iamhi.hizone.terreplein.v2.core.reminder;

import com.github.iamhi.hizone.terreplein.v2.core.reminder.exceptions.NotAllowedException;
import com.github.iamhi.hizone.terreplein.v2.data.reminder.ReminderEntity;
import com.github.iamhi.hizone.terreplein.v2.data.reminder.ReminderRepository;
import com.github.iamhi.hizone.terreplein.v2.domain.ReminderService;
import com.github.iamhi.hizone.terreplein.v2.domain.UserContextService;
import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.Reminder;
import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.ReminderCompleteType;
import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.ReminderStatusType;
import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.ReminderType;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.StreamSupport;

@Service
record ReminderServiceImpl(
    ReminderRepository repository,
    UserContextService userContextService
) implements ReminderService {

    @Override
    public Reminder create(String content, ReminderType type, Instant time, String location) {
        ReminderEntity reminder = new ReminderEntity();

        reminder.setUuid(UUID.randomUUID().toString());
        reminder.setCreatedBy(userContextService.getUsername());
        reminder.setContent(content);
        reminder.setTime(time);
        reminder.setLocation(location);
        reminder.setType(type.name());
        reminder.setStatus(ReminderStatusType.ACTIVE.name());
        reminder.setCompleteType(ReminderCompleteType.INCOMPLETE.name());
        reminder.setCreatedAt(Instant.now());
        reminder.setUpdatedAt(Instant.now());

        return ReminderMapper.map(repository.save(reminder));
    }

    @Override
    public Reminder update(Reminder reminder) {
        return repository.findByUuid(reminder.uuid())
            .map(this::currentUserHasAccess)
            .map(
                reminderEntity -> merge()
                    .andThen(setUpdatedAt())
                    .andThen(repository::save)
                    .andThen(ReminderMapper::map)
                    .apply(reminder, reminderEntity))
            .orElseThrow();
    }

    @Override
    public List<Reminder> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
            .map(ReminderMapper::map)
            .toList();
    }

    @Override
    public List<Reminder> get() {
        return repository.findByCreatedByOrderByStatusAscCreatedAtDesc(userContextService.getUsername()).stream()
            .map(ReminderMapper::map)
            .map(adjustStatus())
            .toList();
    }

    @Override
    public Reminder complete(String uuid, ReminderCompleteType completeType, String completeComment) {
        return repository.findByUuid(uuid)
            .map(this::currentUserHasAccess)
            .map(reminderEntity -> {
                reminderEntity.setStatus(ReminderStatusType.DONE.name());
                reminderEntity.setCompleteType(completeType.name());
                reminderEntity.setCompleteComment(completeComment);
                reminderEntity.setCompletedAt(Instant.now());

                return reminderEntity;
            })
            .map(setUpdatedAt())
            .map(repository::save)
            .map(ReminderMapper::map)
            .orElseThrow();
    }

    @Override
    public Reminder delete(String uuid) {
        return repository.findByUuid(uuid)
            .map(this::currentUserHasAccess)
            .map(reminderEntity -> {
                repository.delete(reminderEntity);

                return reminderEntity;
            }).map(ReminderMapper::map)
            .orElseThrow();
    }

    private ReminderEntity currentUserHasAccess(ReminderEntity entity) {
        if (!entity.getCreatedBy().equals(userContextService.getUsername())) {
            throw new NotAllowedException("Not allowed");
        }

        return entity;
    }

    private BiFunction<Reminder, ReminderEntity, ReminderEntity> merge() {
        return (reminder, reminderEntity) -> {
            ReminderEntity clone = SerializationUtils.clone(reminderEntity);

            clone.setContent(StringUtils.defaultString(StringUtils.defaultString(reminder.content(), reminderEntity.getContent())));
            clone.setTime(ObjectUtils.defaultIfNull(reminder.time(), reminderEntity.getTime()));
            clone.setType(StringUtils.defaultString(reminder.type().name(), reminderEntity.getType()));
            clone.setLocation(StringUtils.defaultString(reminder.location(), reminderEntity.getLocation()));
            clone.setStatus(ReminderStatusUtil.getStatus(ReminderMapper.map(clone)).name());

            return clone;
        };
    }

    private Function<ReminderEntity, ReminderEntity> setUpdatedAt() {
        return reminderEntity -> {
            reminderEntity.setUpdatedAt(Instant.now());

            return reminderEntity;
        };
    }

    private Function<Reminder, Reminder> adjustStatus() {
        return reminder -> {
            ReminderStatusType newStatus = ReminderStatusUtil.getStatus(reminder);

            if (!reminder.status().equals(newStatus)) {
                repository.findByUuid(reminder.uuid())
                    .ifPresent(reminderEntity -> {
                        reminderEntity.setStatus(newStatus.name());

                        repository.save(reminderEntity);
                    });

                return reminder.withStatus(newStatus);
            }

            return reminder;
        };
    }
}
