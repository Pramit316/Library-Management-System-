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

    @Override
    public boolean borrowBookById(int userId, int bookId) {
        String checkBookSql = "SELECT * FROM book WHERE id= ?";
        String insertBorrowing = "INSERT INTO borrowing (user_id, book_id, borrow_date, due_date, status) VALUES (?, ?, ?, ?, ?)";
        String updateBookSql = "UPDATE book SET quantity = quantity -1 WHERE id = ?";

        Connection con = connection.getConnection();

        try {
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(checkBookSql);
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();

            if(!rs.next()){
                System.out.println("Book not found!");
                con.rollback();
                return false;
            }

            int quantity = rs.getInt("quantity");
            if(quantity <= 0){
                System.out.println("Book not available at the moment!");
                con.rollback();
                return false;
            }

            PreparedStatement insertBorrowingPs = con.prepareStatement(insertBorrowing);
            insertBorrowingPs.setInt(1, userId);
            insertBorrowingPs.setInt(2, bookId);
            insertBorrowingPs.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            insertBorrowingPs.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(7))); // due after 7 days
            insertBorrowingPs.setString(5, "BORROWED");
            insertBorrowingPs.executeUpdate();

            PreparedStatement updateBookPs = con.prepareStatement(updateBookSql);
            updateBookPs.setInt(1, bookId);
            updateBookPs.executeUpdate();

            con.commit();
            System.out.println("Book borrowed successfully!");
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void myBorrowedBooks(int id) {
        String sql = "SELECT br.id AS borrowing_id, b.id AS book_id, b.title, b.author, br.borrow_date, br.due_date, br.status FROM borrowing br JOIN book b ON br.book_id = b.id WHERE br.user_id = ? AND br.status = 'BORROWED'";
        Connection con = connection.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            System.out.println("=========== Borrowed Books ===========");

            while (rs.next()) {
                System.out.println("Borrowing ID : " + rs.getInt("borrowing_id"));
                System.out.println("Book ID      : " + rs.getInt("book_id"));
                System.out.println("Title        : " + rs.getString("title"));
                System.out.println("Author       : " + rs.getString("author"));
                System.out.println("Borrow Date  : " + rs.getDate("borrow_date"));
                System.out.println("Due Date     : " + rs.getDate("due_date"));
                System.out.println("Status       : " + rs.getString("status"));
                System.out.println("-----------------------------------");
            }

            if (!found) {
                System.out.println("No borrowed books found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
