package by.polchernikova.lab04spring.Model;

import by.polchernikova.lab04spring.Model.Classroom;
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

    public String getDescription() {
        return name + " from " + school + " in classroom " + classroom.getNumber().toString() + " in place " + place.toString();
    }

    public String getPartialDescription() {
        return name + " from " + school;
    }
}
