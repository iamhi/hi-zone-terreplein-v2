package com.github.iamhi.hizone.terreplein.v2.gateway.feedback;

import com.github.iamhi.hizone.terreplein.v2.domain.models.feedback.Feedback;

import java.util.List;

class FeedbackResponseMapper {

    private FeedbackResponseMapper() {
    }

    static FeedbackResponse fromDto(Feedback dto) {
        return new FeedbackResponse(
            dto.uuid(),
            dto.content(),
            dto.creator(),
            dto.marked(),
            dto.status().value,
            dto.createdAt(),
            dto.updatedAt()
        );
    }

    static List<FeedbackResponse> fromDto(List<Feedback> dto) {
        return dto.stream().map(FeedbackResponseMapper::fromDto).toList();
    }
}
