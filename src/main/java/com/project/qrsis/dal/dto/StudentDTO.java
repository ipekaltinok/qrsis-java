package com.project.qrsis.dal.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor()
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentDTO {

    Integer id;

    String studentNumber;

    String name;

    String email;
}
