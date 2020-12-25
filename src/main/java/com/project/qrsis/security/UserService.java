package com.project.qrsis.security;

import com.project.qrsis.service.StudentService;
import com.project.qrsis.service.TeacherRoleService;
import com.project.qrsis.service.TeacherService;
import com.project.qrsis.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final TeacherRoleService teacherRoleService;

    @Autowired
    public UserService(StudentService studentService,
                       TeacherService teacherService,
                       TeacherRoleService teacherRoleService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.teacherRoleService = teacherRoleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = null;
        if (Utils.isValidEmail(username)) {
            user = studentService.getUserForLogin(username);
            if (user == null) {
                user = teacherService.getUserForLogin(username);
                user.setRoles(teacherRoleService.getRolesFromAuthority(user.getId()));
            }
        }

        return user;
    }
}
