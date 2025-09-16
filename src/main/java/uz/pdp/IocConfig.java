package uz.pdp;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IocConfig {

    @Bean
    public AuthService authService() {
        return new AuthService(authValidator(), authMapper());
    }

    @Bean
    public AuthDao authDao() {
        return new AuthDao();
    }

    @Bean
    public AuthValidator authValidator() {
        return new AuthValidator();
    }

    @Bean
    public AuthMapper authMapper() {
        return new AuthMapper();
    }

}
