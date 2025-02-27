package com.tch_tech.userservice;

import com.tch_tech.userservice.entity.User;
import com.tch_tech.userservice.entity.UserRole;
import com.tch_tech.userservice.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

//    @Bean
//    CommandLineRunner start(UserServiceImpl userServiceImpl){
//        return args -> {
//            userServiceImpl.addNewUserRole(new UserRole(null , "USER"));
//            userServiceImpl.addNewUserRole(new UserRole(null , "ADMIN"));
//
//            userServiceImpl.addNewUser(new User(null, "FirstName1", "LastName1", "12D2D1", "user1@example.com", new ArrayList<>(), "firstLastName1"));
//            userServiceImpl.addNewUser(new User(null, "Firstadm", "Lastadm", "12D2D1", "admin@example.com", new ArrayList<>(), "AdminUser"));
//
//            userServiceImpl.addRoleToUser("firstLastName1", "USER");
//            userServiceImpl.addRoleToUser("AdminUser", "ADMIN");
//
//
//        };
//    }
}
