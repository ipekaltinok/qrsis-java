package com.project.qrsis.dal.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "student_attendance")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "lesson_calendar_id")
    Integer lessonCalendarId;

    @Column(name = "student_id")
    Integer studentId;
}
