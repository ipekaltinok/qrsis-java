package com.project.qrsis.dal.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@NoArgsConstructor()
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonCalendarInDTO {

    Integer lessonId;

    Timestamp startTime;

    Timestamp endTime;
}
