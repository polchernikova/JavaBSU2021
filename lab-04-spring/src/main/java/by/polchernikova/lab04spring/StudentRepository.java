package by.polchernikova.lab04spring;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Iterable<Student> findAllByName(String name);
    Iterable<Student> findAllBySchool(String school);
}
