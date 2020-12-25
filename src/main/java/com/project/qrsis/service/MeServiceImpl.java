package com.project.qrsis.service;

import com.project.qrsis.dal.dto.UserDTO;
import com.project.qrsis.dal.enums.UserType;
import com.project.qrsis.dal.model.Student;
import com.project.qrsis.dal.model.Teacher;
import com.project.qrsis.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class MeServiceImpl implements MeService {

    private final StudentService studentService;
    private final TeacherService teacherService;

    @Autowired
    public MeServiceImpl(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    public UserDTO me(Authentication authentication) {

        if (authentication == null)
            return null;

        User user = (User) authentication.getPrincipal();
        if (user == null)
            return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUserType(user.getUserType().getValue());

        if (user.getUserType() == UserType.TEACHER) {
            for (GrantedAuthority role : user.getAuthorities()) {
                dto.getRoles().add(role.getAuthority());
            }

            Teacher teacher = teacherService.getTeacher(user.getId());
            dto.setName(teacher.getName());

        } else if (user.getUserType() == UserType.STUDENT){
            Student student = studentService.getStudent(user.getId());
            dto.setName(student.getName());
        }

        return dto;
    }
}
