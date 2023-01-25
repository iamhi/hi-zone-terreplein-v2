package com.github.iamhi.hizone.terreplein.v2.domain.models.reminder;

import java.util.Arrays;

public enum ReminderType {
    COMPLETE_BY_TIME("completeByTime"),
    COMPLETE_AT_TIME("completeAtTime"),
    COMPLETE_AFTER_TIME("completeAfterTime"),
    COMPLETE_AT_ANY_TIME("completeAtAnyTime");

    public final String value;

    ReminderType(String value) {
        this.value = value;
    }

    public static ReminderType fromValue(String value) {
        return Arrays.stream(values())
            .filter(reminderType -> reminderType.value.equals(value))
            .findFirst()
            .orElse(null);
    }
}
