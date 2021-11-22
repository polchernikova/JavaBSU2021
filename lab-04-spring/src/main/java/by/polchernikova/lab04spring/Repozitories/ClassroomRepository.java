package by.polchernikova.lab04spring.Repozitories;

import by.polchernikova.lab04spring.Model.Classroom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends CrudRepository<Classroom, Long> {
    Classroom findByNumber(Integer number);
}
