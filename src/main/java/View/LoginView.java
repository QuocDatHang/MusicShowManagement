package View;

import Utils.PasswordUtils;

import java.util.Scanner;

import static View.AdminView.adminMenu;

public class LoginView {
    private static final Scanner scanner = new Scanner(System.in);

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
        adminMenu();
    }
}
