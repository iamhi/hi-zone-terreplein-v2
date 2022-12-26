package com.github.iamhi.hizone.terreplein.v2.domain.models.reminder;

import java.util.Arrays;

public enum ReminderType {
    COMPLETE_BY_TIME("createByTime"),
    COMPLETE_AT_TIME("createAtTime"),
    COMPLETE_AFTER_TIME("createAfterTime"),
    COMPLETE_AT_ANY_TIME("createAtAnyTime");

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
