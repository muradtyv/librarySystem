package com.company.libraryManagment.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String userName;
    private String password;
    private boolean enabled =true;
    private String role = "ROLE_USER";

    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String phoneNumber;

    @OneToMany(mappedBy = "reservedByUser")
    private List<Book> reservedBooks;

    @OneToMany(mappedBy = "theUser")
    private List<Book> books;

    @OneToMany(mappedBy = "notificationReceiver")
    private List<Notification> notifications;
}
