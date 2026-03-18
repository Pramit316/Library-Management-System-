package service;

import model.Book;
import repository.BookRepository;

import java.util.Scanner;

public class BookService {

    BookRepository bookRepository = new BookRepository();
    Scanner sc = new Scanner(System.in);

    public void addNewBook(String title, String authorName, boolean availableBool, float price) {

        Book book = new Book(title, authorName, availableBool, price);

        bookRepository.addBook(book);
    }

    public void findAllBook() {
        bookRepository.findAllBook();
    }
}
