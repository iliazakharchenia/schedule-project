package com.scheduleproject.controllers;

import com.scheduleproject.dto.request.SubjectDtoRequest;
import com.scheduleproject.dto.response.SubjectDtoResponse;
import com.scheduleproject.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subjects")
@RequiredArgsConstructor
public class SubjectsController {
    private final SubjectService service;

    @GetMapping
    public List<SubjectDtoResponse> getAll() {
        return service.getAll();
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
    public SubjectDtoResponse add(SubjectDtoRequest subjectDtoReq) {
        return service.create(subjectDtoReq);
    }
}
