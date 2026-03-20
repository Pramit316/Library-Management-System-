package repository;

import model.User;

import java.util.List;

public interface UserDAOInterface {
    void addMember(User user);
    void deleteMember(int id);
    void updateMember(int id, User user);
    void viewRequestedMember();
    void acceptRequest(int id);
    void findAllMembers();

    User login(String username);
}
