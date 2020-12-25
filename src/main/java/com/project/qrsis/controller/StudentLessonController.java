package com.project.qrsis.controller;

import com.project.qrsis.service.StudentLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentLessonController {

    private final StudentLessonService studentLessonService;

    @Autowired
    public StudentLessonController(StudentLessonService studentLessonService) {
        this.studentLessonService = studentLessonService;
    }

    @GetMapping("/lesson/student")
    public ResponseEntity<?> getStudentsOfLesson(@RequestParam Integer lessonId) {

        return ResponseEntity.ok(studentLessonService.getStudentsOfLesson(lessonId));
    }

    @DeleteMapping("/lesson/student")
    public ResponseEntity<?> removeStudentFromLesson(@RequestParam Integer lessonId,
                                                     @RequestParam Integer studentId) {

        studentLessonService.removeStudentFromLesson(studentId, lessonId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/lesson/student")
    public ResponseEntity<?> addStudentToLesson(@RequestParam Integer lessonId,
                                                @RequestParam Integer studentId) {

        studentLessonService.addStudentToLesson(lessonId, studentId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/lesson")
    public ResponseEntity<?> getLessonsOfStudent(@RequestParam Integer studentId) {

        return ResponseEntity.ok(studentLessonService.getLessonsOfStudent(studentId));
    }
}
