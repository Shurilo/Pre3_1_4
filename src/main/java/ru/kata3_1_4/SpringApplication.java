package ru.kata3_1_4;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.kata3_1_4.service.DBInit;

import java.io.IOException;

@SpringBootApplication
public class SpringApplication {

    public static void main(String[] args) throws IOException {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }
    @Bean(initMethod="addDefaultRole")
    public DBInit getPreRoleBean() {
        return new DBInit();
    }

    @Bean(initMethod="addDefaultUsers")
    public DBInit getPreUserBean() {
        return new DBInit();
    }

}