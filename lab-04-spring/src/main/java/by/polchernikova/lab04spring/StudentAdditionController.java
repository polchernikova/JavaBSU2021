package by.polchernikova.lab04spring;

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
    SeatingService seatingService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("students", students.findAll());
        return "index";
    }

    @GetMapping("/addstudent")
    public String addStudentPage() {
        return "addStudent";
    }

    @PostMapping(value = "/addstudent", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addStudent(Student student) {
        students.save(student);
        return "addStudent";
    }
}
