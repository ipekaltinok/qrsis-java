package com.project.qrsis.service;

import com.project.qrsis.dal.dto.ErrorMessage;
import com.project.qrsis.dal.dto.LessonCalendarInDTO;
import com.project.qrsis.dal.model.LessonCalendar;
import com.project.qrsis.dal.repository.LessonCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;

@Service
public class LessonCalendarServiceImpl implements LessonCalendarService {

    private final LessonCalendarRepository lessonCalendarRepository;

    @Autowired
    public LessonCalendarServiceImpl(LessonCalendarRepository lessonCalendarRepository) {
        this.lessonCalendarRepository = lessonCalendarRepository;
    }

    public List<LessonCalendar> getLessonCalendar(Integer lessonId) {
        return lessonCalendarRepository.findAllByLessonId(lessonId);
    }

    public List<LessonCalendar> getLessonCalendarUntilNow(Integer lessonId, Timestamp now) {

        List<LessonCalendar> lessonCalendarList =
                lessonCalendarRepository.findAllByLessonIdAndStartTimeLessThan(lessonId, now);

        lessonCalendarList.sort(Comparator.comparing(LessonCalendar::getStartTime));

        return lessonCalendarList;
    }

    public ErrorMessage createLessonCalendar(LessonCalendarInDTO dto) {

        Integer lessonId = dto.getLessonId();
        Timestamp startTime = dto.getStartTime();
        Timestamp endTime = dto.getEndTime();

        ErrorMessage errorMessage = new ErrorMessage("Bu ders için bu ders saatleri eklenemez!");

        LessonCalendar lessonCalendar = lessonCalendarRepository.findLessonCalendar(lessonId, startTime);
        if (lessonCalendar != null)
            return errorMessage;

        lessonCalendar = lessonCalendarRepository.findLessonCalendar(lessonId, endTime);
        if (lessonCalendar != null)
            return errorMessage;

        lessonCalendar = new LessonCalendar();
        lessonCalendar.setLessonId(lessonId);
        lessonCalendar.setStartTime(startTime);
        lessonCalendar.setEndTime(endTime);

        lessonCalendarRepository.save(lessonCalendar);

        return null;
    }

    public void deleteLessonCalendar(Integer id) {

        LessonCalendar lessonCalendar = lessonCalendarRepository.findById(id).get();

        lessonCalendarRepository.delete(lessonCalendar);
    }

    public LessonCalendar getLessonCalendar(Integer lessonId, Timestamp dateTime) {
        return lessonCalendarRepository.findLessonCalendar(lessonId, dateTime);
    }
}
