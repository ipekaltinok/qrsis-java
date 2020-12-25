package com.project.qrsis.dal.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor()
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherDTO {

    Integer id;

    String name;

    String email;
}
