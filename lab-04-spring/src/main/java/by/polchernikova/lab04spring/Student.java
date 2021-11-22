package by.polchernikova.lab04spring;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String school;

    @ManyToOne
    @JoinColumn(name = "classroom")
    Classroom classroom;
    Integer place;
}
