package com.project.qrsis.dal.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@Table(name = "lesson_calendar")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "lesson_id")
    Integer lessonId;

    @Column(name = "start_time")
    Timestamp startTime;

    @Column(name = "end_time")
    Timestamp endTime;
}
