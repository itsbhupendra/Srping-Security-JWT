package com.example.springsecurity;

import com.example.springsecurity.domain.AppUser;
import com.example.springsecurity.domain.Roles;
import com.example.springsecurity.service.AppuserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Bean
    CommandLineRunner run(AppuserService appuserService){
        return args -> {
            appuserService.saveRoles(new Roles(null,"ROLE_USER"));
            appuserService.saveRoles(new Roles(null,"ROLE_MANAGER"));
            appuserService.saveRoles(new Roles(null,"ROLE_ADMIN"));
            appuserService.saveRoles(new Roles(null,"ROLE_SUPER_ADMIN"));

            appuserService.saveAppUser(new AppUser(null,"John","john","1234",new ArrayList<>()));
            appuserService.saveAppUser(new AppUser(null,"ashu","ash","1234",new ArrayList<>()));
            appuserService.saveAppUser(new AppUser(null,"Bhupendra","bhupendra","1234",new ArrayList<>()));

            appuserService.addRolesToUser("john","ROLE_MANAGER");
            appuserService.addRolesToUser("ash","ROLE_SUPER_ADMIN");
            appuserService.addRolesToUser("bhupendra","ROLE_USER");
        };
    }
}
