package com.jyl.spring.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class SpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringSecurityJwtApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }

}
