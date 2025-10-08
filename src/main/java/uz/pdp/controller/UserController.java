package uz.pdp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.mapper.UserMapper;
import uz.pdp.model.dto.UserDTO;
import uz.pdp.model.entity.AuthUser;
import uz.pdp.repository.impl.db.AuthUserRepositoryImpl;
import uz.pdp.service.UserService;

import java.util.List;

import static uz.pdp.repository.impl.db.AuthUserRepositoryImpl.users;

@Controller
@RequestMapping("/user")

@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    public final UserService userService;
    private final UserMapper userMapper;
    private final AuthUserRepositoryImpl authUserRepository;

    public UserController(UserService userService, UserMapper userMapper, AuthUserRepositoryImpl authUserRepository) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.authUserRepository = authUserRepository;
    }
    @GetMapping
    public String UsersPage(Model model, @RequestParam(name = "search", defaultValue = "") String search ){
        List<UserDTO> all = userService.getAll(search);
//        for (AuthUser u : users) {
//            authUserRepository.save(u);
//        }
        model.addAttribute("users",all);
        return "user/users";

    }

    @GetMapping("/add")
    public String addPage(Model model) {
        List<String> roles = List.of("ADMIN", "SELLER", "MANAGER");
        model.addAttribute("roles",roles);
        model.addAttribute("authUser", new AuthUser());
        return "user/add";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute UserDTO dto) {

        userService.create(dto);
        return "redirect:/user?success=Muvoffaqqiyatli";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView editPage(@PathVariable(name = "id") String id) {
        ModelAndView mav = new ModelAndView("user/edit");
        UserDTO userDTO = userService.get(id);
        List<String> roles = List.of("ADMIN", "SELLER", "MANAGER");
        mav.addObject("user",userDTO);
        mav.addObject("roles",roles);
        return mav;
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute UserDTO dto, @RequestParam(name = "id") String id) {
        userService.update(dto, id);
        return "redirect:/user?success=O'zgartirildi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") String id) {
        userService.delete(id);
        return "redirect:/user?success=O'chirildi";
    }

}
