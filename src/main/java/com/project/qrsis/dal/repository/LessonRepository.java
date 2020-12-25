package com.project.qrsis.dal.repository;

import com.project.qrsis.dal.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface LessonRepository extends JpaRepository<Lesson, Integer>  {

    @Query("SELECT l FROM Lesson l WHERE l.name LIKE %?1%")
    List<Lesson> findLessons(String name);

    List<Lesson> findAllByIdIn(Set<Integer> idList);

    Lesson findFirstByName(String name);
}
