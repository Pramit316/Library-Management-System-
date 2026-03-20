package repository.Implementation;
import configuration.DBConnect;
import model.Book;
import repository.BookDAOInterface;

import java.sql.*;

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
    public void updateBook(int id, Book book) {
        String sql = "UPDATE book SET title = ?, author = ?, available = ?,  price = ? WHERE id=?";

        Connection con = connection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(5, id);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setBoolean(3, book.isAvailable());
            ps.setFloat(4, book.getPrice());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findAllBook() {
        String sql = "SELECT * FROM BOOK";

        Connection con = connection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs =  ps.executeQuery();
            System.out.println("------List of all the Books!-------");
            while(rs.next()){
                System.out.println("\nID: " + rs.getInt("id") + "\nTitle: " + rs.getString("title") + "\nAuthor: " + rs.getString("author") + "\nAvailable: " + rs.getBoolean("available") + "\nPrice: Rs " + rs.getFloat("price") + "\nQuantity: " + rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBook(int id) {
        String sql = "DELETE FROM BOOK WHERE id= ?";

        Connection con = connection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getBookByID(int id) {
        String sql = "SELECT * FROM book WHERE id= ?";

        Connection con = connection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            ps.setInt(1, id);
            if (rs.next()) {
                System.out.println(
                        "\nID: " + rs.getInt("id") +
                        "\nTitle: " + rs.getString("title") +
                        "\nAuthor: " + rs.getString("author") +
                        "\nAvailable: " + rs.getBoolean("available") +
                        "\nPrice: Rs " + rs.getDouble("price") +
                        "\nQuantity: " + rs.getInt("quantity")
                );
            } else {
                System.out.println("No book found with ID: " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
