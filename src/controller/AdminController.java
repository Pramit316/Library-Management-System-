package controller;

import repository.BookRepository;
import service.BookService;

import java.util.Locale;
import java.util.Scanner;

public class AdminController {

    BookService bookService = new BookService();
    Scanner sc = new Scanner(System.in);

    public void display() {

        while (true) {
            System.out.println("=======++Admin Console++==========");
            System.out.println("1. Add new book");
            System.out.println("2. Remove book");
            System.out.println("3. Update Book Details");
            System.out.println("4. View Books");
            System.out.println("5. Add Member");
            System.out.println("6. Update Member");
            System.out.println("7. Delete Member");
            System.out.println("8. Exit to Main Menu");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addNewBook();
                    break;
                case 2:
                    System.out.println("Book Removed");
                    break;
                case 3:
                    System.out.println("Book Updated");
                    break;
                case 4:
                    bookService.findAllBook();
                    System.out.println("Press any key to continue.....");
                    sc.nextLine();
                    sc.nextLine();
                    break;
                case 5:
                    System.out.println("New Member Added");
                    break;
                case 6:
                    System.out.println("New Member Updated");
                    break;
                case 7:
                    System.out.println("New Member Deleted");
                    break;
                case 8:
                    System.out.println("Back to main menu.....");
                    return;
            }
        }
    }

    private void addNewBook() {
        boolean availableBool;
        System.out.println("Enter the book title");
        String title = sc.next();

        System.out.println("Enter Author Name");
        String authorName = sc.next();

        System.out.println("Enter Availability");
        String available = sc.next();

        if(available.equalsIgnoreCase("y") || available.equalsIgnoreCase("yes")) {
            availableBool = true;
        } else {
            availableBool = false;
        }

        System.out.println("Enter the price of the book");
        float price = sc.nextFloat();

        bookService.addNewBook(title, authorName, availableBool, price);
    }
}
