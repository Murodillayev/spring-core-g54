package uz.pdp;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import uz.pdp.config.security.CustomUserDetails;

import java.time.LocalDate;

@Controller
public class HomeController {

    private final AuthUserRepository authUserRepository;

    public HomeController(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @GetMapping({"/home", "/"})
    public String home() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails principal =(CustomUserDetails) authentication.getPrincipal();
            System.out.println(principal);
        }

        return "home";
    }

    @GetMapping("/init")
    public String init() {
        AuthUser authUser = new AuthUser("Azamat", "azamat", "111", "ADMIN");
        authUserRepository.save(authUser);
        return "redirect:/login";
    }

    @GetMapping("/private")
    public String privateHome(@AuthenticationPrincipal CustomUserDetails sessionUser) {
        System.out.println(sessionUser);
        return "home";
    }

    @PostMapping("/post")
    public String post() {
        return "redirect:/home";
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
