package repository;

import model.Book;

public interface BookDAOInterface {

    void addBook(Book book);
    void removeBook(int id);
    void findAllBook();
    void deleteBook(int id);
}
