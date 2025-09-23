package uz.pdp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
//@ResponseBody
public class HomeController {


    @GetMapping("/home")
    public String home(Model model) {
        List<String> list = List.of("John", "Tom");
        Date date = new Date();

        model.addAttribute("list", list);
        model.addAttribute("date", date);
        return "index";
    }
}
