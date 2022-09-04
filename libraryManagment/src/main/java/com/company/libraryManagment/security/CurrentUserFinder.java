package com.company.libraryManagment.security;

import com.company.libraryManagment.entity.User;
import com.company.libraryManagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserFinder {

    private final UserService userService;

    public Long getCurrentUserId(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        long userId = -1;
        for(User user: userService.getAll()){
            if(user.getUserName().equals(username)){
                userId = user.getUserId();
                break;
            }
        }
        return userId;
    }
    public User getCurrentUser(){
        User currentUser = userService.getById(getCurrentUserId());
        return currentUser;
    }
}
