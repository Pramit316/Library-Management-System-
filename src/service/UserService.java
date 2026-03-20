package service;

import model.User;
import repository.Implementation.UserDAOInterfaceImpl;
import repository.UserDAOInterface;

public class UserService {

    UserDAOInterface userRepo = new UserDAOInterfaceImpl();

    public void addMember(User user) {
        userRepo.addMember(user);
    }

    public void findAllMembers(){
        userRepo.findAllMembers();
    }
}
