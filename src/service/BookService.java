package service;

import model.Book;
import repository.BookDAOInterface;
import repository.Implementation.BookDAOInterfaceImpl;

public class BookService {

    BookDAOInterface bookRepo = new BookDAOInterfaceImpl();

    public void addNewBook(String title, String authorName, boolean availableBool, float price) {

        Book book = new Book(title, authorName, availableBool, price);

        bookRepo.addBook(book);
    }

    public void updateBook(int id, Book book) {
        System.out.println("Enter title of the book: ");
        bookRepo.updateBook(id, book);
    }

    public void findAllBook() {
        bookRepo.findAllBook();
    }

    public void deleteBook(int id){
        bookRepo.deleteBook(id);
    }
}
