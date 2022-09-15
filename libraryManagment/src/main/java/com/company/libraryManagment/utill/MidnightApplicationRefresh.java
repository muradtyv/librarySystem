package com.company.libraryManagment.utill;

import com.company.libraryManagment.entity.Book;
import com.company.libraryManagment.entity.Notification;
import com.company.libraryManagment.entity.User;
import com.company.libraryManagment.service.BookService;
import com.company.libraryManagment.service.NotificationService;
import com.company.libraryManagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MidnightApplicationRefresh {

	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	NotificationService notifService;
	
		//Removes overdue reservations and notifications.
		public void midnightApplicationRefresher() {
			
			for (Book book : bookService.getAll()) {
				if (book.getEndReservationDate() != null) {
					if (book.getEndReservationDate().compareTo(LocalDate.now()) == -1) {
						if (book.getReservedByUser() != null) {
							User user = book.getReservedByUser();
							book.setReservedByUser(null);
							userService.insert(user);
						}
						book.setStartReservationDate(null);
						book.setEndReservationDate(null);	
						book.setReadyForPickup(false);
						bookService.save(book);
					}	
				}
			}
	
			for (Notification notif : notifService.getAll()) {
				if (notif.getValidUntilDate() != null) {
					if (notif.getValidUntilDate().compareTo(LocalDate.now()) == -1) {
						notifService.delete(notif.getNotificationId());
					}
				}	
			}
		}
}
