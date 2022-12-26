package com.github.iamhi.hizone.terreplein.v2.core.feedback;

import com.github.iamhi.hizone.terreplein.v2.data.feedback.FeedbackEntity;
import com.github.iamhi.hizone.terreplein.v2.data.feedback.FeedbackRepository;
import com.github.iamhi.hizone.terreplein.v2.domain.FeedbackService;
import com.github.iamhi.hizone.terreplein.v2.domain.models.feedback.Feedback;
import com.github.iamhi.hizone.terreplein.v2.domain.models.feedback.FeedbackStatusType;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
record FeedbackServiceImpl(
    FeedbackRepository repository
) implements FeedbackService {
    @Override
    public Feedback leaveFeedback(String feedback, String creator) {
        FeedbackEntity feedbackEntity = new FeedbackEntity();

        feedbackEntity.setUuid(UUID.randomUUID().toString());
        feedbackEntity.setContent(feedback);
        feedbackEntity.setCreator(creator);
        feedbackEntity.setMarked(false);
        feedbackEntity.setStatus(FeedbackStatusType.UNREAD.name());
        feedbackEntity.setCreatedAt(Instant.now());
        feedbackEntity.setUpdatedAt(Instant.now());

        return FeedbackMapper.fromEntity(repository.save(feedbackEntity));
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return FeedbackMapper.fromIterator(repository.findAll());
    }
}
