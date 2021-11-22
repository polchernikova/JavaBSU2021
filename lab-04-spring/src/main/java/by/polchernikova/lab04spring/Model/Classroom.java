package by.polchernikova.lab04spring.Model;

import lombok.Data;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Classroom {
    public Classroom() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer number;
}
