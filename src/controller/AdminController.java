package controller;

import model.Book;
import model.User;
import service.BookService;
import service.UserService;

import java.util.Scanner;

public class AdminController {

    BookService bookService = new BookService();
    UserService userService = new UserService();
    Scanner sc = new Scanner(System.in);

    public void display() {

        while (true) {
            System.out.println("=======++Admin Console++==========");
            System.out.println("1. Add new book");
            System.out.println("2. Remove book");
            System.out.println("3. Update Book Details");
            System.out.println("4. View Books");
            System.out.println("5. Member Action Menu");
            System.out.println("6. Exit to Main Menu");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addNewBook();
                    break;

                case 2:
                    removeBook();
                    break;

                case 3:
                    updateBook();
                    break;

                case 4:
                    bookService.findAllBook();
                    System.out.println("Press Enter to continue.....");
                    sc.nextLine();
                    break;

                case 5:
                    memberActionMenu();
                    break;

                case 6:
                    System.out.println("Back to main menu.....");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void memberActionMenu() {
        while (true) {
            System.out.println("=======++Member Action Menu++==========");
            System.out.println("1. Add Member");
            System.out.println("2. View All Members");
            System.out.println("3. View Requested Membership");
            System.out.println("4. Accept Membership");
            System.out.println("5. Delete Member");
            System.out.println("6. Back to Admin Menu");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addMember();
                    break;

                case 2:
                    userService.findAllMembers();
                    System.out.println("Press Enter to continue.....");
                    sc.nextLine();
                    break;

                case 3:
                    userService.viewRequestedMembers();
                    System.out.println("Press Enter to continue.....");
                    sc.nextLine();
                    break;

                case 4:
                    acceptMembership();
                    break;

                case 5:
                    System.out.println("Member Deleted");
                    break;

                case 6:
                    System.out.println("Back to Admin Console.....");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void acceptMembership() {
        System.out.println("Enter the ID of the member to accept membership:");
        int id = sc.nextInt();
        sc.nextLine();

        userService.acceptMembership(id);

        System.out.println("Membership status updated successfully.");
        System.out.println("Press Enter to continue.....");
        sc.nextLine();
    }

    private void addMember() {
        boolean isMember;
        sc.nextLine();
        System.out.println("Enter the name of member");
        String name = sc.nextLine();

        System.out.println("Enter the age of member");
        int age = sc.nextInt();

        System.out.println("Enter phone number");
        long phone = sc.nextLong();

        sc.nextLine();
        System.out.println("Enter address");
        String address = sc.nextLine();

        System.out.println("Enter role of the user: (member or admin)");
        String role = sc.nextLine().toLowerCase().trim();

        User user = new User(name, role,age, true, phone, address);

        userService.addMember(user);
    }

    private void updateBook() {
        System.out.println("Enter the id of the book you want to edit: ");
        int id = sc.nextInt();

        sc.nextLine();
        System.out.println("Enter book title");
        String title = sc.nextLine();

        System.out.println("Enter book author");
        String author = sc.nextLine();

        System.out.println("Enter book availability");
        String available = sc.nextLine();

        boolean availableBool;
        if(available.equalsIgnoreCase("y") || available.equalsIgnoreCase("yes")) {
            availableBool = true;
        } else {
            availableBool = false;
        }

        System.out.println("Enter book price");
        float price = sc.nextFloat();

        System.out.println("Enter Quantity");
        int quantity = sc.nextInt();

        Book book = new Book(title, author, availableBool, price, quantity);

        bookService.updateBook(id, book);
    }

    private void removeBook() {

        System.out.println("Enter the id of the book you want to remove: ");
        int id = sc.nextInt();

        bookService.deleteBook(id);
    }

    private void addNewBook() {
        boolean availableBool;
        sc.nextLine();
        System.out.println("Enter the book title");
        String title = sc.nextLine();

        System.out.println("Enter Author Name");
        String authorName = sc.nextLine();

        System.out.println("Enter Availability");
        String available = sc.nextLine();

        if(available.equalsIgnoreCase("y") || available.equalsIgnoreCase("yes")) {
            availableBool = true;
        } else {
            availableBool = false;
        }

        System.out.println("Enter the price of the book");
        float price = sc.nextFloat();

        System.out.println("Enter Quantity");
        int quantity = sc.nextInt();

        bookService.addNewBook(title, authorName, availableBool, price, quantity);
    }
}
