package View;

import Utils.PasswordUtils;

import java.util.Scanner;

import static View.AdminView.adminMenu;
import static View.UserView.userMenu;

public class MainView {
    private static final Scanner scanner = new Scanner(System.in);

    public static void mainMenu() {
        System.out.println("             ╔════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("             ║                      WELCOME TO MUSIC SHOW MANAGEMENT SOFTWARE                     ║");
        System.out.println("             ╠════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("             ║                                                                                    ║");
        System.out.println("             ║                                      1. Log in                                     ║");
        System.out.println("             ║                                      2. Register                                   ║");
        System.out.println("             ║                                      0. Exit                                       ║");
        System.out.println("             ║                                                                                    ║");
        System.out.println("             ╚════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1: {
                login();
                break;
            }
            case 2: {
                register();
                break;
            }
            case 0: {
                System.exit(0);
                break;
            }
            default:{
                System.out.println("Please choose a number form 0-2");
                mainMenu();
                break;
            }
        }
    }
    public static void main(String[] args) {
        mainMenu();
    }

    public static void login(){
        String accountName;
        String hashPassword;
        do {
            System.out.print("Enter your account name: ");
            accountName = scanner.nextLine();
            hashPassword = PasswordUtils.isValidAccountName(accountName);
        }
        while (hashPassword == null);

        String password;
        do {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
        } while (!PasswordUtils.isValidPassword(password, hashPassword));
        System.out.println("Login successful!");
        if (accountName.equals("quocdathang")) {
            adminMenu();
        }
        else {
            userMenu();
        }
    }

    private static void register() {
        AdminView.addUser();
        mainMenu();
    }

}
