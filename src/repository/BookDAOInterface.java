package repository;

import model.Book;

public interface BookDAOInterface {

    void addBook(Book book);
    void updateBook(int id, Book book);
    void findAllBook();
    void deleteBook(int id);
    void getBookByID(int id);


    boolean borrowBookById(int userId, int bookId);

    void myBorrowedBooks(int id);
}
