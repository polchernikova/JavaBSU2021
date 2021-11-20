package by.polchernikova.lab04spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class DisplayController {
    @GetMapping("/seating")
    public String displaySeating() {
        return "tablichka";
    }
}
