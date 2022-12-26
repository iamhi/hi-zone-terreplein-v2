package com.github.iamhi.hizone.terreplein.v2.domain.models.reminder;

import java.util.Arrays;

public enum ReminderCompleteType {
    INCOMPLETE("incomplete"),
    COMPLETED("completed"),
    WONT_DO("wontDo");

    public final String value;

    ReminderCompleteType(String value) {
        this.value = value;
    }

    public static ReminderCompleteType fromValue(String value) {
        return Arrays.stream(values())
            .filter(reminderType -> reminderType.value.equals(value))
            .findFirst()
            .orElse(null);
    }
}
