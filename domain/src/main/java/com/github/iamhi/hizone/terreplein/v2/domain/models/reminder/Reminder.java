package com.github.iamhi.hizone.terreplein.v2.domain.models.reminder;

import java.time.Instant;

public record Reminder(
    String uuid,
    String createdBy,
    String content,
    Instant time,
    String location,
    ReminderType type,
    ReminderStatusType status,
    ReminderCompleteType completeType,
    String completeComment,
    Instant completedAt,
    Instant createdAt,
    Instant updatedAt
) {

    public Reminder withStatus(ReminderStatusType newReminderStatusType) {
        return new Reminder(
            uuid,
            createdBy,
            content,
            time,
            location,
            type,
            newReminderStatusType,
            completeType,
            completeComment,
            completedAt,
            createdAt,
            updatedAt
        );
    }
}
