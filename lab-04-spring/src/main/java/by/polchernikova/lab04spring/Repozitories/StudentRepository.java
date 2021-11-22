package by.polchernikova.lab04spring.Repozitories;

import by.polchernikova.lab04spring.Model.Classroom;
import by.polchernikova.lab04spring.Model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Iterable<Student> findAllByName(String name);
    Iterable<Student> findAllBySchool(String school);
    Iterable<Student> findAllByClassroom(Classroom classroom);
}
