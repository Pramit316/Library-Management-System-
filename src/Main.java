import configuration.DBConnect;
import controller.AdminController;
import controller.UserController;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AdminController adminController = new AdminController();
        UserController userController = new UserController();

        while (true) {
            System.out.println("================================WELCOME TO PRAMIT'S LIBRARY MANAGEMENT SYSTEM================================");
            System.out.println("CHOOSE AN OPTION BELOW!");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    adminController.display();
                    break;

                case 2:
                    userController.display();
                    break;

                case 3:
                    System.out.println("Exiting.........");
                    return;

                default:
                    System.out.println("Invalid Number");
                    break;
            }
        }
    }
}