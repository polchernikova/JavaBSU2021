package by.polchernikova.lab04spring.Model;

import lombok.Data;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Classroom {
    public Classroom() {
        students = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            students.add(null);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer number;

    @OneToMany(mappedBy = "classroom", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Student> students;
}
