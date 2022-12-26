package com.github.iamhi.hizone.terreplein.v2.gateway.reminder.responses;

import java.time.Instant;

public record ReminderResponse(
    String uuid,
    String createdBy,
    String content,
    Instant time,
    String location,
    String type,
    String status,
    String completeType,
    String completeComment,
    Instant completedAt,
    Instant createdAt,
    Instant updatedAt
) {
}
