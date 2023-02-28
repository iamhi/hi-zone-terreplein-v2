package com.github.iamhi.hizone.terreplein.v2.data.userpreference;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_preference_entity")
@Entity
public class UserPreferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "feedback_activated")
    Boolean feedbackActivated;

    @Column(name = "reminders_activated")
    Boolean remindersActivated;

    @Column(name = "cloudy_memory_activated")
    Boolean cloudyMemoryActivated;
}
