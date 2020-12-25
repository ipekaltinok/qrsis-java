package com.project.qrsis.service;

import com.project.qrsis.dal.dto.ErrorMessage;
import com.project.qrsis.dal.dto.StudentDTO;
import com.project.qrsis.dal.dto.StudentInDTO;
import com.project.qrsis.dal.model.Student;
import com.project.qrsis.security.User;

import java.util.List;
import java.util.Set;

public interface StudentService {

    User getUserForLogin(String email);

    ErrorMessage createStudent(StudentInDTO dto);

    ErrorMessage updateStudent (String email, String name, String password, Integer studentNumber);

    void deleteStudent(Integer studentId);

    Student getStudent(Integer id);

    List<StudentDTO> getStudents(String query);

    List<Student> getStudents(Set<Integer> studentList);
}
