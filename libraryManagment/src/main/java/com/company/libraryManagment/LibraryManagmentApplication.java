package com.company.libraryManagment;

import com.company.libraryManagment.entity.User;
import com.company.libraryManagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
//@RequiredArgsConstructor
public class LibraryManagmentApplication  {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagmentApplication.class, args);

	}
//	@Autowired
//	UserService usService;
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;


//	private final BCryptPasswordEncoder bCryptPasswordEncoder;

//	@Bean
//	CommandLineRunner runner() {
//		return args -> {
//			User user1 = new User("admin2", passwordEncoder.encode("test"), "muradteymurov20002gmail.com", "Admin2", "Teymurov", "H.B.Zerdabi", "+994553892266", "Saatli");
//			user1.setRole("ROLE_ADMIN");
//
//			usService.insert(user1);
//		};
//	}

}
