package com.scheduleproject.repos;

import com.scheduleproject.entities.Lesson;
import com.scheduleproject.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Component
@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    public List<Lesson> getLessonsByDayAndStudentsContaining(LocalDate date, Student student);
}