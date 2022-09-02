package com.scheduleproject.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "lesson")
@ToString(of = {"id", "day"})
@Table(name = "\"lessons\"")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate day;

    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> students;

    public Lesson(LocalDate day, Subject subject, List<Student> students) {
        this.day = day;
        this.subject = subject;
        this.students = students;
    }
}
