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
import static View.UserView.*;

public class AdminView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService iUserService = new UserService();

    public static void adminMenu(){
        System.out.println("                ╔════════════════════════════════════════════╗");
        System.out.println("                ║                   ADMIN MENU               ║");
        System.out.println("                ║      1. Manage users                       ║");
        System.out.println("                ║      2. Manage music show                  ║");
        System.out.println("                ║      3. Manage bill                        ║");
        System.out.println("                ║      4. View report music show             ║");
        System.out.println("                ║      0. Return                             ║");
        System.out.println("                ╚════════════════════════════════════════════╝");

        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1: {
                userManagementMenu();
                break;
            }
            case 2: {
                showMenu();
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




}
