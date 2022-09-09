package com.company.libraryManagment;

import com.company.libraryManagment.entity.User;
import com.company.libraryManagment.repository.UserRepository;
import com.company.libraryManagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class Main {
    @Autowired
    public static UserService userService;
    @Autowired
	private static PasswordEncoder passwordEncoder;

//    public static List<User> users = userService.getByLastName("teymurov");

//public static List<User> users = userService.getFullName("Murad", "Teymurov");


    public static void main(String[] args) {
//       users.forEach(user -> System.out.println(user));
    }
}
