package by.polchernikova.lab04spring.Model;

import by.polchernikova.lab04spring.Model.Classroom;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String school;

    @ManyToOne
    @JoinColumn(name = "classroom")
    private Classroom classroom;
    private Integer place;

    public String getDescription() {
        return name + " from " + school + " in classroom " + classroom.getNumber().toString() + " in place " + place.toString() + "\n";
    }

    public String getPartialDescription() {
        return name + " from " + school + "\n";
    }
}
