package com.github.iamhi.hizone.terreplein.v2.gateway.userpreference;

import com.github.iamhi.hizone.terreplein.v2.domain.UserPreferenceService;
import com.github.iamhi.hizone.terreplein.v2.domain.models.userpreference.UserPreference;
import com.github.iamhi.hizone.terreplein.v2.gateway.userpreference.requests.UserPreferenceRequest;
import com.github.iamhi.hizone.terreplein.v2.gateway.userpreference.responses.UserPreferenceResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.iamhi.hizone.terreplein.v2.config.SwaggerConfig.SECURITY_SCHEME_NAME;

@RestController
@RequestMapping("/userPreference")
@RequiredArgsConstructor
@SecurityRequirement(name = SECURITY_SCHEME_NAME)
class UserPreferenceController {

    final private UserPreferenceService userPreferenceService;

    @GetMapping
    @PreAuthorize("hasAuthority('basic')")
    UserPreferenceResponse getUserPreference() {
        return toResponse(userPreferenceService.getUserPreference());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('basic')")
    UserPreferenceResponse setUserPreference(@RequestBody UserPreferenceRequest request) {
        return toResponse(userPreferenceService.setUserPreference(toDto(request)));
    }

    private UserPreferenceResponse toResponse(UserPreference userPreference) {
        return new UserPreferenceResponse(
            userPreference.feedbackActivated(),
            userPreference.remindersActivated()
        );
    }

    private UserPreference toDto(UserPreferenceRequest request) {
        return new UserPreference(
            request.feedbackActivated(),
            request.remindersActivated()
        );
    }
}
