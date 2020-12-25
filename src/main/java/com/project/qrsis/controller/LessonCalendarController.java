package com.project.qrsis.controller;

import com.project.qrsis.dal.dto.ErrorMessage;
import com.project.qrsis.dal.dto.LessonCalendarInDTO;
import com.project.qrsis.service.LessonCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lesson/calendar")
public class LessonCalendarController {

    private final LessonCalendarService lessonCalendarService;

    @Autowired
    public LessonCalendarController(LessonCalendarService lessonCalendarService) {
        this.lessonCalendarService = lessonCalendarService;
    }

    @PostMapping
    public ResponseEntity<?> createLessonCalendar(@RequestBody LessonCalendarInDTO dto) {

        ErrorMessage errorMessage = lessonCalendarService.createLessonCalendar(dto);
        if (errorMessage != null)
            return ResponseEntity.badRequest().body(errorMessage);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<?> getLessonCalendar(@RequestParam Integer lessonId) {

        return ResponseEntity.ok(lessonCalendarService.getLessonCalendar(lessonId));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLessonCalendar(@RequestParam Integer id) {

        lessonCalendarService.deleteLessonCalendar(id);

        return ResponseEntity.noContent().build();
    }
}
