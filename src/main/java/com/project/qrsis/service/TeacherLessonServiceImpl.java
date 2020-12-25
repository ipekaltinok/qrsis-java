package com.project.qrsis.service;

import com.project.qrsis.dal.dto.TeacherDTO;
import com.project.qrsis.dal.model.Lesson;
import com.project.qrsis.dal.model.Teacher;
import com.project.qrsis.dal.model.TeacherLesson;
import com.project.qrsis.dal.repository.TeacherLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TeacherLessonServiceImpl implements TeacherLessonService {

    private final TeacherLessonRepository teacherLessonRepository;

    private final TeacherService teacherService;
    private final LessonService lessonService;

    @Autowired
    public TeacherLessonServiceImpl(TeacherLessonRepository teacherLessonRepository,
                                    TeacherService teacherService,
                                    LessonService lessonService) {

        this.teacherLessonRepository = teacherLessonRepository;

        this.teacherService = teacherService;
        this.lessonService = lessonService;
    }

    public List<Lesson> getLessonsOfTeacher(Integer teacherId) {

        Set<Integer> lessonIdList = teacherLessonRepository.findLessonsOfTeacher(teacherId);
        return lessonService.getLessons(lessonIdList);
    }

    public List<TeacherDTO> getTeachersOfLesson(Integer lessonId) {

        Set<Integer> teacherIdList = teacherLessonRepository.findTeachersOfLesson(lessonId);
        List<Teacher> teachers = teacherService.getTeachers(teacherIdList);

        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        for (Teacher teacher : teachers) {

            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setId(teacher.getId());
            teacherDTO.setEmail(teacher.getEmail());
            teacherDTO.setName(teacher.getName());

            teacherDTOList.add(teacherDTO);
        }

        return teacherDTOList;
    }

    public void addTeacherToLesson(Integer lessonId, Integer teacherId) {

        TeacherLesson teacherLesson = new TeacherLesson();
        teacherLesson.setLessonId(lessonId);
        teacherLesson.setTeacherId(teacherId);

        teacherLessonRepository.save(teacherLesson);
    }

    public void removeTeacherFromLesson(Integer lessonId, Integer teacherId) {

        TeacherLesson teacherLesson = teacherLessonRepository.findFirstByLessonIdAndTeacherId(lessonId, teacherId);

        teacherLessonRepository.delete(teacherLesson);
    }
}
