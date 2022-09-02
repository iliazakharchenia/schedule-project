package com.scheduleproject.dto.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDtoResponse implements Serializable {
    private Integer id;
    private String name;
}
