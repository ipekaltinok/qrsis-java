package com.project.qrsis.service;

import com.project.qrsis.dal.dto.StudentDTO;
import com.project.qrsis.dal.model.Lesson;
import com.project.qrsis.dal.model.Student;
import com.project.qrsis.dal.model.StudentLesson;
import com.project.qrsis.dal.repository.StudentLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class StudentLessonServiceImpl implements StudentLessonService {

    private final StudentLessonRepository studentLessonRepository;

    private final LessonService lessonService;
    private final StudentService studentService;

    @Autowired
    public StudentLessonServiceImpl(StudentLessonRepository studentLessonRepository,
                                    LessonService lessonService,
                                    StudentService studentService) {

        this.studentLessonRepository = studentLessonRepository;

        this.lessonService = lessonService;
        this.studentService = studentService;
    }

    public List<StudentDTO> getStudentsOfLesson(Integer lessonId) {

        Set<Integer> studentIdList = studentLessonRepository.findStudentsOfLesson(lessonId);
        List<Student> students = studentService.getStudents(studentIdList);

        List<StudentDTO> studentDTOList = new ArrayList<>();
        for (Student student : students) {

            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(student.getId());
            studentDTO.setStudentNumber(student.getStudentNumber());
            studentDTO.setEmail(student.getEmail());
            studentDTO.setName(student.getName());

            studentDTOList.add(studentDTO);
        }

        return studentDTOList;
    }

    public void addStudentToLesson(Integer lessonId, Integer studentId) {

        StudentLesson studentLesson = new StudentLesson();
        studentLesson.setLessonId(lessonId);
        studentLesson.setStudentId(studentId);

        studentLessonRepository.save(studentLesson);
    }

    public void removeStudentFromLesson(Integer studentId, Integer lessonId) {

        StudentLesson studentLesson = studentLessonRepository.findFirstByStudentIdAndLessonId(studentId, lessonId);

        studentLessonRepository.delete(studentLesson);
    }

    public List<Lesson> getLessonsOfStudent(Integer studentId) {

        Set<Integer> lessonIdList = studentLessonRepository.findLessonsOfStudent(studentId);
        return lessonService.getLessons(lessonIdList);
    }
}
