package com.project.qrsis.controller;

import com.project.qrsis.dal.dto.ErrorMessage;
import com.project.qrsis.dal.dto.StudentInDTO;
import com.project.qrsis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentInDTO dto) {

        ErrorMessage errorMessage = studentService.createStudent(dto);
        if (errorMessage != null)
            return ResponseEntity.badRequest().body(errorMessage);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteStudent(@RequestParam Integer id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> getStudent(@RequestParam String query) {

        return ResponseEntity.ok(studentService.getStudents(query));
    }

    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestParam Integer id,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) String email,
                                           @RequestParam(required = false) String password) {

        ErrorMessage errorMessage = studentService.updateStudent(email, name, password, id);
        if (errorMessage != null)
            return ResponseEntity.badRequest().body(errorMessage);

        return ResponseEntity.noContent().build();
    }
}
