package com.project.qrsis.controller;

import com.project.qrsis.dal.dto.ErrorMessage;
import com.project.qrsis.dal.dto.TeacherInDTO;
import com.project.qrsis.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody TeacherInDTO dto) {

        ErrorMessage errorMessage = teacherService.createTeacher(dto);
        if (errorMessage != null)
            return ResponseEntity.badRequest().body(errorMessage);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTeacher(@RequestParam Integer id) {

        teacherService.deleteTeacher(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> updateTeacher(@RequestParam Integer id,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) String email,
                                           @RequestParam(required = false) String password) {

        ErrorMessage errorMessage = teacherService.updateTeacher(id, name, password, email);
        if (errorMessage != null)
            return ResponseEntity.badRequest().body(errorMessage);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> getTeachers(@RequestParam String query) {

        return ResponseEntity.ok(teacherService.getTeachers(query));
    }
}
