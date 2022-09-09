package com.company.libraryManagment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;

    private String title;
    private String author;
    private int releaseYear;
    private LocalDate returnDate = null;
    private LocalDate startReservationDate;
    private LocalDate endReservationDate;
    private int timeExtended=0;
    private boolean readyForPickup = false;

    @ManyToOne
    private User reservedByUser;

    @ManyToOne
    private User theUser;


    public Book(String title, String author, int releaseYear, int edition) {
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
    }

    public boolean getReadyForPickup() {
        return readyForPickup;
    }

    public void setReadyForPickup(boolean readyForPickup) {
        this.readyForPickup = readyForPickup;
    }
}
