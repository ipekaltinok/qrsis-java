package com.project.qrsis.service;

import com.project.qrsis.dal.model.TeacherRole;
import com.project.qrsis.dal.repository.TeacherRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherRoleServiceImpl implements TeacherRoleService {

    private final TeacherRoleRepository teacherRoleRepository;

    @Autowired
    public TeacherRoleServiceImpl(TeacherRoleRepository teacherRoleRepository) {
        this.teacherRoleRepository = teacherRoleRepository;
    }

    public List<SimpleGrantedAuthority> getRolesFromAuthority(Integer teacherId) {

        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        teacherRoleRepository.findAllByTeacherId(teacherId).forEach(role -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        return roles;
    }

    public List<TeacherRole> getRoles(Integer teacherId) {
        return teacherRoleRepository.findAllByTeacherId(teacherId);
    }

    public void addRoleToTeacher(String role, Integer teacherId) {

        TeacherRole teacherRole = new TeacherRole();
        teacherRole.setTeacherId(teacherId);
        teacherRole.setRole(role);

        teacherRoleRepository.save(teacherRole);
    }

    public void removeRoleFromTeacher(Integer id) {

        TeacherRole teacherRole = teacherRoleRepository.findById(id).get();

        teacherRoleRepository.delete(teacherRole);
    }

    public void removeAllRolesFromTeacher(Integer teacherId) {
        List<TeacherRole> roles = getRoles(teacherId);

        teacherRoleRepository.deleteAll(roles);
    }
}
