package uz.pdp;

import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class HomeController {


    private final MessageSource messageSource;

    public HomeController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping
    public String index() {

        String message = messageSource.getMessage("hello", null, Locale.ENGLISH);
        System.out.println(message);
        return "index";
    }
}
