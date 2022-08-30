package com.scheduleproject.entities;

import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;

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

    private DayOfWeek day;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Student> students;
}
