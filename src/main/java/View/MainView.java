package View;

import jdk.jfr.internal.tool.Main;

import java.util.Scanner;

import static View.LoginView.login;
import static sun.security.jgss.GSSUtil.login;

public class MainView {
    private static final Scanner scanner = new Scanner(System.in);


    public static void mainMenu() {
        System.out.println(" ╔════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(" ║                      WELCOME TO MUSIC SHOW MANAGEMENT SOFTWARE                     ║");
        System.out.println(" ╠════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(" ║                                                                                    ║");
        System.out.println(" ║                                      1. Log in                                     ║");
        System.out.println(" ║                                      2. Register                                   ║");
        System.out.println(" ║                                      0. Exit                                       ║");
        System.out.println(" ║                                                                                    ║");
        System.out.println(" ╚════════════════════════════════════════════════════════════════════════════════════╝");
    }
    public static void main(String[] args) {
        mainMenu();
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
        }
    }

    private static void register() {
    }

}
