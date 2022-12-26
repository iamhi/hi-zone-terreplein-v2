package com.github.iamhi.hizone.terreplein.v2.gateway.reminder;

import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.Reminder;
import com.github.iamhi.hizone.terreplein.v2.gateway.reminder.responses.ReminderResponse;

class ReminderResponseMapper {

    private ReminderResponseMapper() {
    }

    static ReminderResponse fromDto(Reminder dto) {
        return new ReminderResponse(
            dto.uuid(),
            dto.createdBy(),
            dto.content(),
            dto.time(),
            dto.location(),
            dto.type().value,
            dto.status().value,
            dto.completeType().value,
            dto.completeComment(),
            dto.completedAt(),
            dto.createdAt(),
            dto.updatedAt()
        );
    }
}
