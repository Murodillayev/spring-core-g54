package uz.pdp;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TodoService {

    private final TodoValidator validator;

    public TodoService(@Qualifier("todoValidator1") TodoValidator validator) {
        this.validator = validator;
    }

    public String create(String title) {
        System.out.println("Creating => " + title);
        return title;

    }

    public String update(String title) {
        System.out.println("Updating => " + title);
        return title;
    }
}
