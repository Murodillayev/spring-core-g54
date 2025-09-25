package uz.pdp.controller;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {


    @GetMapping
    public String testPage(Model model) {
//        model.addAttribute("username", "admin");
        model.addAttribute("people", List.of(new Person("Muhammad"), new Person("Aziz")));
        model.addAttribute("buttonType", "primary");
        model.addAttribute("role", "admin");
        model.addAttribute("htmlCode", """
                <form>
                  <div class="mb-3" >
                    <label for="exampleInputEmail1" class="form-label">Email address</label>
                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                  </div>
                  <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <input type="password" class="form-control" id="exampleInputPassword1">
                  </div>
                  <div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Check me out</label>
                  </div>
                  <button type="submit" class="btn btn-primary">Submit</button>
                </form>
                """);
        return "test";
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Person {
        private String name;
    }
}
