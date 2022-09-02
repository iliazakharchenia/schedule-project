package com.scheduleproject.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonDtoRequest implements Serializable {
    @NotNull(message = "Should be not null")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate day;
    @NotNull(message = "Should be not null")
    private Integer subjectId;
    @NotNull(message = "Should be not null")
    private List<Integer> studentsIds;
}