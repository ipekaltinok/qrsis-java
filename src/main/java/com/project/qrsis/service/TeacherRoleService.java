package com.project.qrsis.service;

import com.project.qrsis.dal.model.TeacherRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public interface TeacherRoleService {

    List<SimpleGrantedAuthority> getRolesFromAuthority(Integer teacherId);

    void removeRoleFromTeacher(Integer id);

    void addRoleToTeacher(String role, Integer teacherId);

    List<TeacherRole> getRoles(Integer teacherId);

    void removeAllRolesFromTeacher(Integer teacherId);
}
