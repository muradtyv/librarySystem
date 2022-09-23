package com.company.libraryManagment;

import com.company.libraryManagment.entity.Book;
import com.company.libraryManagment.entity.User;
import com.company.libraryManagment.service.BookService;
import com.company.libraryManagment.service.UserService;
import com.company.libraryManagment.utill.MidnightApplicationRefresh;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@RequiredArgsConstructor
public class LibraryManagmentApplication  {

	private final MidnightApplicationRefresh midnightApplicationRefresh;

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagmentApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			midnightApplicationRefresh.midnightApplicationRefresher();
		};
	}
}
