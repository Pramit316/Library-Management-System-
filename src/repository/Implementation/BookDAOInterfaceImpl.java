package repository.Implementation;
import configuration.DBConnect;
import model.Book;
import repository.BookDAOInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDAOInterfaceImpl implements BookDAOInterface {

    private final DBConnect connection = new DBConnect();

    @Override
    public void addBook(Book book) {
        String sql = "INSERT INTO book (title, author, available, price) values (?,?,?,?)";

        Connection con = connection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setBoolean(3, book.isAvailable());
            ps.setFloat(4, book.getPrice());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQL Exception!! " + e);
        }
    }

    @Override
    public void removeBook(int id) {

    }

    @Override
    public void findAllBook() {

    }

    @Override
    public void deleteBook(int id) {

    }
}
