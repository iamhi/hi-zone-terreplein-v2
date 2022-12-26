package com.github.iamhi.hizone.terreplein.v2.domain.models.feedback;

import java.time.Instant;

public record Feedback(
    String uuid,
    String content,
    String creator,
    boolean marked,
    FeedbackStatusType status,
    Instant createdAt,
    Instant updatedAt
) {
}
