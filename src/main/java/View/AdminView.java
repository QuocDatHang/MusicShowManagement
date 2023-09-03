package View;

import Models.*;
import Services.*;
import Utils.DateUtils;
import Utils.PasswordUtils;
import Utils.ValidateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import Enum.EGender;
import Enum.ERole;
import Enum.ELocation;

import static View.MainView.mainMenu;
import static View.ShowView.*;
import static View.UserView.manageUsersMenu;

public class AdminView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService iUserService = new UserService();

    public static void adminMenu(){
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║                   ADMIN MENU               ║");
        System.out.println("║      1. Manage users                       ║");
        System.out.println("║      2. Manage music show                  ║");
        System.out.println("║      3. Manage bill                        ║");
        System.out.println("║      4. View report music show             ║");
        System.out.println("║      0. Return                             ║");
        System.out.println("╚════════════════════════════════════════════╝");

        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1: {
                manageUsersMenu();
                break;
            }
            case 2: {
                manageMusicShowMenu();
                break;
            }
            case 3: {
                manageBillMenu();
                break;
            }
            case 4: {
                viewReportMenu();
                break;
            }
            case 0: {
                mainMenu();
                break;
            }
        }
    }

    private static void viewReportMenu() {

    }

    private static void manageBillMenu() {

    }

    static void manageMusicShowMenu() {
        System.out.println("            ╔════════════════════════════════════════════╗");
        System.out.println("            ║         MUSIC SHOW MANAGEMENT MENU         ║");
        System.out.println("            ║      1. Show all shows                     ║");
        System.out.println("            ║      2. Add new show                       ║");
        System.out.println("            ║      3. Edit show                          ║");
        System.out.println("            ║      4. Delete show                        ║");
        System.out.println("            ║      0. Return                             ║");
        System.out.println("            ╚════════════════════════════════════════════╝");

        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1: {
                showAllShows();
                break;
            }
            case 2: {
                addShow();
                manageMusicShowMenu();
                break;
            }
            case 3: {
                editShow();
                break;
            }
            case 4: {
                deleteShow();
                break;
            }
            case 0: {
                adminMenu();
                break;
            }
            default:{
                System.out.println("Please enter a number between 0-6");
                manageMusicShowMenu();
            }
        }
    }


}
