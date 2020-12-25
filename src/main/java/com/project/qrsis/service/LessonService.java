package com.project.qrsis.service;

import com.project.qrsis.dal.dto.ErrorMessage;
import com.project.qrsis.dal.dto.LessonInDTO;
import com.project.qrsis.dal.model.Lesson;

import java.util.List;
import java.util.Set;

public interface LessonService {

    Lesson getLesson(Integer id);

    ErrorMessage createLesson(LessonInDTO dto);

    ErrorMessage updateLesson(Integer id, String name);

    void deleteLesson(Integer id);

    List<Lesson> getLessons(String name);

    List<Lesson> getLessons(Set<Integer> lessonIdList);
}
