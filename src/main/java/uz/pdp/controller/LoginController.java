package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.entity.AuthUser;
import uz.pdp.model.enums.AuthRole;
import uz.pdp.repository.impl.db.AuthUserRepositoryImpl;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final AuthUserRepositoryImpl authUserRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login/login";
    }


    @GetMapping("/signup")
    public String showSignUpPage() {
        return "login/signup";
    }

    @PostMapping("/signup")
    public String addUser(@ModelAttribute AuthUser authUser, @RequestParam("role") String role) {
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        authUser.setRole(AuthRole.valueOf(role));
        authUserRepository.save(authUser);
        return "redirect:/login?registered";
    }
}
