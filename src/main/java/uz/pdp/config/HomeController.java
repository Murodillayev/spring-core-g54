package uz.pdp.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "adminPage";
    }

    @GetMapping("/userPage")
    public String userPage() {
        return "userPage";
    }
}
