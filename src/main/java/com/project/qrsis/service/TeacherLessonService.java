package com.project.qrsis.service;

import com.project.qrsis.dal.dto.TeacherDTO;
import com.project.qrsis.dal.model.Lesson;

import java.util.List;

public interface TeacherLessonService {

    List<TeacherDTO> getTeachersOfLesson(Integer lessonId);

    void removeTeacherFromLesson(Integer lessonId, Integer teacherId);

    void addTeacherToLesson(Integer teacherId, Integer lessonId);

    List<Lesson> getLessonsOfTeacher(Integer teacherId);
}
