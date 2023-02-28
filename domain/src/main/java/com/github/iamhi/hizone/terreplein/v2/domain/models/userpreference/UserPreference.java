package com.github.iamhi.hizone.terreplein.v2.domain.models.userpreference;

public record UserPreference(
    Boolean feedbackActivated,
    Boolean remindersActivated,
    Boolean cloudyMemoryActivated
) {
}
