package com.company.libraryManagment.service;

import com.company.libraryManagment.entity.User;
import com.company.libraryManagment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
//    public void saveById(Long userId) {
//        User user = userRepository.findById(userId).get();
//        userRepository.save(user);
//    }

    public List<User> getFullName(String firstName, String lastName){
       return userRepository.findByFirstNameAndLastName(firstName,lastName);
    }
    public List<User> getByName(String firstName){
        return userRepository.findByFirstName(firstName);
    }
    public List<User> getByLastName(String lastName){
        return userRepository.findByLastName(lastName);
    }
    public List<User> userSearcher(String firstName, String lastName){
        if(firstName != null && lastName != null) return getFullName(firstName,lastName);
        else if (firstName == null && lastName !=null) return getByLastName(lastName);
        else if (firstName != null && lastName == null) return getByName(firstName);
        else return new ArrayList<User>();
    }
}
