package com.project.qrsis.controller;

import com.project.qrsis.service.TeacherLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeacherLessonController {

    private final TeacherLessonService teacherLessonService;

    @Autowired
    public TeacherLessonController(TeacherLessonService teacherLessonService) {
        this.teacherLessonService = teacherLessonService;
    }

    @GetMapping("/lesson/teacher")
    public ResponseEntity<?> getTeachersOfLesson(@RequestParam Integer lessonId) {

        return ResponseEntity.ok(teacherLessonService.getTeachersOfLesson(lessonId));
    }

    @GetMapping("/teacher/lesson")
    public ResponseEntity<?> getLessonsOfTeacher(@RequestParam Integer teacherId) {

        return ResponseEntity.ok(teacherLessonService.getLessonsOfTeacher(teacherId));
    }

    @DeleteMapping("/lesson/teacher")
    public ResponseEntity<?> removeTeacherFromLesson(@RequestParam Integer lessonId,
                                                     @RequestParam Integer teacherId) {

        teacherLessonService.removeTeacherFromLesson(lessonId, teacherId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/lesson/teacher")
    public ResponseEntity<?> addTeacherToLesson(@RequestParam Integer lessonId,
                                                @RequestParam Integer teacherId) {

        teacherLessonService.addTeacherToLesson(lessonId, teacherId);

        return ResponseEntity.noContent().build();
    }
}
