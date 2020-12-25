package com.project.qrsis.dal.repository;

import com.project.qrsis.dal.model.TeacherRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRoleRepository extends JpaRepository<TeacherRole, Integer> {

    List<TeacherRole> findAllByTeacherId(Integer teacherId);
}
