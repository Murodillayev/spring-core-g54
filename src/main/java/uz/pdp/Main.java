package uz.pdp;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(IocConfig.class);
        AuthService authService = beanFactory.getBean(AuthService.class);
//        authService.login("admin", "admin");


        AuthService authService2 = beanFactory.getBean(AuthService.class);
        AuthService authService3 = beanFactory.getBean(AuthService.class);

//        System.out.println(authService);
//        System.out.println(authService2);
//        System.out.println(authService3);


        authService.login("admin");
        authService2.login("admin");
        authService3.login("admin");
    }
}