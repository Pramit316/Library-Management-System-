package controller;

import model.User;
import service.BookService;
import service.UserService;

import java.util.Scanner;

public class UserController {

    private final Scanner sc = new Scanner(System.in);
    private final UserService userService = new UserService();
    private final BookService bookService = new BookService();
    private User user;

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

        if(user != null) {
            this.user = user;
            userMenu(user);
        }
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
            System.out.println("3. My Borrowing");
            System.out.println("4. Exit");
            System.out.println("5. Logout");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    viewBooks();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    myBooks();
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

    private void myBooks() {
        bookService.myBooks(user.getId());
    }

    private void borrowBook() {

        int id = user.getId();
        String name = user.getName();

        while (true) {
            System.out.println("========= Borrow Book =========");
            System.out.println("1. Borrow by Book ID");
            System.out.println("2. Borrow by Book Name");
            System.out.println("3. Back");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:

                    System.out.println("Borrowing book for name: " + name + " | ID: " + id);
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();
                    sc.nextLine();

                    bookService.borrowBookById(id, bookId);
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;

                case 2:
                    System.out.println("This feature will be available soon!!!");
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private void viewBooks() {
        bookService.findAllBook();
    }
}
