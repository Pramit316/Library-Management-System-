package controller;

import model.Book;
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
                    removeBook();
                    break;
                case 3:
                    updateBook();
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

        Book book = new Book(title, author, availableBool, price);

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

        bookService.addNewBook(title, authorName, availableBool, price);
    }
}
