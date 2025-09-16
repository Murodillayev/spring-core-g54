package uz.pdp;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import uz.pdp.config.IocConfig;
import uz.pdp.service.AuthService;

public class Main {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(IocConfig.class);
        AuthService authService = beanFactory.getBean(AuthService.class);
//        authService.login("admin", "admin");

        // Uy ishi
        // MyIOC ioc = new MyIoc();
        // ioc.register(AuthService.class);
        // AuthService authService = ioc.getBean(AuthService.class)


         // MyIOC ioc = new MyIoc();
         // ico.componentScan("uz.pdp");
         // AuthService authService = ioc.getBean(AuthService.class)
        // Custom Annotation => @MyComponent


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