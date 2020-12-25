package com.project.qrsis.dal.repository;

import com.project.qrsis.dal.model.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Integer> {

    @Query("SELECT sa.studentId FROM StudentAttendance sa WHERE sa.lessonCalendarId = ?1")
    Set<Integer> findAllByLessonCalendarId(Integer lessonCalendarId);

    StudentAttendance findFirstByStudentIdAndLessonCalendarId(Integer studentId, Integer lessonIdCalendarId);

    @Query("SELECT count(sa) FROM StudentAttendance sa WHERE sa.studentId = ?1 AND sa.lessonCalendarId IN ?2")
    Integer getAttendingCount(Integer studentId, Set<Integer> lessonCalendarIdList);

    @Query("SELECT sa.lessonCalendarId FROM StudentAttendance sa WHERE sa.studentId = ?1 AND sa.lessonCalendarId IN ?2")
    Set<Integer> getAttendedLessonCalendarIds(Integer studentId, Set<Integer> lessonCalendarIdList);

}
