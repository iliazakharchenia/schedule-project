package com.scheduleproject.controllers;

import com.scheduleproject.dto.request.StudentDtoRequest;
import com.scheduleproject.dto.response.StudentDtoResponse;
import com.scheduleproject.entities.Student;
import com.scheduleproject.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentsController {
    private final StudentService service;

    @Autowired
    private final ModelMapper modelMapper;

    @GetMapping
    public List<StudentDtoResponse> getAll() {
        return service.getAll().stream()
                .map(el->modelMapper.map(el,StudentDtoResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/student/{id}")
    public StudentDtoResponse get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PostMapping("/student")
    public StudentDtoResponse update(@RequestBody StudentDtoResponse studentDtoResp) {
        return service.update(studentDtoResp);
    }

    @DeleteMapping("/student/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PostMapping("/add")
    public Student add(StudentDtoRequest studentDtoReq) {
        return service.create(studentDtoReq);
    }
}
