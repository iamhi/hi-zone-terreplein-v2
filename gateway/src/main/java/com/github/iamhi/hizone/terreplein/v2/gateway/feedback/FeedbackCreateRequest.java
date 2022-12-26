package com.github.iamhi.hizone.terreplein.v2.gateway.feedback;

public record FeedbackCreateRequest(
    String content,
    String creator
) {
}
