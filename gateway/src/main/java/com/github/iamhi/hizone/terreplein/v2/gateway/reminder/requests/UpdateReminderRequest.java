package com.github.iamhi.hizone.terreplein.v2.gateway.reminder.requests;

import java.time.Instant;

public record UpdateReminderRequest(
    String content,
    Instant time,
    String location,
    String type
) {
}
