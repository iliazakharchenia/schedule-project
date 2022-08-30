package com.scheduleproject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(of = {"id", "name", "surname"})
@Table(name = "\"students\"")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Lesson> lessons;
}
