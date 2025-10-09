package uz.pdp.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfiguration(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                auth -> {
                    auth.requestMatchers(
                                    new AntPathRequestMatcher("/login"),
                                    )
                            .permitAll()
//                            .requestMatchers("/admin")
//                            .hasRole("ADMIN")
                            .anyRequest()
                            .authenticated();
                }
        );
        http.userDetailsService(userDetailsService);
        http.formLogin(formLoginConfigurer -> {
            formLoginConfigurer.loginPage("/login");
            formLoginConfigurer.usernameParameter("usr");
            formLoginConfigurer.passwordParameter("psw");
            formLoginConfigurer.successForwardUrl("/");
        });
        return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder instance = NoOpPasswordEncoder.getInstance();
        return instance;
    }

//    @Bean
//    public UserDetailsService getUserDetailsService() {
//
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails);
//    }


}
