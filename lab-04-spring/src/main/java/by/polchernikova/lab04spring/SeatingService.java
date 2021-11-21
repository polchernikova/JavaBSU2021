package by.polchernikova.lab04spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatingService {
    @Autowired
    StudentRepository students;

    public void makeSeating() {
        students.findAll();
    }
}
