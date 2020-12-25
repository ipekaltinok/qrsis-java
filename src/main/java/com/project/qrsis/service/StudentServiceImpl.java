package com.project.qrsis.service;

import com.project.qrsis.dal.dto.ErrorMessage;
import com.project.qrsis.dal.dto.StudentDTO;
import com.project.qrsis.dal.dto.StudentInDTO;
import com.project.qrsis.dal.enums.UserType;
import com.project.qrsis.dal.model.Student;
import com.project.qrsis.dal.model.Teacher;
import com.project.qrsis.dal.repository.StudentRepository;
import com.project.qrsis.security.User;
import com.project.qrsis.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    /**
     * Verilen eposta adresine sahip öğrenciyi veritabanından bulur ve döner
     *
     * @param email             Öğrencinin eposta adresi
     * @return                  Student: Öğrenci
     */
    public User getUserForLogin(String email) {
        Student student = studentRepository.findFirstByEmail(email);
        if (student == null)
            return null;

        return new User(
                student.getId(),
                student.getEmail(),
                student.getPassword(),
                UserType.STUDENT
        );
    }

    public Student getStudent(Integer id) {
        return studentRepository.findById(id).get();
    }

    /**
     * Verilen bilgilerle yeni öğrenci kaydı oluşturur.
     *
     * @param dto   Öğrenci bilgileri nesnesi
     */
    public ErrorMessage createStudent(StudentInDTO dto) {

        String email = dto.getEmail();
        String studentNumber = dto.getStudentNumber();
        String name = dto.getName();
        String password = dto.getPassword();

        Student student = studentRepository.findFirstByStudentNumber(studentNumber);
        if (student != null)
            return new ErrorMessage(studentNumber + " öğrenci numarası başka bir öğrenciye ait!");

        if (!Utils.isValidEmail(email))
            return new ErrorMessage(email + " bir eposta adresi değildir! ");

        student = studentRepository.findFirstByEmail(email);
        if (student != null)
            return new ErrorMessage(email + " e-posta adresi başka bir öğrenciye ait!");

        student = new Student();
        student.setEmail(email);
        student.setName(name);
        student.setPassword(new BCryptPasswordEncoder().encode(password));
        student.setStudentNumber(studentNumber);

        studentRepository.save(student);

        return null;
    }

    /**
     * Verilen öğrenci bilgilerini günceller
     *
     * @param email             Yeni eposta adresi
     * @param name              Yeni isim
     * @param password          Yeni şifre
     */
    public ErrorMessage updateStudent (String email, String name, String password, Integer id) {

        Student student = getStudent(id);
        if (email != null) {

            if (!Utils.isValidEmail(email))
                return new ErrorMessage(email + " bir eposta adresi değildir! ");

            Student studentHaveEmail = studentRepository.findFirstByEmail(email);
            if (studentHaveEmail != null)
                return new ErrorMessage(email + " e-posta adresi başka bir öğrenciye ait!");

            student.setEmail(email);
        }

        if (name != null)
            student.setName(name);

        if (password != null)
            student.setPassword(new BCryptPasswordEncoder().encode(password));

        studentRepository.save(student);

        return null;
    }

    public void deleteStudent(Integer id) {

        Student student = getStudent(id);

        studentRepository.delete(student);
    }

    public List<StudentDTO> getStudents(String query) {

        List<StudentDTO> resultDTO = new ArrayList<>();

        List<Student> studentList = studentRepository.findStudents(query);
        for (Student student : studentList) {

            StudentDTO dto = new StudentDTO();
            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setEmail(student.getEmail());
            dto.setStudentNumber(student.getStudentNumber());

            resultDTO.add(dto);
        }

        return resultDTO;
    }

    public List<Student> getStudents(Set<Integer> studentList) {
        return studentRepository.findAllByIdIn(studentList);
    }
}
