package by.polchernikova.lab04spring;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends CrudRepository<Classroom, Long> {
}
