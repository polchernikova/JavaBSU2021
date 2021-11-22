package by.polchernikova.lab04spring.Controllers;

import by.polchernikova.lab04spring.Repozitories.ClassroomRepository;
import by.polchernikova.lab04spring.Model.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClassroomAdditionController {
    @Autowired
    ClassroomRepository classroomRepository;

    @GetMapping("/addclassroom")
    public String addClassroomPage() {
        return "addClassroom";
    }

    @PostMapping(value = "/addclassroom", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addClassroom(Classroom classroom) {
        classroomRepository.save(classroom);
        return "addClassroom";
    }
}
