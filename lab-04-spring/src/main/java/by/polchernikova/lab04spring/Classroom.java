package by.polchernikova.lab04spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer number;

    @OneToMany(mappedBy = "classroom", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<Student> students = new ArrayList<>(30);
}
