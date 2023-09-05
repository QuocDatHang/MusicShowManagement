package View;

import Enum.EGender;
import Enum.ERole;
import Enum.EStatus;
import Models.*;
import Services.*;
import Utils.DateUtils;
import Utils.PasswordUtils;
import Utils.ValidateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Services.SeatService.showSeatList;
import static View.AdminView.adminMenu;
import static View.MainView.mainMenu;
import static View.OrderView.bookTicket;
import static View.OrderView.yourOrder;
import static View.ShowView.getAllMusicShows;

public class UserView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static final OrderService orderService = new OrderService();
    private static final TicketService ticketService = new TicketService();
    private static final ShowService showService = new ShowService();
    private static final SeatService seatService = new SeatService();

    public static void userMenu(long idUser) {
        System.out.println("                ╔════════════════════════════════════════════╗");
        System.out.println("                ║                   USER MENU                ║");
        System.out.println("                ║      1. Get All Music Shows                ║");
        System.out.println("                ║      2. Book Ticket                        ║");
        System.out.println("                ║      3. Your Order                         ║");
        System.out.println("                ║      4. Delete Order                       ║");
        System.out.println("                ║      0. Return Main Menu                   ║");
        System.out.println("                ╚════════════════════════════════════════════╝");

        int choice = MainView.isValidChoice(0, 4);

        switch (choice) {
            case 1: {
                getAllMusicShows();
                userMenu(idUser);
                break;
            }
            case 2: {
                bookTicket(idUser);
                break;
            }
            case 3: {
                yourOrder(idUser);
                break;
            }
            case 4: {
                mainMenu();
                break;
            }
            case 0: {
                mainMenu();
                break;
            }
        }
    }

    public static void userManagementMenu() {
        System.out.println("            ╔════════════════════════════════════════════╗");
        System.out.println("            ║            USER MANAGEMENT MENU            ║");
        System.out.println("            ║      1. Show all users                     ║");
        System.out.println("            ║      2. Add new user                       ║");
        System.out.println("            ║      3. Edit user                          ║");
        System.out.println("            ║      4. Find user by ID                    ║");
        System.out.println("            ║      5. Delete user                        ║");
        System.out.println("            ║      0. Return                             ║");
        System.out.println("            ╚════════════════════════════════════════════╝");

        int choice = MainView.isValidChoice(0, 5);

        switch (choice) {
            case 1: {
                showAllUser();
                break;
            }
            case 2: {
                addUser();
                userManagementMenu();
                break;
            }
            case 3: {
                editUser();
                break;
            }
            case 4: {
                findUserById();
                break;
            }
            case 5: {
                deleteUser();
                break;
            }
            case 0: {
                adminMenu();
                break;
            }
        }
    }


    private static void showAllUser() {
        List<User> userList = userService.getAll();
        System.out.printf("%10s | %20s | %15s | %15s | %30s | %20s | %15s | %10s | %10s\n", "ID USER", "NAME", "ACCOUNT NAME"
                , "DATE OF BIRTH", "EMAIL", "ADDRESS", "PHONE NUMBER", "GENDER", "ROLE");
        for (User u : userList) {
            System.out.printf("%10s | %20s | %15s | %15s | %30s | %20s | %15s | %10s | %10s\n", u.getIdUser(), u.getName(),
                    u.getAccountName(), DateUtils.formatDate(u.getDob()), u.getEmail(), u.getAddress(), u.getPhoneNumber(), u.getGender(), u.getRole());
        }
        userManagementMenu();
    }

    public static void addUser() {
        String name = inputName();
        String accountName = inputAccountName();
        String password = inputPassword();
        LocalDate dob = inputDOB();
        String email = inputEmail();
        String address = inputAddress();
        String phoneNumber = inputPhoneNumber();
        EGender gender = inputGender();

        User user = new User(userService.nextId(), name, accountName, PasswordUtils.generatePassword(password), dob, email, address, phoneNumber, gender, ERole.USER);
        userService.create(user);
    }

    private static void editUser() {
        System.out.print("Enter id to find: ");
        long idUser = Long.parseLong(scanner.nextLine());
        User editUser = userService.findById(idUser);
        showUser(editUser);

        editUser.setName(inputName());
        editUser.setAccountName(inputAccountName());
        editUser.setPassword(inputPassword());
        editUser.setDob(inputDOB());
        editUser.setEmail(inputEmail());
        editUser.setAddress(inputAddress());
        editUser.setPhoneNumber(inputPhoneNumber());
        editUser.setGender(inputGender());

        userService.update(editUser);
        userManagementMenu();
    }

    private static void findUserById() {
        System.out.print("Enter id to find: ");
        long idUser = Long.parseLong(scanner.nextLine());
        showUser(userService.findById(idUser));
        userManagementMenu();
    }

    private static void deleteUser() {
        System.out.print("Enter id to delete: ");
        long idUser = Long.parseLong(scanner.nextLine());
        userService.delete(idUser);
        userManagementMenu();
    }

    private static void showUser(User u) {
        System.out.printf("%10s | %20s | %15s | %15s | %30s | %20s | %15s | %10s | %10s\n", "ID", "NAME", "ACCOUNT NAME",
                "DATE OF BIRTH", "EMAIL", "ADDRESS", "PHONE NUMBER", "GENDER", "ROLE");
        System.out.printf("%10s | %20s | %15s | %15s | %30s | %20s | %15s | %10s | %10s\n", u.getIdUser(), u.getName(),
                u.getAccountName(), DateUtils.formatDate(u.getDob()), u.getEmail(), u.getAddress(), u.getPhoneNumber(), u.getGender(), u.getRole());
    }

    private static String inputName() {
        boolean validateName = true;
        String name;
        do {
            System.out.print("Enter your name: ");
            name = scanner.nextLine();
            if (ValidateUtils.isValidName(name)) {
                validateName = false;
            } else {
                System.out.println("'Name' must start with an alphabetical character," +
                        " included 2-20 character");
            }
        } while (validateName);
        return name;
    }

    private static String inputAccountName() {
        boolean validateAccountName = true;
        String accountName;
        do {
            System.out.print("Enter your account name: ");
            accountName = scanner.nextLine();
            if (ValidateUtils.isValidAccountName(accountName)) {
                validateAccountName = false;
            } else {
                System.out.println("'Account Name' must start with an alphabetical character," +
                        " included 6-20 characters that are either alphabetical, numeric, or underscore!");
            }
        } while (validateAccountName);
        return accountName;
    }

    private static String inputPassword() {
        String password;
        boolean validatePassword = false;
        do {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            if (ValidateUtils.isValidPassword(password)) {
                validatePassword = false;
            } else {
                validatePassword = true;
                System.out.println("'Password' contains at least one alphabetical character, one digit or " +
                        "one special character from '@$!%*?&', included 6-20 characters!");
            }
        } while (validatePassword);
        return password;
    }

    private static LocalDate inputDOB() {
        LocalDate dob;
        do {
            System.out.print("Enter your date of birth: ");
            dob = DateUtils.parseDate(scanner.nextLine());
        }
        while (dob == null);
        return dob;
    }

    private static String inputEmail() {
        String email;
        boolean validateEmail = false;
        do {
            System.out.print("Enter your email: ");
            email = scanner.nextLine();
            if (ValidateUtils.isValidEmail(email)) {
                validateEmail = false;
            } else {
                validateEmail = true;
                System.out.println("'Email' contains 5-32 alphabetical character, digit or underscore + @ + " +
                        "at least two alphabetical character or digit + '.' + 2-4 alphabetical character or digit");
            }
        } while (validateEmail);
        return email;
    }

    private static String inputAddress() {
        String address;
        boolean validateAddress = false;
        do {
            System.out.print("Enter your address: ");
            address = scanner.nextLine();
            if (ValidateUtils.isValidAddress(address)) {
                validateAddress = false;
            } else {
                validateAddress = true;
                System.out.println("'Address' contains at least 2 alphabetical character or digit");
            }
        } while (validateAddress);
        return address;
    }

    private static String inputPhoneNumber() {
        String phoneNumber;
        boolean validatePhoneNumber = false;
        do {
            System.out.print("Enter your phone number: ");
            phoneNumber = scanner.nextLine();
            if (ValidateUtils.isValidPhoneNumber(phoneNumber)) {
                validatePhoneNumber = false;
            } else {
                validatePhoneNumber = true;
                System.out.println("'Phone number' contains 10 digit and begin with number 0");
            }
        } while (validatePhoneNumber);
        return phoneNumber;
    }

    private static EGender inputGender() {
        System.out.print("Enter your gender: ");
        for (EGender e : EGender.values()) {
            System.out.print(e.getId() + ". " + e.getName() + "\t");
        }
        EGender gender;
        do {
            long idGender = Long.parseLong(scanner.nextLine());
            gender = EGender.findGenderById(idGender);
        }
        while (gender == null);
        return gender;
    }


    public static void main(String[] args) {
        UserView userView = new UserView();
    }
}
