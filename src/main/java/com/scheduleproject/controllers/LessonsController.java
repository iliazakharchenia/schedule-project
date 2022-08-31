package com.scheduleproject.controllers;

import com.scheduleproject.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("subjects")
@RequiredArgsConstructor
public class LessonsController {
    private final SubjectService service;

    @Autowired
    private final ModelMapper modelMapper;
}
