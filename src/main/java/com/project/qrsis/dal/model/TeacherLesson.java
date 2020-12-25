package com.project.qrsis.dal.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "teacher_lesson")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "lesson_id")
    Integer lessonId;

    @Column(name = "teacher_id")
    Integer teacherId;
}
