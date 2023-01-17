package com.github.iamhi.hizone.terreplein.v2.domain;

import com.github.iamhi.hizone.terreplein.v2.domain.models.userpreference.UserPreference;

public interface UserPreferenceService {

    UserPreference getUserPreference();

    UserPreference setUserPreference(UserPreference userPreference);
}
