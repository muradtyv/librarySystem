package com.company.libraryManagment.utill;

import com.company.libraryManagment.entity.Book;
import com.company.libraryManagment.security.CurrentUserFinder;
import com.company.libraryManagment.service.BookService;
import com.company.libraryManagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class DateTracker {

    @Autowired
    BookService bookService;

    @Autowired
    CurrentUserFinder currentUserFinder;

    @Autowired
    UserService userService;

    private LocalDate now =LocalDate.now();


//    public LocalDate getNow(){
//        return this.now;
//    }

    public long differenceDays(LocalDate date){
        return ChronoUnit.DAYS.between(date, LocalDate.now());
    }

    public long daysTooLate(LocalDate date){
        long dayDifference = ChronoUnit.DAYS.between(date,LocalDate.now());
        if(dayDifference > 0){
            return dayDifference;
        }
        return 0;
    }

    public String getReservationDatesInString(Book book){

        String reservationDatesInString ="";

        LocalDate firstAvailableDate;

        if(book.getReturnDate() == null){
            reservationDatesInString += LocalDate.now().toString() + " / ";
            firstAvailableDate = LocalDate.now();
        }else{
            if(book.getReturnDate().compareTo(LocalDate.now()) < 0){
                reservationDatesInString +=LocalDate.now().toString() + " / ";
                firstAvailableDate = LocalDate.now();
            }else{
                reservationDatesInString += book.getReturnDate().plusDays(1);
                firstAvailableDate = book.getReturnDate().plusDays(1);
            }
        }
        reservationDatesInString +=firstAvailableDate.plusDays(7).toString();

        return reservationDatesInString;
    }

    public void setReservationDatesAndReservedByCurrentUserForMultipleBooks(Collection<Long> bookIdList){
        for(Long bookId: bookIdList) setBookReservationDatesAndReservedByCurrentUser(bookId);
    }
    public void setBookReservationDatesAndReservedByCurrentUser(Long bookId){
        Book book = bookService.getById(bookId);

        LocalDate startReservationDate = null;

        if(book.getReturnDate() == null){
            startReservationDate = LocalDate.now();
        }else{
            if(book.getReturnDate().compareTo(LocalDate.now()) < 0){
                startReservationDate = LocalDate.now();
            }else{
                startReservationDate =book.getReturnDate().plusDays(1);
            }
        }
        LocalDate endReservationDate = startReservationDate.plusDays(7);
        book.setStartReservationDate(startReservationDate);
        book.setEndReservationDate(endReservationDate);
        book.setReservedByUser(userService.getById(currentUserFinder.getCurrentUserId()));
        bookService.save(book);
        userService.insert(currentUserFinder.getCurrentUser());
    }

    public int getWeeksToExtendReturnDate(Book book){
        long daysTooLate =daysTooLate(book.getReturnDate());

        int weeksToExtend;

        if(daysTooLate <= 7){
            weeksToExtend = 1;
        }else if(daysTooLate > 7 && daysTooLate <= 14){
            weeksToExtend =2;
        }else{
            weeksToExtend =3;
        }
        return weeksToExtend;
    }

    public Map<Book, String> listedBookReservations(Collection<Long> bookIdList){
        Map<Book, String> listedBookReservations= new LinkedHashMap<Book,String>();

        for(Long bookId: bookIdList){
            Book reservedBookObject =bookService.getById(bookId);
            String reservationDates = getReservationDatesInString(reservedBookObject);
            listedBookReservations.put(reservedBookObject,reservationDates);
        }

        return listedBookReservations;
    }
}
