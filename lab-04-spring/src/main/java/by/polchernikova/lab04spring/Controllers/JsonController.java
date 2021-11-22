package by.polchernikova.lab04spring.Controllers;

import by.polchernikova.lab04spring.Model.Classroom;
import by.polchernikova.lab04spring.Repozitories.ClassroomRepository;
import by.polchernikova.lab04spring.Repozitories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JsonController {
    @Autowired
    ClassroomRepository classrooms;

    @GetMapping("/json")
    public @ResponseBody
    Iterable<Classroom> getStudentsJson() {
        return classrooms.findAll();
    }


}
