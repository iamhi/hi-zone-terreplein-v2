package com.github.iamhi.hizone.terreplein.v2.gateway.reminder.requests;

import java.time.Instant;

public record CreateReminderRequest(
    String content,
    String type,
    Instant time,
    String location
) {
}
