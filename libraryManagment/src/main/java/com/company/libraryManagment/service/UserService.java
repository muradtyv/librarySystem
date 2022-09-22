package com.company.libraryManagment.service;

import com.company.libraryManagment.entity.User;
import com.company.libraryManagment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User getById(long id){
        return userRepository.findById(id).get();
    }

    public void insert(User user){
        userRepository.save(user);
    }
    public void saveById(Long userId) {
        User user = userRepository.findById(userId).get();
        userRepository.save(user);
    }

    public List<User> userSearcher(String firstName, String lastName){
        if (firstName != null && lastName != null) return getByFullName(firstName, lastName);
        else if (firstName == null && lastName != null) return getByLastName(lastName);
        else if (firstName != null && lastName == null) return getByFirstName(firstName);
        return new ArrayList<User>();
    }

    public List<User> getByFirstName(String firstName){
        List<User> users = new ArrayList<User>();
        for (User user : userRepository.findAll()) {
            if (user.getFirstName().toLowerCase().contains(firstName.toLowerCase())) {
                users.add(user);
            }
        }
        return users;
    }

    public List<User> getByLastName(String lastName){
        List<User> users = new ArrayList<User>();
        for (User user : userRepository.findAll()) {
            if(user.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
                users.add(user);
            }
        }
        return users;
    }

    public List<User> getByFullName(String firstName, String lastName){
        List<User> users = new ArrayList<User>();
        for (User user : userRepository.findAll()) {
            if (user.getFirstName().toLowerCase().contains(firstName.toLowerCase()) &&
                    user.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
                users.add(user);
            }
        }
        return users;

    }

    public Optional<User> getByUsername(User user){
        return userRepository.findByUserName(user.getUserName());
    }

}
