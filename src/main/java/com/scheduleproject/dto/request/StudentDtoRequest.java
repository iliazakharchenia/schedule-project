package com.scheduleproject.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class StudentDtoRequest implements Serializable {
    @NotNull(message = "Should be not null")
    @Size(min = 1, max = 21, message = "Length can't be =>1 or =<21")
    private final String name;
    @NotNull(message = "Should be not null")
    @Size(min = 1, max = 21, message = "Length can't be =>1 or =<21")
    private final String surname;
}
