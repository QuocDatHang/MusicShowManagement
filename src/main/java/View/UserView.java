package View;

import Services.IAdminService;
import Services.UserService;

import java.util.Scanner;

import static View.MainView.mainMenu;

public class UserView {
    private static final Scanner scanner = new Scanner(System.in);
    private IAdminService iAdminService;
    public static void userMenu(){
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║                   USER MENU                ║");
        System.out.println("║      1. Get All Music Shows                ║");
        System.out.println("║      2. Book Ticket                        ║");
        System.out.println("║      3. Your Order                         ║");
        System.out.println("║      4. Edit Order                         ║");
        System.out.println("║      5. Delete Order                       ║");
        System.out.println("║      0. Return Main Menu                   ║");
        System.out.println("╚════════════════════════════════════════════╝");

        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1: {
                getAllMusicShow();
                break;
            }
            case 2: {

                break;
            }
            case 3: {

                break;
            }
            case 4: {

                break;
            }
            case 0: {
                mainMenu();
                break;
            }
        }
    }

    private static void getAllMusicShow() {

    }


    public static void main(String[] args) {
        UserView userView = new UserView();
    }
}