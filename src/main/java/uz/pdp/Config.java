package uz.pdp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean("myClass")
    public MyClass myClas(){
        return new MyClass();
    }

    @Bean("oybek")
    public MyBean myBean(){
        return new MyBean();
    }
}
