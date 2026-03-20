package controller;

import model.User;
import service.BookService;
import service.UserService;

import java.util.Scanner;

public class UserController {

    private final Scanner sc = new Scanner(System.in);
    private final UserService userService = new UserService();
    private final BookService bookService = new BookService();

    public void display() {
        while (true) {
            System.out.println("======= User Console =======");
            System.out.println("1. Login");
            System.out.println("2. Request Membership");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    requestMembership();
                    break;
                case 3:
                    System.out.println("Exiting user console...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    void login() {
        System.out.println("Enter the username\n");
        String username =  sc.nextLine();
        User user = userService.login(username);

        userMenu(user);
    }

    private void requestMembership() {
        System.out.println("======= Membership Request Form =======");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter your age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter your phone number: ");
        long phoneNumber = sc.nextLong();
        sc.nextLine();

        System.out.print("Enter your address: ");
        String address = sc.nextLine();

        String role = "USER";
        boolean isMember = false;

        User user = new User(name, role, age, isMember, phoneNumber, address);

        userService.addMember(user);

        System.out.println("Membership request submitted successfully.");
    }

    private void userMenu(User user) {
        System.out.println("Logged in with user: " + user.getName());
        while (true) {
            System.out.println("======= User Menu =======");
            System.out.println("1. View Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Add Book to Cart");
            System.out.println("4. Exit");
            System.out.println("5. Logout");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    viewBooks();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    addToCart();
                    break;
                case 4:
                    System.out.println("Exiting");
                    return;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addToCart() {
    }

    private void borrowBook() {
    }

    private void viewBooks() {
        bookService.findAllBook();
    }
}
