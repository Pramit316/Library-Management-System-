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
                    "Id: " + rs.getInt("id") +
                    "\nName: " + rs.getString("name") +
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
        String sql = "UPDATE users SET ismember = true WHERE id = (?)";

        Connection con = connect.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was some error that occured while accepting membership\n" + e);
        }
    }

    @Override
    public void findAllMembers() {
        String sql = "SELECT * FROM users WHERE ismember = true";

        Connection con = connect.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            System.out.println("\n------List of all the Members!-------");
            while(resultSet.next()){
                System.out.println(
                        "Id: " + resultSet.getInt("id") +
                        "\nName: " + resultSet.getString("name") +
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

    @Override
    public User login(String username) {
        String sql = "SELECT * FROM users WHERE name = (?)";

        Connection con = connect.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if(!rs.next()) {
                System.out.println("Please Apply for Membership!");
                return null;
            }

            if (!rs.getBoolean("ismember")) {
                System.out.println("User membership request is still processing!");
                return null;
            }

            return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("role"),
                    rs.getInt("age"),
                    rs.getBoolean("ismember"),
                    rs.getLong("phone_number"),
                    rs.getString("address")
            );
        } catch (SQLException e) {
            System.out.println("There was an error finding all members!!!\n" + e);
        }
        return null;
    }
}
