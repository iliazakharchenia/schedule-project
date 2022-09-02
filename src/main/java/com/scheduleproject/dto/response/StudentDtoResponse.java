package com.scheduleproject.dto.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDtoResponse implements Serializable {
    private Integer id;
    private String name;
    private String surname;
}
