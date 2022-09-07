package com.company.libraryManagment.controller;

import com.company.libraryManagment.entity.User;
import com.company.libraryManagment.security.CurrentUserFinder;
import com.company.libraryManagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	UserService usService;
	
	@Autowired
	CurrentUserFinder currentUserFinder;
	
	@GetMapping
	public String adminHome(Model model) {
		User currentUser = currentUserFinder.getCurrentUser();
		model.addAttribute("currentUser", currentUser);
		return "admin/admin-home.html";
	}
	
//	@GetMapping(value="/manageaccounts")
//	public String manageAuthorities(@RequestParam (required = false) String firstName,
//									@RequestParam (required = false) String lastName,
//									Model model) {
//		List<User> users = usService.userSearcher(firstName, lastName);
//
//		model.addAttribute("users", users);
//		model.addAttribute("firstName", firstName);
//		model.addAttribute("lastName", lastName);
//		return "admin/admin-manage-accounts.html";
//	}
//
//	@GetMapping(value="/manageaccount")
//	public String manageAccount(@RequestParam Long userId,
//								Model model) {
//
//		User user = usService.findById(userId);
//		model.addAttribute("user", user);
//		return "admin/admin-manage-account.html";
//	}
//
//	@PutMapping(value="/confirmaccountsettings")
//	public String confirmAccountChanges(@RequestParam boolean accStatus,
//										@RequestParam String role,
//										@RequestParam Long userId,
//										Model model) {
//		model.addAttribute("role", role);
//		model.addAttribute("accStatus", accStatus);
//		model.addAttribute("user", usService.findById(userId));
//		return "admin/admin-confirm-account-settings.html";
//	}
//
//	@PutMapping(value="/saveaccountsettings")
//	public String saveAccountSettings(@RequestParam boolean accStatus,
//									  @RequestParam String role,
//									  @RequestParam Long userId) {
//		User user = usService.findById(userId);
//		user.setRole(role);
//		user.setEnabled(accStatus);
//		usService.save(user);
//		return "redirect:/admin/accountsettingssaved";
//	}
//
//	@GetMapping(value="/accountsettingssaved")
//	public String accountSettingsSaved() {
//		return "admin/admin-account-settings-saved.html";
//	}
}
