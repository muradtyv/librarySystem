package com.company.libraryManagment.controller;

import com.company.libraryManagment.entity.User;
import com.company.libraryManagment.security.CurrentUserFinder;
import com.company.libraryManagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(value="/admin")
public class AdminController {


	private final UserService userService;



	private final CurrentUserFinder currentUserFinder;

	@GetMapping
	public String adminHome(Model model) {
		User currentUser = currentUserFinder.getCurrentUser();
		model.addAttribute("currentUser", currentUser);
		return "admin/admin-home.html";
	}

	@GetMapping("/manageaccounts")
	public String manageAuthorities(@RequestParam(required = false) String firstName,
									@RequestParam (required = false) String lastName,
									Model model) {
		List<User> users = userService.userSearcher(firstName, lastName);


		model.addAttribute("users", users);
		model.addAttribute("firstName", firstName);
		model.addAttribute("lastName", lastName);

		return "admin/admin-manage-accounts.html";
	}

	@GetMapping(value="/manageaccount")
	public String manageAccount(@RequestParam Long userId,
								Model model) {

		User user = userService.getById(userId);
		model.addAttribute("user", user);
		return "admin/admin-manage-account.html";
	}

	@PutMapping(value="/confirmaccountsettings")
	public String confirmAccountChanges(@RequestParam boolean accStatus,
										@RequestParam String role,
										@RequestParam Long userId,
										Model model) {
		model.addAttribute("role", role);
		model.addAttribute("accStatus", accStatus);
		model.addAttribute("user", userService.getById(userId));
		return "admin/admin-confirm-account-settings.html";
	}

	@PutMapping(value="/saveaccountsettings")
	public String saveAccountSettings(@RequestParam boolean accStatus,
									  @RequestParam String role,
									  @RequestParam Long userId) {
		User user = userService.getById(userId);
		user.setRole(role);
		user.setEnabled(accStatus);
		userService.insert(user);
		return "redirect:/admin/accountsettingssaved";
	}

	@GetMapping(value="/accountsettingssaved")
	public String accountSettingsSaved() {
		return "admin/admin-account-settings-saved.html";
	}
}
