package com.project.qrsis.dal.repository;

import com.project.qrsis.dal.model.TeacherLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface TeacherLessonRepository extends JpaRepository<TeacherLesson, Integer> {

    List<TeacherLesson> findAllByTeacherId(Integer teacherId);

    @Query("SELECT tl.teacherId FROM TeacherLesson tl WHERE tl.lessonId = ?1")
    Set<Integer> findTeachersOfLesson(Integer lessonId);

    @Query("SELECT tl.lessonId FROM TeacherLesson tl WHERE tl.teacherId = ?1")
    Set<Integer> findLessonsOfTeacher(Integer teacherId);

    TeacherLesson findFirstByLessonIdAndTeacherId(Integer lessonId, Integer teacherId);
}
