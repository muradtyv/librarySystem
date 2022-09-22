package com.company.libraryManagment.controller;

import com.company.libraryManagment.entity.User;
import com.company.libraryManagment.repository.UserRepository;
import com.company.libraryManagment.security.CurrentUserFinder;
import com.company.libraryManagment.security.UserPrincipalDetailsService;
import com.company.libraryManagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SecurityController {

	private final PasswordEncoder pwEncoder;

	private final UserService accService;

	private final UserPrincipalDetailsService userPrincipalDetailsService;



	@GetMapping(value="/login")
	public String login() {
		return "security/login.html";
	}
	
	@GetMapping(value="/logout")
	public String logout() {
		return "security/logout.html";
	}
	
	@GetMapping(value="/register")
	public String register(Model model) {
		model.addAttribute("newAccount", new User());
		return "security/register.html";
	}
	
	@PostMapping(value="/register/save")
	public String saveNewAccount(User account,Model model) {

		Optional<User> userOptional = accService.getByUsername(account);

		if(userOptional.isPresent()){
			model.addAttribute("existUsername", userOptional.get().getUserName());
			return "/security/account-exist-already.html";
		}

		account.setPassword(pwEncoder.encode(account.getPassword()));
		accService.insert(account);
		return "redirect:/register/accountcreated";

	}
	
	@GetMapping(value="/register/accountcreated")
	public String accountCreated() {
		return "security/account-created.html";
	}	
}
