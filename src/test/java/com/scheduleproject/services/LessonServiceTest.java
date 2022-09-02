package com.scheduleproject.services;

import com.scheduleproject.dto.request.LessonDtoRequest;
import com.scheduleproject.dto.request.StudentDtoRequest;
import com.scheduleproject.dto.request.SubjectDtoRequest;
import com.scheduleproject.dto.response.LessonDtoResponse;
import com.scheduleproject.dto.response.StudentDtoResponse;
import com.scheduleproject.dto.response.SubjectDtoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LessonServiceTest {

    @SpyBean
    private LessonService lessonService;

    @SpyBean
    private SubjectService subjectService;

    @SpyBean
    private StudentService studentService;

    @Test
    @Transactional
    void create() {
        SubjectDtoResponse subjectDtoResponse = subjectService
                .create(new SubjectDtoRequest("math"));
        StudentDtoResponse studentDtoResponse1 = studentService
                .create(new StudentDtoRequest("john","smith"));
        StudentDtoResponse studentDtoResponse2 = studentService
                .create(new StudentDtoRequest("jane","turner"));
        List<Integer> ids = new ArrayList<>();
        ids.add(studentDtoResponse1.getId());
        ids.add(studentDtoResponse2.getId());
        LessonDtoRequest request = new LessonDtoRequest(
                LocalDate.of(2022, 11, 3),
                subjectDtoResponse.getId(),
                ids
        );
        LessonDtoResponse lessonDtoResponse = lessonService.create(request);
        assertEquals(lessonDtoResponse.getDay(), request.getDay());
        assertEquals(lessonDtoResponse.getSubject().getId(), request.getSubjectId());
        assertEquals(lessonDtoResponse.getSubject().getName(), subjectDtoResponse.getName());
        assertEquals(lessonDtoResponse.getStudents().get(0).getId(), ids.get(0));
        assertEquals(lessonDtoResponse.getStudents().get(1).getId(), ids.get(1));
        assertEquals(lessonDtoResponse.getStudents().get(0).getName(), studentDtoResponse1.getName());
        assertEquals(lessonDtoResponse.getStudents().get(1).getName(), studentDtoResponse2.getName());
    }

    @Test
    @Transactional
    void get() {
        LessonDtoResponse response = getLesson();
        LessonDtoResponse actualResponse = lessonService.get(response.getId());
        assertEquals(actualResponse.getId(),response.getId());
        assertEquals(actualResponse.getSubject().getName(),response.getSubject().getName());
        assertEquals(actualResponse.getDay(),response.getDay());
        assertEquals(actualResponse.getStudents().size(),response.getStudents().size());
        assertEquals(actualResponse.getStudents().get(0).getId(), response.getStudents().get(0).getId());
        assertEquals(actualResponse.getStudents().get(1).getId(), response.getStudents().get(1).getId());
        assertEquals(actualResponse.getStudents().get(0).getName(), response.getStudents().get(0).getName());
        assertEquals(actualResponse.getStudents().get(1).getName(), response.getStudents().get(1).getName());
    }

    @Test
    @Transactional
    void update() {
        LessonDtoResponse response = getLesson();
        response.setSubject(subjectService.create(new SubjectDtoRequest("english")));
        LessonDtoResponse actualResponse = lessonService.update(response);
        assertEquals(actualResponse.getId(),response.getId());
        assertEquals(actualResponse.getSubject().getName(),response.getSubject().getName());
        assertEquals(actualResponse.getDay(),response.getDay());
        assertEquals(actualResponse.getStudents().size(),response.getStudents().size());
        assertEquals(actualResponse.getStudents().get(0).getId(), response.getStudents().get(0).getId());
        assertEquals(actualResponse.getStudents().get(1).getId(), response.getStudents().get(1).getId());
        assertEquals(actualResponse.getStudents().get(0).getName(), response.getStudents().get(0).getName());
        assertEquals(actualResponse.getStudents().get(1).getName(), response.getStudents().get(1).getName());
    }

    @Test
    @Transactional
    void getAllBy() {
        SubjectDtoResponse subjectDtoResponse = subjectService
                .create(new SubjectDtoRequest("math"));
        StudentDtoResponse studentDtoResponse1 = studentService
                .create(new StudentDtoRequest("john","smith"));
        StudentDtoResponse studentDtoResponse2 = studentService
                .create(new StudentDtoRequest("jane","turner"));
        List<Integer> ids = new ArrayList<>();
        ids.add(studentDtoResponse1.getId());
        ids.add(studentDtoResponse2.getId());
        LessonDtoRequest request = new LessonDtoRequest(
                LocalDate.of(2022, 11, 3),
                subjectDtoResponse.getId(),
                ids
        );
        LessonDtoResponse lessonDtoResponse1 = lessonService.create(request);
        request.setDay(LocalDate.of(2022, 11, 4));
        SubjectDtoResponse subjectDtoResponse1 = subjectService.create(new SubjectDtoRequest("english"));
        request.setSubjectId(subjectDtoResponse1.getId());
        LessonDtoResponse lessonDtoResponse2 = lessonService.create(request);
        assertEquals(lessonService.getAllBy(studentDtoResponse1.getId(), request.getDay()).size(),1);
        assertEquals(lessonService.getAllBy(studentDtoResponse1.getId(), request.getDay()).get(0)
                        .getSubject().getName(), subjectDtoResponse1.getName());
    }

    @Test
    @Transactional
    void delete() {
        LessonDtoResponse lesson = getLesson();
        lessonService.delete(lesson.getId());
        assertEquals(0, lessonService.getAllBy(lesson.getStudents().get(0).getId(), LocalDate.of(2022, 11, 3)).size());
    }

    private LessonDtoResponse getLesson() {
        SubjectDtoResponse subjectDtoResponse = subjectService
                .create(new SubjectDtoRequest("math"));
        StudentDtoResponse studentDtoResponse1 = studentService
                .create(new StudentDtoRequest("john","smith"));
        StudentDtoResponse studentDtoResponse2 = studentService
                .create(new StudentDtoRequest("jane","turner"));
        List<Integer> ids = new ArrayList<>();
        ids.add(studentDtoResponse1.getId());
        ids.add(studentDtoResponse2.getId());
        LessonDtoRequest request = new LessonDtoRequest(
                LocalDate.of(2022, 11, 3),
                subjectDtoResponse.getId(),
                ids
        );
        return lessonService.create(request);
    }
}