package repository;

import model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void findAllBook() {
        for(Book book: books) {
            System.out.println(book);
        }
    }
}
