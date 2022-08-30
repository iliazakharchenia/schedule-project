package com.scheduleproject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(of = {"id", "name"})
@Table(name = "\"subjects\"")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private List<Lesson> lessons;
}
