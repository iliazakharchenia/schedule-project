package com.scheduleproject.repos;

import com.scheduleproject.entities.Lesson;
import com.scheduleproject.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    public List<Lesson> getLessonsBy(LocalDate date, Student student);
}