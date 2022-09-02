package com.scheduleproject.services;

import com.scheduleproject.dto.request.LessonDtoRequest;
import com.scheduleproject.dto.response.LessonDtoResponse;
import com.scheduleproject.dto.response.StudentDtoResponse;
import com.scheduleproject.dto.response.SubjectDtoResponse;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public LessonDtoResponse create(LessonDtoRequest lessonDtoReq) {
        List<Student> students = studentRepository.findAllById(lessonDtoReq.getStudentsIds());
        Optional<Subject> subject = subjectRepository.findById(lessonDtoReq.getSubjectId());
        if (subject.isPresent())
            return get(lessonRepository.save(new Lesson(lessonDtoReq.getDay(), subject.get(), students)).getId());
        else throw new NoEntityByIdException("No subject entity with id="+
                lessonDtoReq.getSubjectId());
    }

    public LessonDtoResponse get(Integer id) {
        Lesson lesson = lessonRepository.getReferenceById(id);
        List<StudentDtoResponse> studentDtosResp = getStudentDtosResponse(lesson);
        return new LessonDtoResponse(
                lesson.getId(),
                lesson.getDay(),
                mapper.map(lesson.getSubject(), SubjectDtoResponse.class),
                studentDtosResp);
    }

    @Transactional
    public LessonDtoResponse update(LessonDtoResponse response) {
        Lesson lesson = lessonRepository.getReferenceById(response.getId());
        lesson.setDay(response.getDay());
        lesson.setSubject(mapper.map(response.getSubject(), Subject.class));
        lesson.setStudents(response.getStudents().stream().map(el->mapper.map(el, Student.class))
                .collect(Collectors.toList()));
        lessonRepository.save(lesson);
        return get(lesson.getId());
    }

    public List<LessonDtoResponse> getAllBy(Integer studentId, LocalDate date) {
        List<Lesson> lessons = lessonRepository.getLessonsByDayAndStudentsContaining(date,
                studentRepository.getReferenceById(studentId));
        List<LessonDtoResponse> lessonsDtosResp = new ArrayList<>();
        lessons.forEach(el->lessonsDtosResp.add(
                new LessonDtoResponse(
                    el.getId(),
                    el.getDay(),
                    mapper.map(el.getSubject(), SubjectDtoResponse.class),
                    getStudentDtosResponse(el))));
        return lessonsDtosResp;
    }

    @Transactional
    public void delete(Integer lessonId) {
        lessonRepository.deleteById(lessonId);
    }

    private List<StudentDtoResponse> getStudentDtosResponse(Lesson lesson) {
        List<StudentDtoResponse> studentDtosResp = new ArrayList<>();
        lesson.getStudents().forEach(el->studentDtosResp.add(mapper.map(el,StudentDtoResponse.class)));
        return studentDtosResp;
    }
}
