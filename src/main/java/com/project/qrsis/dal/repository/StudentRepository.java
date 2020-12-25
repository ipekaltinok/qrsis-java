package com.project.qrsis.dal.repository;

import com.project.qrsis.dal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findFirstByStudentNumber(String studentNumber);

    Student findFirstByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.email LIKE %?1% OR s.name LIKE %?1% OR s.studentNumber LIKE %?1%")
    List<Student> findStudents(String query);

    List<Student> findAllByIdIn(Set<Integer> studentIdList);
}
