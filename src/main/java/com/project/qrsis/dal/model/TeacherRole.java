package com.project.qrsis.dal.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "teacher_role")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "teacher_id")
    Integer teacherId;
    @Column(name = "role")
    String role;
}
