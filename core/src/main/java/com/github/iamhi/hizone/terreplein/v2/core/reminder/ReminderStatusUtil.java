package com.github.iamhi.hizone.terreplein.v2.core.reminder;

import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.Reminder;
import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.ReminderCompleteType;
import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.ReminderStatusType;

import java.time.Instant;

class ReminderStatusUtil {

    private ReminderStatusUtil() {}

    public static ReminderStatusType getStatus(Reminder reminder) {

        if (ReminderCompleteType.COMPLETED.equals(reminder.completeType())) {
            return ReminderStatusType.DONE;
        }

        switch (reminder.type()) {
            case COMPLETE_AT_ANY_TIME -> {
                return ReminderStatusType.ACTIVE;
            }

            case COMPLETE_BY_TIME -> {
                return Instant.now().isBefore(reminder.time()) ? ReminderStatusType.ACTIVE : ReminderStatusType.EXPIRED;
            }

            case COMPLETE_AFTER_TIME -> {
                return Instant.now().isAfter(reminder.time()) ? ReminderStatusType.ACTIVE : ReminderStatusType.EXPIRED;
            }

            default -> {
                return ReminderStatusType.EXPIRED;
            }
        }
    }
}
