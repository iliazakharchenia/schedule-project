package com.scheduleproject.dto.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonDtoResponse implements Serializable {
    private Integer id;
    private LocalDate day;
    private SubjectDtoResponse subject;
    private List<StudentDtoResponse> students;
}
