package com.project.qrsis.service;

import com.project.qrsis.dal.dto.StudentAttendanceDTO;
import com.project.qrsis.dal.dto.StudentAttendanceInDTO;
import com.project.qrsis.dal.model.StudentAttendance;

import java.util.List;
import java.util.Set;

public interface StudentAttendanceService {

    void createStudentAttendance(Integer studentId, Integer lessonCalendarId);

    Set<Integer> getStudentsAttendance(Integer lessonCalendarId);

    void addAttendanceToLessonForStudent(StudentAttendanceInDTO dto);

    void removeAttendanceFromLessonForStudent(Integer lessonId, Integer studentId);

    Integer getStudentAttendingCount(Integer studentId, Set<Integer> lessonCalendarIdList);

    StudentAttendanceDTO getStudentAttendances(Integer studentId, Integer lessonId);

    StudentAttendance getStudentAttendance(Integer studentId, Integer lessonCalendarId);
}
