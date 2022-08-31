package com.scheduleproject.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.List;

@Data
public class LessonDtoResponse implements Serializable {
    private final Integer id;
    private final DayOfWeek day;
    private final SubjectDtoResponse subject;
    private final List<StudentDtoResponse> students;
}
