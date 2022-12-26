package com.github.iamhi.hizone.terreplein.v2.gateway.feedback;

import com.github.iamhi.hizone.terreplein.v2.domain.FeedbackService;
import com.github.iamhi.hizone.terreplein.v2.domain.UserContextService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authentication v2 access token")
public class FeedbackController{

    private final FeedbackService feedbackService;

    private final UserContextService userContextService;

    @PostMapping
    public FeedbackResponse leaveFeedback(@RequestBody FeedbackCreateRequest request) {
        return FeedbackResponseMapper.fromDto(feedbackService.leaveFeedback(request.content(),
            StringUtils.defaultString(
                request.creator(),
                userContextService.getUsername())
        ));
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/all")
    public List<FeedbackResponse> fetchAllFeedback() {
        return FeedbackResponseMapper.fromDto(feedbackService.getAllFeedback());
    }

    // TODO: Mark feedback

    // TODO: Complete feedback

    // TODO: delete feedback
}
