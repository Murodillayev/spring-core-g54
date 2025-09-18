package uz.pdp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class DataSourceConfig {

    @Value("${database.username}")
    String dbUsername;

    @Value("${database.password}")
    String dbPassword;

    @Bean
    public DataSource getDataSource() {

        System.out.println(dbUsername);
        System.out.println(dbPassword);
        return null;
    }

}
