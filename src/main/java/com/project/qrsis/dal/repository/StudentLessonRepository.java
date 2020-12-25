package com.project.qrsis.dal.repository;

import com.project.qrsis.dal.model.StudentLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface StudentLessonRepository extends JpaRepository<StudentLesson, Integer> {

    List<StudentLesson> findAllByLessonId(Integer lessonId);

    StudentLesson findFirstByStudentIdAndLessonId(Integer studentId, Integer lessonId);

    @Query("SELECT sl.studentId FROM StudentLesson sl WHERE sl.lessonId = ?1")
    Set<Integer> findStudentsOfLesson(Integer lessonId);

    @Query("SELECT sl.lessonId FROM StudentLesson sl WHERE sl.studentId = ?1")
    Set<Integer> findLessonsOfStudent(Integer studentId);
}
