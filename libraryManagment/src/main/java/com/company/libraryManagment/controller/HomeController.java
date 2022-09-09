package com.company.libraryManagment.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> role =new ArrayList<>();

        role =userDetails.getAuthorities();
        if(role.toString().equals("[ROLE_ADMIN]")){
            return "redirect:/admin";
        }else if(role.toString().equals("[ROLE_EMPLOYEE]")){
            return "redirect:/employee";
        }else{
            return "redirect:/user";
        }

    }
}
