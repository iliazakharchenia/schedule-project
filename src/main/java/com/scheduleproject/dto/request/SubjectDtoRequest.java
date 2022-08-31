package com.scheduleproject.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubjectDtoRequest implements Serializable {
    private final String name;
}
