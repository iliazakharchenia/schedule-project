package com.scheduleproject.controllers;

import com.scheduleproject.dto.request.LessonDtoRequest;
import com.scheduleproject.dto.response.LessonDtoResponse;
import com.scheduleproject.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("lessons")
@RequiredArgsConstructor
public class LessonsController {
    private final LessonService service;

    @PostMapping("/add")
    public LessonDtoResponse add(LessonDtoRequest lessonDtoReq) {
        return service.create(lessonDtoReq);
    }

    @GetMapping("/lesson/{id}")
    public LessonDtoResponse get(@PathVariable Integer id) {
        return service.get(id);
    }

    @GetMapping
    public List<LessonDtoResponse> getLessons(@RequestBody Integer studentId,
                                               @RequestBody LocalDate date) {
        return service.getAllBy(studentId, date);
    }

    @PostMapping("/lesson")
    public LessonDtoResponse update(@RequestBody LessonDtoResponse lessonDtoResponse) {
        return service.update(lessonDtoResponse);
    }

    @DeleteMapping("/lesson/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
