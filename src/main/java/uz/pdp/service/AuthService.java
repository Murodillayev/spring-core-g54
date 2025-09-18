package uz.pdp.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uz.pdp.mapper.AuthMapper;
import uz.pdp.validator.AuthValidator;

//@Component
//@Scope("prototype")
//@Lazy
public class AuthService {

    //    @Autowired
    private AuthValidator validator;
    private AuthMapper mapper;// DI


    //    @Autowired
    public AuthService(AuthValidator validator, AuthMapper mapper) {
        System.out.println("AuthService bean initialized");
        this.validator = validator;
        this.mapper = mapper;
    }

    public void login(String username) {

        System.out.println("validator => " + validator);
        validator.validateLogin();
        System.out.println("Logged in as " + username);
    }


}
