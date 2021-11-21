package by.polchernikova.lab04spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DisplayController {
    @Autowired
    StudentRepository students;

    @GetMapping("/seating")
    public String displaySeating() {
        return "displayer";
    }

     // потом объединить эти два в один как-нибудь по-умному
    @GetMapping("/searchbyname")
    public String displaySeatByName(Model model,
                                    @RequestParam(value="query", required = true) String query) {
        Iterable<Student> namesakers = students.findAllByName(query);
        model.addAttribute("students", namesakers);
        return "displayer";
    }

    @GetMapping("/searchbyschool")
    public String displaySeatBySchool(Model model,
                                      @RequestParam(value="query", required = true) String query) {
        Iterable<Student> schoolmates = students.findAllBySchool(query);
        model.addAttribute("students", schoolmates);
        return "displayer";
    }
}
