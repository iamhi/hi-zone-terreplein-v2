package com.github.iamhi.hizone.terreplein.v2.core.reminder;

import com.github.iamhi.hizone.terreplein.v2.data.reminder.ReminderEntity;
import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.Reminder;
import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.ReminderCompleteType;
import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.ReminderStatusType;
import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.ReminderType;

class ReminderMapper {

    private ReminderMapper() {}

    public static Reminder map(ReminderEntity reminderEntity) {
        return new Reminder(
            reminderEntity.getUuid(),
            reminderEntity.getCreatedBy(),
            reminderEntity.getContent(),
            reminderEntity.getTime(),
            reminderEntity.getLocation(),
            ReminderType.valueOf(reminderEntity.getType()),
            ReminderStatusType.valueOf(reminderEntity.getStatus().toUpperCase()),
            ReminderCompleteType.valueOf(reminderEntity.getCompleteType()),
            reminderEntity.getCompleteComment(),
            reminderEntity.getCompletedAt(),
            reminderEntity.getCreatedAt(),
            reminderEntity.getUpdatedAt()
        );
    }
}
