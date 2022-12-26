package com.github.iamhi.hizone.terreplein.v2.domain;

import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.Reminder;
import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.ReminderCompleteType;
import com.github.iamhi.hizone.terreplein.v2.domain.models.reminder.ReminderType;

import java.time.Instant;
import java.util.List;

public interface ReminderService {

    Reminder create(String content, ReminderType type, Instant time, String location);

    Reminder update(Reminder reminder);

    List<Reminder> getAll();

    List<Reminder> get();

    Reminder complete(String uuid, ReminderCompleteType completeType, String completeComment);

    Reminder delete(String uuid);
}
