package com.project.qrsis.service;

import com.project.qrsis.dal.dto.*;
import com.project.qrsis.dal.model.LessonCalendar;
import com.project.qrsis.dal.model.StudentAttendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.*;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final EncryptionService encryptionService;
    private final LessonCalendarService lessonCalendarService;
    private final MeService meService;
    private final QRCodeGeneratorService qrCodeGeneratorService;
    private final StudentAttendanceService studentAttendanceService;
    private final StudentLessonService studentLessonService;

    @Autowired
    public AttendanceServiceImpl(EncryptionService encryptionService,
                                 LessonCalendarService lessonCalendarService,
                                 MeService meService,
                                 QRCodeGeneratorService qrCodeGeneratorService,
                                 StudentAttendanceService studentAttendanceService,
                                 StudentLessonService studentLessonService) {

        this.encryptionService = encryptionService;
        this.lessonCalendarService = lessonCalendarService;
        this.meService = meService;
        this.qrCodeGeneratorService = qrCodeGeneratorService;
        this.studentAttendanceService = studentAttendanceService;
        this.studentLessonService = studentLessonService;
    }

    public BufferedImage startAttendance(Integer lessonId) {

        Timestamp now = new Timestamp(System.currentTimeMillis());
        LessonCalendar lessonCalendar = lessonCalendarService.getLessonCalendar(lessonId, now);

        String encryptedValue = encryptionService.encrypt(lessonCalendar.getId().toString());

        return qrCodeGeneratorService.generate(encryptedValue);
    }

    public void doAttendance(AttendanceInDTO dto, Authentication authentication) {

        UserDTO me = meService.me(authentication);

        String decrypt = encryptionService.decrypt(dto.getQrCode());
        int lessonCalendarId = Integer.parseInt(decrypt);

        StudentAttendance studentAttendance = studentAttendanceService.getStudentAttendance(me.getId(), lessonCalendarId);
        if (studentAttendance == null)
            studentAttendanceService.createStudentAttendance(me.getId(), lessonCalendarId);
    }

    public Object attendanceStatus(Integer lessonId) {

        Timestamp now = new Timestamp(System.currentTimeMillis());
        LessonCalendar lessonCalendar = lessonCalendarService.getLessonCalendar(lessonId, now);
        if (lessonCalendar == null)
            return new ErrorMessage("Bu ders için bu saatte yoklama yapılamaz");

        Set<Integer> attendingStudents =
                studentAttendanceService.getStudentsAttendance(lessonCalendar.getId());

        AttendanceStatusDTO attendanceStatusDTO = new AttendanceStatusDTO();

        List<StudentDTO> studentsOfLesson = studentLessonService.getStudentsOfLesson(lessonId);
        for (StudentDTO studentDTO : studentsOfLesson)
            if (attendingStudents.contains(studentDTO.getId()))
                attendanceStatusDTO.getAttendingStudents().add(studentDTO);
            else
                attendanceStatusDTO.getNonAttendingStudents().add(studentDTO);

        return attendanceStatusDTO;
    }

    public List<AttendingRatioDTO> getAttendingRatioOfLesson(Integer lessonId) {

        Timestamp now = new Timestamp(System.currentTimeMillis());

        List<LessonCalendar> lessonCalendar = lessonCalendarService.getLessonCalendarUntilNow(lessonId, now);
        if (lessonCalendar.isEmpty())
            return new ArrayList<>();

        Set<Integer> lessonCalendarIdList = new HashSet<>();
        for (LessonCalendar calendar : lessonCalendar)
            lessonCalendarIdList.add(calendar.getId());

        int totalLessonTime = lessonCalendar.size();

        List<AttendingRatioDTO> attendingRatioDTOList = new ArrayList<>();

        List<StudentDTO> studentsOfLesson = studentLessonService.getStudentsOfLesson(lessonId);
        for (StudentDTO student : studentsOfLesson) {

            Integer attendingCount =
                    studentAttendanceService.getStudentAttendingCount(student.getId(), lessonCalendarIdList);

            AttendingRatioDTO attendingRatioDTO = new AttendingRatioDTO();
            attendingRatioDTO.setId(student.getId());
            attendingRatioDTO.setName(student.getName());
            attendingRatioDTO.setStudentNumber(student.getStudentNumber());
            attendingRatioDTO.setRatio((double) attendingCount / totalLessonTime * 100);

            attendingRatioDTOList.add(attendingRatioDTO);
        }

        attendingRatioDTOList.sort(Comparator.comparing(AttendingRatioDTO::getRatio));

        return attendingRatioDTOList;
    }
}
