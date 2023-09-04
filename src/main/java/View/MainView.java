package View;

import Services.LoginService;

import java.util.Scanner;

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

        int choice = isValidChoice(0, 2);

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
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }

    public static void login() {
        System.out.print("Enter your account name: ");
        String accountName = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        LoginService.checkAccount(accountName, password);
    }

    private static void register() {
        UserView.addUser();
        mainMenu();
    }

    public static int isValidChoice(int min, int max) {
        int temp = min-1;
        String input;
        do {
            System.out.print("Enter your choice: ");
            try {
                input = scanner.nextLine();
                if (input.isEmpty()) {
                    temp = min - 1;
                    System.out.println("Please enter a valid number!");
                } else {
                    temp = Integer.parseInt(input);
                    if (temp < min || temp > max){
                        System.out.println("Please enter a valid number!");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        } while (temp < min || temp > max);
        return temp;
    }

}
