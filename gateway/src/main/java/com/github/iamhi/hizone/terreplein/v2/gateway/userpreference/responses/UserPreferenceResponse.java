package com.github.iamhi.hizone.terreplein.v2.gateway.userpreference.responses;

public record UserPreferenceResponse(
    Boolean feedbackActivated,
    Boolean remindersActivated,
    Boolean cloudyMemoryActivated
) {
}
