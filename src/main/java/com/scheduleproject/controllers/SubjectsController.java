package com.scheduleproject.controllers;

import com.scheduleproject.dto.request.SubjectDtoRequest;
import com.scheduleproject.dto.response.SubjectDtoResponse;
import com.scheduleproject.entities.Subject;
import com.scheduleproject.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("subjects")
@RequiredArgsConstructor
public class SubjectsController {
    private final SubjectService service;

    @Autowired
    private final ModelMapper modelMapper;

    @GetMapping
    public List<SubjectDtoResponse> getAll() {
        return service.getAll().stream()
                .map(el->modelMapper.map(el,SubjectDtoResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/subject/{id}")
    public SubjectDtoResponse get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PostMapping("/subject")
    public SubjectDtoResponse update(@RequestBody SubjectDtoResponse subjectDtoResp) {
        return service.update(subjectDtoResp);
    }

    @DeleteMapping("/subject/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PostMapping("/add")
    public Subject add(SubjectDtoRequest subjectDtoReq) {
        return service.create(subjectDtoReq);
    }
}
