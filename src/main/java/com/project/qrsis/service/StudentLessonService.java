package com.project.qrsis.service;

import com.project.qrsis.dal.dto.StudentDTO;
import com.project.qrsis.dal.model.Lesson;
import com.project.qrsis.dal.model.StudentLesson;

import java.util.List;

public interface StudentLessonService {

    List<StudentDTO> getStudentsOfLesson(Integer lessonId);

    void addStudentToLesson(Integer lessonId, Integer studentId);

    void removeStudentFromLesson(Integer studentId, Integer lessonId);

    List<Lesson> getLessonsOfStudent(Integer studentId);
}
