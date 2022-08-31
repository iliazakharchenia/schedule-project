package com.scheduleproject.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentDtoResponse implements Serializable {
    private final Integer id;
    private final String name;
    private final String surname;
}
