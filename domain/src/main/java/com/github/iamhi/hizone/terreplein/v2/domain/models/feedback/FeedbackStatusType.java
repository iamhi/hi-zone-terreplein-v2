package com.github.iamhi.hizone.terreplein.v2.domain.models.feedback;

public enum FeedbackStatusType {
    UNREAD("unread"),
    READ("read"),
    COMPLETED("completed");

    public final String value;

    FeedbackStatusType(String value) {
        this.value = value;
    }
}
