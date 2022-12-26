package com.github.iamhi.hizone.terreplein.v2.data.reminder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reminder_entity")
@Entity
public class ReminderEntity implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "content")
    String content;

    @Column(name = "time")
    Instant time;

    @Column(name = "location")
    String location;

    @Column(name = "type")
    String type;

    @Column(name = "status")
    String status;

    @Column(name = "complete_type")
    String completeType;

    @Column(name = "complete_comment")
    String completeComment;

    @Column(name = "completed_at")
    Instant completedAt;

    @Column(name = "created_at")
    Instant createdAt;

    @Column(name = "updated_at")
    Instant updatedAt;
}
