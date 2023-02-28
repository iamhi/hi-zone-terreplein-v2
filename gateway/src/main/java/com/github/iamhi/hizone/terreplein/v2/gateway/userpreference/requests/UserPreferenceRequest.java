package com.github.iamhi.hizone.terreplein.v2.gateway.userpreference.requests;

public record UserPreferenceRequest(
    Boolean feedbackActivated,
    Boolean remindersActivated,
    Boolean cloudyMemoryActivated
) {
}
