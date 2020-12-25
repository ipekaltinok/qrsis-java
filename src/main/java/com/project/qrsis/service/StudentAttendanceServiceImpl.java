package com.project.qrsis.service;

import com.project.qrsis.dal.dto.StudentAttendanceDTO;
import com.project.qrsis.dal.dto.StudentAttendanceDetailDTO;
import com.project.qrsis.dal.dto.StudentAttendanceInDTO;
import com.project.qrsis.dal.model.LessonCalendar;
import com.project.qrsis.dal.model.StudentAttendance;
import com.project.qrsis.dal.repository.StudentAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService {

    private final LessonCalendarService lessonCalendarService;
    private final StudentLessonService studentLessonService;

    private final StudentAttendanceRepository studentAttendanceRepository;

    @Autowired
    public StudentAttendanceServiceImpl(LessonCalendarService lessonCalendarService,
                                        StudentLessonService studentLessonService,
                                        StudentAttendanceRepository studentAttendanceRepository) {

        this.lessonCalendarService = lessonCalendarService;
        this.studentLessonService = studentLessonService;

        this.studentAttendanceRepository = studentAttendanceRepository;
    }

    public void createStudentAttendance(Integer studentId, Integer lessonCalendarId) {

        StudentAttendance studentAttendance = new StudentAttendance();
        studentAttendance.setLessonCalendarId(lessonCalendarId);
        studentAttendance.setStudentId(studentId);

        studentAttendanceRepository.save(studentAttendance);
    }
    
    public Set<Integer> getStudentsAttendance(Integer lessonCalendarId) {
        return studentAttendanceRepository.findAllByLessonCalendarId(lessonCalendarId);
    }

    public void addAttendanceToLessonForStudent(StudentAttendanceInDTO dto) {

        Timestamp now = new Timestamp(System.currentTimeMillis());
        LessonCalendar lessonCalendar = lessonCalendarService.getLessonCalendar(dto.getLessonId(), now);

        createStudentAttendance(dto.getStudentId(), lessonCalendar.getId());
    }

    public void removeAttendanceFromLessonForStudent(Integer lessonId, Integer studentId) {

        Timestamp now = new Timestamp(System.currentTimeMillis());
        LessonCalendar lessonCalendar = lessonCalendarService.getLessonCalendar(lessonId, now);

        StudentAttendance studentAttendance =
                studentAttendanceRepository.findFirstByStudentIdAndLessonCalendarId(studentId, lessonCalendar.getId());

        studentAttendanceRepository.delete(studentAttendance);
    }

    public Integer getStudentAttendingCount(Integer studentId, Set<Integer> lessonCalendarIdList) {
        return studentAttendanceRepository.getAttendingCount(studentId, lessonCalendarIdList);
    }

    public StudentAttendanceDTO getStudentAttendances(Integer studentId, Integer lessonId) {

        Set<Integer> lessonCalendarIdSet = new HashSet<>();

        Timestamp now = new Timestamp(System.currentTimeMillis());

        List<LessonCalendar> lessonCalendarList = lessonCalendarService.getLessonCalendarUntilNow(lessonId, now);
        for (LessonCalendar lessonCalendar : lessonCalendarList)
            lessonCalendarIdSet.add(lessonCalendar.getId());

        Set<Integer> attendedLessonCalendarIds =
                studentAttendanceRepository.getAttendedLessonCalendarIds(studentId, lessonCalendarIdSet);

        StudentAttendanceDTO studentAttendanceDTO = new StudentAttendanceDTO();
        studentAttendanceDTO.setRatio(
                (double) attendedLessonCalendarIds.size() / lessonCalendarIdSet.size() * 100
        );

        for (LessonCalendar lessonCalendar : lessonCalendarList) {
            StudentAttendanceDetailDTO detailDTO = new StudentAttendanceDetailDTO();
            detailDTO.setStartTime(lessonCalendar.getStartTime());
            detailDTO.setEndTime(lessonCalendar.getEndTime());
            detailDTO.setIsAttended(attendedLessonCalendarIds.contains(lessonCalendar.getId()));

            studentAttendanceDTO.getDetails().add(detailDTO);
        }

        return studentAttendanceDTO;
    }

    public StudentAttendance getStudentAttendance(Integer studentId, Integer lessonCalendarId) {
        return studentAttendanceRepository.findFirstByStudentIdAndLessonCalendarId(studentId, lessonCalendarId);
    }
}
