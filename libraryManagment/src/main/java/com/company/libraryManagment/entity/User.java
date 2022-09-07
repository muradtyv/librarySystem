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

    @Column(name = "username")
    private String userName;
    private String password;
    private boolean enabled = true;
    private String role = "";

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

    public User(String userName, String password, String email, String firstName,
                String lastName, String address, String phoneNumber, String city) {

        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.city= city;
    }
}
