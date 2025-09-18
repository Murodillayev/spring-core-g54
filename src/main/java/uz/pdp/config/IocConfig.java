package uz.pdp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import uz.pdp.dao.AuthDao;

@Configuration
@ComponentScan("uz.pdp")
public class IocConfig {


    @Bean
//    @Scope("prototype")
    public AuthDao authDao(){
        return new AuthDao();
    }
}
