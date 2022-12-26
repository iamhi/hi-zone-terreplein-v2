package com.github.iamhi.hizone.terreplein.v2.domain;

import com.github.iamhi.hizone.terreplein.v2.domain.models.feedback.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback leaveFeedback(String feedback, String creator);

    List<Feedback> getAllFeedback();
}
