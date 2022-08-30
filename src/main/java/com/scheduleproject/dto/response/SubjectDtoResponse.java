package com.scheduleproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SubjectDtoResponse implements Serializable {
    private final Integer id;
    private final String name;
}
