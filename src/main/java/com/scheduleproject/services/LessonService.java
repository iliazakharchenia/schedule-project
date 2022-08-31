package com.scheduleproject.services;

import com.scheduleproject.dto.request.LessonDtoRequest;
import com.scheduleproject.dto.response.LessonDtoResponse;
import com.scheduleproject.entities.Lesson;
import com.scheduleproject.entities.Student;
import com.scheduleproject.entities.Subject;
import com.scheduleproject.exceptions.NoEntityByIdException;
import com.scheduleproject.repos.LessonRepository;
import com.scheduleproject.repos.StudentRepository;
import com.scheduleproject.repos.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Component
public class LessonService {
    private final LessonRepository lessonRepository;

    private final SubjectRepository subjectRepository;

    private final StudentRepository studentRepository;

    @Autowired
    private final ModelMapper mapper;

    @Transactional
    public Lesson create(LessonDtoRequest lessonDtoReq) {
        List<Integer> ids = new ArrayList<>();
        lessonDtoReq.getStudents().forEach(el->ids.add(el.getId()));
        List<Student> students = studentRepository.findAllById(ids);
        Optional<Subject> subject = subjectRepository.findById(lessonDtoReq.getSubject().getId());
        if (subject.isPresent())
            return lessonRepository.save(new Lesson(lessonDtoReq.getDay(), subject.get(), students));
        else throw new NoEntityByIdException("No subject entity with id="+lessonDtoReq.getSubject().getId());
    }

    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }

    public Lesson getById(Integer id) {
        return lessonRepository.getReferenceById(id);
    }

    @Transactional
    public void delete(Integer lessonId) {
        lessonRepository.deleteById(lessonId);
    }
}
