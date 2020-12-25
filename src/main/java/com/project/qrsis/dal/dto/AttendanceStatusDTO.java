package com.project.qrsis.dal.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttendanceStatusDTO {

    List<StudentDTO> attendingStudents = new ArrayList<>();

    List<StudentDTO> nonAttendingStudents = new ArrayList<>();
}
