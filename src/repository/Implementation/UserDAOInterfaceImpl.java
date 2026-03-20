package repository.Implementation;

import configuration.DBConnect;
import model.User;
import repository.UserDAOInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOInterfaceImpl implements UserDAOInterface {

    private final DBConnect connect = new DBConnect();

    @Override
    public void addMember(User user) {
        String sql = "INSERT INTO users (name, role, age, isMember, phone_number, address) VALUES (?,?,?,?,?,?)";

        Connection con = connect.getConnection();

        try {
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getName());
            st.setString(2, user.getRole());
            st.setInt(3, user.getAge());
            st.setBoolean(4, user.isMember());
            st.setLong(5, user.getPhone());
            st.setString(6, user.getAddress());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMember(int id) {

    }

    @Override
    public void updateMember(int id, User user) {

    }

    @Override
    public void viewRequestedMember() {
        String sql = "SELECT * FROM users WHERE ismember = false";

        Connection con = connect.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                System.out.println(
                    "Name: " + rs.getString("name") +
                    "\nPhone Number: " + rs.getLong("phone_number") +
                    "\nRole: " + rs.getString("role") +
                    "\nAge: " + rs.getInt("age") +
                    "\nMember: " + rs.getBoolean("ismember") +
                    "\nAddress: " + rs.getString("address") + "\n"
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void acceptRequest(int id) {

    }

    @Override
    public void findAllMembers() {
        String sql = "SELECT * FROM users";

        Connection con = connect.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            System.out.println("\n------List of all the Members!-------");
            while(resultSet.next()){
                System.out.println(
                        "Name: " + resultSet.getString("name") +
                        "\nPhone Number: " + resultSet.getLong("phone_number") +
                        "\nRole: " + resultSet.getString("role") +
                        "\nAge: " + resultSet.getInt("age") +
                        "\nMember: " + resultSet.getBoolean("ismember") +
                        "\nAddress: " + resultSet.getString("address") + "\n"
                );
            }
        } catch (SQLException e) {
            System.out.println("There was an error finding all members!!!\n" + e);
        }
    }
}
