package service;

import model.Book;
import repository.BookDAOInterface;
import repository.Implementation.BookDAOInterfaceImpl;

public class BookService {

    BookDAOInterface bookRepo = new BookDAOInterfaceImpl();

    public void addNewBook(String title, String authorName, boolean availableBool, float price, int quantity) {

        Book book = new Book(title, authorName, availableBool, price, quantity);

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

    public void borrowBookById(int userId, int bookId) {
        bookRepo.borrowBookById(userId,bookId);
    }

    public void myBooks(int id) {
        bookRepo.myBorrowedBooks(id);
    }
}
