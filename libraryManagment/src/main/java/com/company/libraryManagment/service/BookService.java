package com.company.libraryManagment.service;

import com.company.libraryManagment.entity.Book;
import com.company.libraryManagment.entity.User;
import com.company.libraryManagment.repository.BookRepository;
import com.company.libraryManagment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    public void save(Book book){
        bookRepository.save(book);
    }

    public void saveById(Long bookId) {
        bookRepository.save(bookRepository.findById(bookId).get());
    }

    public List<Book>getAll(){
        return bookRepository.findAll();
    }
    public Book getById(long id){
        return bookRepository.findById(id).get();
    }

    public void delete(long id){
        bookRepository.deleteById(id);
    }

    public List<Book> getByTitle(String title){
        return bookRepository.findByTitle(title);
    }
    public List<Book> getByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }
    public List<Book> getByTitleAndAuthor(String title, String author){
        return bookRepository.findByTitleAndAuthor(title,author);
    }

    public List<Book> searchBooks(String title, String author){
        if(title != null && author != null ) return getByTitleAndAuthor(title,author);
        else if( title == null && author != null) return getByAuthor(author);
        else if( title != null && author == null) return getByTitle(title);
        else return new ArrayList<Book>();
    }
    public List<Book> getUnprocessedBookReservations(){
        List<Book> unprocessedBooks =new ArrayList<>();
        for(Book book : bookRepository.findAll()){
            if(book.getReservedByUser() != null && book.getReadyForPickup() == false){
                unprocessedBooks.add(book);
            }
        }
        return unprocessedBooks;
    }
    public List<Book> getProcessedBookReservations(){
        List<Book> processedBooks = new ArrayList<>();
        for(Book book : bookRepository.findAll()){
            if(book.getReservedByUser() != null && book.getReadyForPickup() == true){
                processedBooks.add(book);
            }
        }
        return processedBooks;
    }
//    convert?
    public List<Book> convertIdsCollectionToBooksList(Collection<Long> bookIds){
        List<Book> books = new ArrayList<>();
        for (Long bookId : bookIds){
            books.add(bookRepository.findById(bookId).get());
        }
        return books;
    }

    public void removeCurrentUserOfMultipleBooks(List<Book> books){
        for(Book book: books) {
            removeCurrentUserOfBook(book);
        }
    }

    public void removeCurrentUserOfBook(Book book){
        User currentUser = book.getTheUser();
        List<Book> currentUserBooks = currentUser.getBooks();
        for(int i =0; i < currentUserBooks.size(); i++){
            if(currentUserBooks.get(i).getBookId() == book.getBookId()){
                currentUser.getBooks().remove(i);
                break;
            }

        }
        userRepository.save(currentUser);
        book.setTheUser(null);
        book.setReturnDate(null);
        book.setTimeExtended(0);
        bookRepository.save(book);
    }

    public void removeReservation(Book book){
        User reservedUser = book.getReservedByUser();
        List<Book> reservedUserBooks = reservedUser.getReservedBooks();
        for(int i =0; i< reservedUserBooks.size();i++){
            if(reservedUserBooks.get(i).getBookId() == book.getBookId()){
                reservedUser.getReservedBooks().remove(i);
                break;
            }
        }
        userRepository.save(reservedUser);
        book.setTheUser(null);
        book.setReturnDate(null);
        book.setTimeExtended(0);
        bookRepository.save(book);
    }
//    save order?
    public void saveBookOrder(Collection<Long> selectedBookIds, User user){
        for (Long bookId : selectedBookIds) {
            Book book = bookRepository.findById(bookId).get();
            book.setReturnDate(LocalDate.now().plusDays(20));
            book.setStartReservationDate(null);
            book.setEndReservationDate(null);
            book.setReservedByUser(null);
            book.setReadyForPickup(false);
            book.setTheUser(user);
            bookRepository.save(book);
            userRepository.save(user);
        }
    }
}
