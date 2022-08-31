package com.scheduleproject.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentDtoRequest implements Serializable {
    private final Integer id;
    private final String name;
    private final String surname;
}
