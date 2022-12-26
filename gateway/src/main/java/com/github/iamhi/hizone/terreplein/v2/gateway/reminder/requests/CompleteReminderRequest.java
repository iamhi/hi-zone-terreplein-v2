package com.github.iamhi.hizone.terreplein.v2.gateway.reminder.requests;

public record CompleteReminderRequest(
    String uuid,
    String completeType,
    String comment
) {
}
