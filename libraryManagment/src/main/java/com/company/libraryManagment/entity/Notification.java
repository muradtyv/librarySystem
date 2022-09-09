package com.company.libraryManagment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long notificationId;
    private LocalDate notificationDate;
    private LocalDate validUntilDate;
    private String notificationMessage;

    @ManyToOne
    private User notificationReceiver;

    public Notification (LocalDate notificationDate, LocalDate validUntilDate, String notificationMessage) {
        this.notificationDate = notificationDate;
        this.validUntilDate = validUntilDate;
        this.notificationMessage = notificationMessage;
    }
}
