package com.scheduleproject.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(of = {"id", "day"})
@Table(name = "\"lessons\"")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate day;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Student> students;

    public Lesson(LocalDate day, Subject subject, List<Student> students) {

    }
}
