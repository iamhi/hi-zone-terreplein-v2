package com.github.iamhi.hizone.terreplein.v2.domain.models.reminder;

import java.util.Arrays;

public enum ReminderStatusType {
    ACTIVE("active"),
    EXPIRED("expired"),
    DONE("done");

    public final String value;

    ReminderStatusType(String value) {
        this.value = value;
    }

    public static ReminderStatusType fromValue(String value) {
        return Arrays.stream(values())
            .filter(reminderType -> reminderType.value.equals(value))
            .findFirst()
            .orElse(null);
    }
}
