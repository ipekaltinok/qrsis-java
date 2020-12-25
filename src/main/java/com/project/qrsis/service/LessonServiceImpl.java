package com.project.qrsis.service;

import com.project.qrsis.dal.dto.ErrorMessage;
import com.project.qrsis.dal.dto.LessonInDTO;
import com.project.qrsis.dal.model.Lesson;
import com.project.qrsis.dal.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public Lesson getLesson(Integer id) {
        return lessonRepository.findById(id).get();
    }

    public ErrorMessage createLesson(LessonInDTO dto) {

        String name = dto.getName();

        Lesson lesson = lessonRepository.findFirstByName(name);
        if (lesson != null)
            return new ErrorMessage("Bu isme ait bir ders sistemde mevcuttur! " + name);

        lesson = new Lesson();
        lesson.setName(name);

        lessonRepository.save(lesson);

        return null;
    }

    public ErrorMessage updateLesson(Integer id, String name) {

        Lesson lesson = lessonRepository.findFirstByName(name);
        if (lesson != null)
            return new ErrorMessage("Bu isme ait bir ders sistemde mevcuttur! " + name);

        lesson = getLesson(id);
        lesson.setName(name);

        lessonRepository.save(lesson);

        return null;
    }

    public void deleteLesson(Integer id) {

        Lesson lesson = getLesson(id);

        lessonRepository.delete(lesson);
    }

    public List<Lesson> getLessons(String name) {

        return lessonRepository.findLessons(name);
    }

    public List<Lesson> getLessons(Set<Integer> lessonIdList) {

        return lessonRepository.findAllByIdIn(lessonIdList);
    }
}
