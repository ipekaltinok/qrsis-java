package com.project.qrsis.service;

import com.project.qrsis.dal.dto.ErrorMessage;
import com.project.qrsis.dal.dto.StudentDTO;
import com.project.qrsis.dal.dto.TeacherDTO;
import com.project.qrsis.dal.dto.TeacherInDTO;
import com.project.qrsis.dal.enums.UserType;
import com.project.qrsis.dal.model.Student;
import com.project.qrsis.dal.model.Teacher;
import com.project.qrsis.dal.repository.TeacherRepository;
import com.project.qrsis.security.User;
import com.project.qrsis.utils.Utils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final TeacherRoleService teacherRoleService;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository,
                              TeacherRoleService teacherRoleService) {

        this.teacherRepository = teacherRepository;

        this.teacherRoleService = teacherRoleService;
    }

    /**
     * Verilen eposta adresine sahip öğrenciyi veritabanından bulur ve döner
     *
     * @param email             Öğrencinin eposta adresi
     * @return                  Student: Öğrenci
     */
    public User getUserForLogin(String email) {
        Teacher teacher = teacherRepository.findFirstByEmail(email);
        if (teacher == null)
            return null;

        return new User(
                teacher.getId(),
                teacher.getEmail(),
                teacher.getPassword(),
                UserType.TEACHER
        );
    }

    public Teacher getTeacher(Integer id) {
        return teacherRepository.findById(id).get();
    }

    public ErrorMessage createTeacher(TeacherInDTO dto) {

        String email = dto.getEmail();
        String name = dto.getName();
        String password = dto.getPassword();

        if (!Utils.isValidEmail(email))
            return new ErrorMessage(email + " bir eposta adresi değildir! ");

        Teacher teacher = teacherRepository.findFirstByEmail(email);
        if (teacher != null)
            return new ErrorMessage(email + " e-posta adresi başka bir öğretmene ait!");

        teacher = new Teacher();
        teacher.setEmail(email);
        teacher.setName(name);
        teacher.setPassword(new BCryptPasswordEncoder().encode(password));

        teacherRepository.save(teacher);

        return null;
    }

    public ErrorMessage updateTeacher(Integer id, String name, String password, String email) {

        Teacher teacher = getTeacher(id);
        if (name != null)
            teacher.setName(name);

        if (password != null)
            teacher.setPassword(new BCryptPasswordEncoder().encode(password));

        if (email != null) {

            if (!Utils.isValidEmail(email))
                return new ErrorMessage(email + " bir eposta adresi değildir! ");

            Teacher teacherHaveEmail = teacherRepository.findFirstByEmail(email);
            if (teacherHaveEmail != null)
                return new ErrorMessage(email + " e-posta adresi başka bir öğretmene ait!");

            teacher.setEmail(email);
        }

        teacherRepository.save(teacher);

        return null;
    }

    public void deleteTeacher(Integer id) {

        Teacher teacher = getTeacher(id);

        teacherRepository.delete(teacher);

        teacherRoleService.removeAllRolesFromTeacher(id);
    }

    public List<TeacherDTO> getTeachers(String query) {

        List<TeacherDTO> resultDTO = new ArrayList<>();

        List<Teacher> teacherList = teacherRepository.findTeachers(query);
        for (Teacher teacher : teacherList) {

            TeacherDTO dto = new TeacherDTO();
            dto.setId(teacher.getId());
            dto.setName(teacher.getName());
            dto.setEmail(teacher.getEmail());

            resultDTO.add(dto);
        }

        return resultDTO;
    }

    public List<Teacher> getTeachers(Set<Integer> teacherIdList) {
        return teacherRepository.findAllByIdIn(teacherIdList);
    }
}
