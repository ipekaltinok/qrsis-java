package com.project.qrsis.controller;

import com.project.qrsis.dal.dto.ErrorMessage;
import com.project.qrsis.dal.dto.LessonInDTO;
import com.project.qrsis.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public ResponseEntity<?> createLesson(@RequestBody LessonInDTO dto) {

        ErrorMessage errorMessage = lessonService.createLesson(dto);
        if (errorMessage != null)
            return ResponseEntity.badRequest().body(errorMessage);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLesson(@RequestParam Integer id) {

        lessonService.deleteLesson(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> updateLesson(@RequestParam Integer id,
                                          @RequestParam(required = false) String name) {

        ErrorMessage errorMessage = lessonService.updateLesson(id, name);
        if (errorMessage != null)
            return ResponseEntity.badRequest().body(errorMessage);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> getLessons(@RequestParam String query) {

        return ResponseEntity.ok(lessonService.getLessons(query));
    }
}
