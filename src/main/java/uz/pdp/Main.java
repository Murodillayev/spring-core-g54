package uz.pdp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        // has permission 2
        // todo create 1
        // log about create 2
        // throw error. hasError 2

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TodoService service = context.getBean(TodoService.class);
        String newTodo = service.create("Uxlash");
        String updated = service.update("Yiglash");

    }
}