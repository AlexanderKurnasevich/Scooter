package by.scooter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResetController {

    @GetMapping("/reset")
    public String showReset() {
        return "reset";
    }
}
