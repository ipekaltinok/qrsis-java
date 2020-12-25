package com.project.qrsis.dal.repository;

import com.project.qrsis.dal.model.Student;
import com.project.qrsis.dal.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Teacher findFirstByEmail(String email);

    @Query("SELECT t FROM Teacher t WHERE t.email LIKE %?1% OR t.name LIKE %?1%")
    List<Teacher> findTeachers(String query);

    List<Teacher> findAllByIdIn(Set<Integer> teacherIdList);
}
