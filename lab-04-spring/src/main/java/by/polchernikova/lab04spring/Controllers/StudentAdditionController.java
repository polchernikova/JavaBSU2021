package by.polchernikova.lab04spring.Controllers;

import by.polchernikova.lab04spring.Repozitories.ClassroomRepository;
import by.polchernikova.lab04spring.SeatingService;
import by.polchernikova.lab04spring.Model.Student;
import by.polchernikova.lab04spring.Repozitories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentAdditionController {
    @Autowired
    StudentRepository students;

    @Autowired
    ClassroomRepository classrooms;

    @Autowired
    SeatingService seatingService;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("students", students.findAll());
        model.addAttribute("classrooms", seatingService.prepareDataForDisplaying());
        return "index";
    }

    @GetMapping("/addstudent")
    public String addStudentPage() {
        return "addStudent";
    }

    @PostMapping(value = "/addstudent", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addStudent(Student student) {
        students.save(student);
        seatingService.makeSeating();
        return "addStudent";
    }
}
