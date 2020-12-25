package com.project.qrsis.dal.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "lesson")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;
}
