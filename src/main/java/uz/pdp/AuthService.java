package uz.pdp;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AuthService {

    //    @Autowired
    private AuthValidator validator; // DI


//    @Autowired
    public AuthService(AuthValidator validator) {
        this.validator = validator;
    }

    public void login(String username) {

        System.out.println("validator => " + validator);
        validator.validateLogin();
        System.out.println("Logged in as " + username);
    }


}
