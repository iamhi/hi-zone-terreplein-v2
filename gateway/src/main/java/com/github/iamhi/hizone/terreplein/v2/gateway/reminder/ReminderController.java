package com.github.iamhi.hizone.terreplein.v2.gateway.reminder;

import com.github.iamhi.hizone.terreplein.v2.domain.ReminderService;
import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.Reminder;
import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.ReminderCompleteType;
import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.ReminderType;
import com.github.iamhi.hizone.terreplein.v2.gateway.reminder.requests.CompleteReminderRequest;
import com.github.iamhi.hizone.terreplein.v2.gateway.reminder.requests.CreateReminderRequest;
import com.github.iamhi.hizone.terreplein.v2.gateway.reminder.requests.UpdateReminderRequest;
import com.github.iamhi.hizone.terreplein.v2.gateway.reminder.responses.CompleteReminderResponse;
import com.github.iamhi.hizone.terreplein.v2.gateway.reminder.responses.ReminderResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reminder")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authentication v2 access token")
public class ReminderController {

    private final ReminderService reminderService;

    @GetMapping
    @PreAuthorize("hasAuthority('basic')")
    List<ReminderResponse> findReminders() {
        return reminderService.get().stream().map(ReminderResponseMapper::fromDto).toList();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('basic')")
    ReminderResponse addReminder(@RequestBody CreateReminderRequest createReminderRequest) {
        return ReminderResponseMapper.fromDto(reminderService.create(
            createReminderRequest.content(),
            ReminderType.fromValue(createReminderRequest.type()),
            createReminderRequest.time(),
            createReminderRequest.location()
        ));
    }

    @PutMapping("/{uuid}")
    @PreAuthorize("hasAuthority('basic')")
    ReminderResponse updateReminder(@PathVariable String uuid, @RequestBody UpdateReminderRequest updateReminderRequest) {
        return ReminderResponseMapper.fromDto(reminderService.update(new Reminder(
            uuid,
            null,
            updateReminderRequest.content(),
            updateReminderRequest.time(),
            updateReminderRequest.location(),
            ReminderType.fromValue(updateReminderRequest.type()),
            null,
            null,
            null,
            null,
            null,
            null
        )));
    }

    @PutMapping("/{uuid}/complete")
    @PreAuthorize("hasAuthority('basic')")
    CompleteReminderResponse completeReminder(@PathVariable String uuid, @RequestBody CompleteReminderRequest completeReminderRequest) {
        Reminder completedReminder = reminderService.complete(uuid,
            ReminderCompleteType.fromValue(completeReminderRequest.completeType()),
            completeReminderRequest.comment()
        );

        return new CompleteReminderResponse(
            completedReminder.uuid(),
            completedReminder.content(),
            completedReminder.completeType().value,
            completedReminder.completeComment(),
            completedReminder.completedAt()
        );
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasAuthority('basic')")
    ReminderResponse deleteReminder(@PathVariable String uuid) {
        return ReminderResponseMapper.fromDto(reminderService.delete(uuid));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin')")
    List<ReminderResponse> findAllReminders() {
        return reminderService.getAll().stream().map(ReminderResponseMapper::fromDto).toList();
    }
}
