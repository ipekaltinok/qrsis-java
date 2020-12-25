package com.project.qrsis.service;

import com.project.qrsis.dal.dto.ErrorMessage;
import com.project.qrsis.dal.dto.TeacherDTO;
import com.project.qrsis.dal.dto.TeacherInDTO;
import com.project.qrsis.dal.model.Teacher;
import com.project.qrsis.security.User;

import java.util.List;
import java.util.Set;

public interface TeacherService {

    User getUserForLogin(String email);

    ErrorMessage createTeacher(TeacherInDTO dto);

    void deleteTeacher(Integer id);

    ErrorMessage updateTeacher(Integer id, String name, String password, String email);

    Teacher getTeacher(Integer id);

    List<TeacherDTO> getTeachers(String query);

    List<Teacher> getTeachers(Set<Integer> teacherIdList);
}
