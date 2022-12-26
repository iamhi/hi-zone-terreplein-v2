package com.github.iamhi.hizone.terreplein.v2.core.feedback;

import com.github.iamhi.hizone.terreplein.v2.data.feedback.FeedbackEntity;
import com.github.iamhi.hizone.terreplein.v2.domain.models.feedback.Feedback;
import com.github.iamhi.hizone.terreplein.v2.domain.models.feedback.FeedbackStatusType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FeedbackMapper {

    private FeedbackMapper() {

    }

    public static Feedback fromEntity(FeedbackEntity entity) {
        return new Feedback(
            entity.getUuid(),
            entity.getContent(),
            entity.getCreator(),
            entity.isMarked(),
            FeedbackStatusType.valueOf(entity.getStatus()),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }

    public static List<Feedback> fromIterator(Iterable<FeedbackEntity> iterable) {
        Iterator<FeedbackEntity> iterator = iterable.iterator();
        List<Feedback> feedbackList = new ArrayList<>();

        while(iterator.hasNext()) {
            feedbackList.add(fromEntity(iterator.next()));
        }

        return feedbackList;
    }
}
