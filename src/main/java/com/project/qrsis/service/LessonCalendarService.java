package com.project.qrsis.service;

import com.project.qrsis.dal.dto.ErrorMessage;
import com.project.qrsis.dal.dto.LessonCalendarInDTO;
import com.project.qrsis.dal.model.LessonCalendar;

import java.sql.Timestamp;
import java.util.List;

public interface LessonCalendarService {

    ErrorMessage createLessonCalendar(LessonCalendarInDTO dto);

    List<LessonCalendar> getLessonCalendar(Integer lessonId);

    void deleteLessonCalendar(Integer id);

    LessonCalendar getLessonCalendar(Integer lessonId, Timestamp dateTime);

    List<LessonCalendar> getLessonCalendarUntilNow(Integer lessonId, Timestamp now);
}
