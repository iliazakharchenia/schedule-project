package com.scheduleproject.dto.request;

import com.scheduleproject.dto.response.StudentDtoResponse;
import com.scheduleproject.dto.response.SubjectDtoResponse;
import lombok.Data;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.List;

@Data
public class LessonDtoRequest implements Serializable {
    private final DayOfWeek day;
    private final SubjectDtoResponse subject;
    private final List<StudentDtoResponse> students;
}