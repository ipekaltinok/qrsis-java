package com.project.qrsis.dal.repository;

import com.project.qrsis.dal.model.LessonCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface LessonCalendarRepository extends JpaRepository<LessonCalendar, Integer>  {

    List<LessonCalendar> findAllByLessonId(Integer lessonId);

    List<LessonCalendar> findAllByLessonIdAndStartTimeLessThan(Integer lessonId, Timestamp now);

    @Query("SELECT lc FROM LessonCalendar lc WHERE lc.lessonId = ?1 AND lc.startTime <= ?2 AND lc.endTime >= ?2 ")
    LessonCalendar findLessonCalendar(Integer lessonId, Timestamp dateTime);

}
