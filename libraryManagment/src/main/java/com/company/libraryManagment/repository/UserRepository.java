package com.company.libraryManagment.repository;

import com.company.libraryManagment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    User findByUserName(String userName);

    Optional<User> findByUserName(String username);

//    @Query(value = "select a from user a WHERE a.first_name =?1")
//    List<User> findByFirstName(String firstName);
//
//    List<User> findByLastName(String lastName);
//
//    List<User> findByFirstNameAndLastName(String firstName, String lastName);



}
