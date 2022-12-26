package com.github.iamhi.hizone.terreplein.v2.gateway.reminder.responses;

import java.time.Instant;

public record CompleteReminderResponse(
    String uuid,
    String content,
    String completeType,
    String completeComment,
    Instant completedAt
) {
}
