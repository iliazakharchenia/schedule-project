package com.scheduleproject.services;

import com.scheduleproject.entities.Lesson;
import com.scheduleproject.repos.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Component
public class LessonService {
    private final LessonRepository lessonRepository;

    @Transactional
    public Lesson create(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }

    public Lesson getById(Integer id) {
        return lessonRepository.getReferenceById(id);
    }

    @Transactional
    public void delete(Lesson lesson) {
        lessonRepository.delete(lesson);
    }
}
