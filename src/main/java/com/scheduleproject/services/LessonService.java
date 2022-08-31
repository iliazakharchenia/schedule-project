package com.scheduleproject.services;

import com.scheduleproject.dto.request.LessonDtoRequest;
import com.scheduleproject.dto.response.LessonDtoResponse;
import com.scheduleproject.entities.Lesson;
import com.scheduleproject.repos.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Component
public class LessonService {
    private final LessonRepository lessonRepository;

    @Autowired
    private final ModelMapper mapper;

    @Transactional
    public Lesson create(LessonDtoRequest lessonDtoReq) {
        return lessonRepository.save(mapper.map(lessonDtoReq, Lesson.class));
    }

    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }

    public Lesson getById(Integer id) {
        return lessonRepository.getReferenceById(id);
    }

    public Lesson getById(LessonDtoResponse lessonDtoResp) {
        return lessonRepository.getReferenceById(lessonDtoResp.getId());
    }

    @Transactional
    public void delete(Lesson lesson) {
        lessonRepository.delete(lesson);
    }

    @Transactional
    public void delete(Integer lessonId) {
        lessonRepository.deleteById(lessonId);
    }

    @Transactional
    public void delete(LessonDtoResponse lessonDtoResp) {
        lessonRepository.deleteById(lessonDtoResp.getId());
    }
}
