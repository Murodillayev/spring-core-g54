package uz.pdp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.model.dto.UserDTO;
import uz.pdp.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String UsersPage(Model model){
        List<UserDTO> all = userService.getAll("");
        model.addAttribute("users",all);
        return "users";

    }
}
