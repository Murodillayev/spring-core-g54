package uz.pdp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final AuthUserRepository authUserRepository;

    public HomeController(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/init")
    public String init() {
        AuthUser authUser = new AuthUser("Azamat","azamat","111","ADMIN");
        authUserRepository.save(authUser);
        return "redirect:/login";
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin ")
    public String admin() {
        return "adminPage";
    }

    @GetMapping("/userPage")
    public String userPage() {
        return "userPage";
    }
}
