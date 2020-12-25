package com.project.qrsis.dal.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "student_lesson")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "lesson_id")
    Integer lessonId;

    @Column(name = "student_id")
    Integer studentId;
}
