package com.scheduleproject.services;

import com.scheduleproject.dto.request.StudentDtoRequest;
import com.scheduleproject.dto.response.StudentDtoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @SpyBean
    private StudentService service;

    @Test
    @Transactional
    void create() {
        StudentDtoRequest request = new StudentDtoRequest("vasiliy", "trockiy");
        StudentDtoResponse response = service.create(request);
        assertTrue(response.getName().equals(request.getName()) &&
                response.getSurname().equals(request.getSurname()));
    }

    @Test
    @Transactional
    void getAll() {
        StudentDtoRequest request1 = new StudentDtoRequest("vasiliy", "trockiy");
        StudentDtoRequest request2 = new StudentDtoRequest("vlad", "rak");
        StudentDtoRequest request3 = new StudentDtoRequest("gleb", "popov");

        service.create(request1);
        service.create(request2);
        service.create(request3);

        assertTrue(service.getAll().get(0).getName().equals(request1.getName())
                            && service.getAll().get(1).getName().equals(request2.getName())
                            && service.getAll().get(2).getName().equals(request3.getName())
                            && service.getAll().get(0).getSurname().equals(request1.getSurname())
                            && service.getAll().get(1).getSurname().equals(request2.getSurname())
                            && service.getAll().get(2).getSurname().equals(request3.getSurname())
        );
    }

    @Test
    @Transactional
    void get() {
        StudentDtoRequest request1 = new StudentDtoRequest("vasiliy", "trockiy");
        StudentDtoResponse response1 = service.create(request1);
        assertEquals(service.get(response1.getId()).getId(), response1.getId());
        assertEquals(service.get(response1.getId()).getName(), response1.getName());
        assertEquals(service.get(response1.getId()).getSurname(), response1.getSurname());
    }

    @Test
    @Transactional
    void update() {
        StudentDtoRequest request1 = new StudentDtoRequest("vasiliy", "trockiy");
        StudentDtoResponse response1 = service.create(request1);
        response1.setName("vladimir");
        StudentDtoResponse response2 = service.update(response1);
        assertEquals(service.get(response1.getId()).getId(), response2.getId());
        assertEquals(service.get(response1.getId()).getName(), response2.getName());
        assertEquals(service.get(response1.getId()).getSurname(), response2.getSurname());
    }

    @Test
    @Transactional
    void delete() {
        StudentDtoRequest request1 = new StudentDtoRequest("vasiliy", "trockiy");
        StudentDtoResponse response1 = service.create(request1);
        service.delete(response1.getId());
        List<StudentDtoResponse> responseList = new ArrayList<>();
        assertEquals(service.getAll(), responseList);
    }
}