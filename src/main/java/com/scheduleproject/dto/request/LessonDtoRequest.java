package com.scheduleproject.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class LessonDtoRequest implements Serializable {
    @NotNull(message = "Should be not null")
    @JsonFormat(pattern="yyyy-MM-dd")
    private final LocalDate day;
    @NotNull(message = "Should be not null")
    private final Integer subjectId;
    @NotNull(message = "Should be not null")
    private final List<Integer> studentsIds;
}