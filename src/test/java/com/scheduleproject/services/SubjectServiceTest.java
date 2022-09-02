package com.scheduleproject.services;

import com.scheduleproject.dto.request.SubjectDtoRequest;
import com.scheduleproject.dto.response.SubjectDtoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubjectServiceTest {

    @SpyBean
    private SubjectService service;

    @Test
    @Transactional
    void create() {
        SubjectDtoRequest request = new SubjectDtoRequest("math");
        SubjectDtoResponse response = service.create(request);
        assertEquals(request.getName(),response.getName());
    }

    @Test
    @Transactional
    void getAll() {
        SubjectDtoRequest request = new SubjectDtoRequest("math");
        SubjectDtoResponse response = service.create(request);
        SubjectDtoRequest request1 = new SubjectDtoRequest("english");
        SubjectDtoResponse response1 = service.create(request1);
        assertEquals(service.getAll().get(0).getId(), response.getId());
        assertEquals(service.getAll().get(0).getName(), response.getName());
        assertEquals(service.getAll().get(1).getId(), response1.getId());
        assertEquals(service.getAll().get(1).getName(), response1.getName());
    }

    @Test
    @Transactional
    void get() {
        SubjectDtoRequest request = new SubjectDtoRequest("math");
        SubjectDtoResponse response = service.create(request);
        assertEquals(response.getId(), service.get(response.getId()).getId());
        assertEquals(response.getName(), service.get(response.getId()).getName());
    }

    @Test
    @Transactional
    void delete() {
        SubjectDtoRequest request = new SubjectDtoRequest("math");
        SubjectDtoResponse response = service.create(request);
        service.delete(response.getId());
        assertEquals(service.getAll(), new ArrayList<SubjectDtoResponse>());
    }

    @Test
    @Transactional
    void update() {
        SubjectDtoRequest request = new SubjectDtoRequest("math");
        SubjectDtoResponse response = service.create(request);
        response.setName("programming");
        service.update(response);
        assertEquals(service.get(response.getId()).getName(), response.getName());
    }
}