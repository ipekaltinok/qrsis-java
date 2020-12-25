package com.project.qrsis.controller;

import com.project.qrsis.dal.dto.StudentAttendanceDTO;
import com.project.qrsis.dal.dto.StudentAttendanceInDTO;
import com.project.qrsis.service.StudentAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/attendance")
public class StudentAttendanceController {

    private final StudentAttendanceService studentAttendanceService;

    @Autowired
    public StudentAttendanceController(StudentAttendanceService studentAttendanceService) {
        this.studentAttendanceService = studentAttendanceService;
    }

    @PostMapping
    public ResponseEntity<?> addAttendanceToLessonForStudent(@RequestBody StudentAttendanceInDTO dto) {

        studentAttendanceService.addAttendanceToLessonForStudent(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> removeAttendanceToLessonForStudent(@RequestParam Integer studentId,
                                                             @RequestParam Integer lessonId) {

        studentAttendanceService.removeAttendanceFromLessonForStudent(lessonId, studentId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> getStudentAttendance(@RequestParam Integer studentId,
                                                  @RequestParam Integer lessonId) {

        StudentAttendanceDTO studentAttendance = studentAttendanceService.getStudentAttendances(studentId, lessonId);

        return ResponseEntity.ok(studentAttendance);
    }
}
