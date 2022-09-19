package com.company.libraryManagment.controller;

import com.company.libraryManagment.entity.User;
import com.company.libraryManagment.security.CurrentUserFinder;
import com.company.libraryManagment.security.UserPrincipalDetailsService;
import com.company.libraryManagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SecurityController {

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
	public String saveNewAccount(User account ,Model model) {

		UserDetails userDetails = userPrincipalDetailsService.loadUserByUsername(account.getUserName());

		String username = userDetails.getUsername();

		if(username != null){
			model.addAttribute("existUsername", username);
			return "/security/account-exist-already.html";
		}
		account.setPassword(account.getPassword());
		accService.insert(account);
		return "redirect:/register/accountcreated";
	}
	
	@GetMapping(value="/register/accountcreated")
	public String accountCreated() {
		return "security/account-created.html";
	}	
}
