package com.github.iamhi.hizone.terreplein.v2.gateway.feedback;

import java.time.Instant;

public record FeedbackResponse(
    String uuid,
    String content,
    String creator,
    boolean marked,
    String status,
    Instant createdAt,
    Instant updatedAt
) {
}
