package View;

import Services.LoginService;
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
        System.out.print("Enter your account name: ");
        String accountName = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        LoginService.checkAccount(accountName, password);
    }

    private static void register() {
        AdminView.addUser();
        mainMenu();
    }

}
