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

public class AdminView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final IUserService iUserService = new UserService();
    private static final IShowService iShowService = new ShowService();

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

    private static void manageMusicShowMenu() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║         MUSIC SHOW MANAGEMENT MENU         ║");
        System.out.println("║      1. Show all shows                     ║");
        System.out.println("║      2. Add new show                       ║");
        System.out.println("║      3. Edit show                          ║");
        System.out.println("║      4. Delete show                        ║");
        System.out.println("║      0. Return                             ║");
        System.out.println("╚════════════════════════════════════════════╝");

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

                break;
            }
            case 4: {

                break;
            }
            case 5: {

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

    private static void addShow() {
        String showName = inputShowName();
        String singer = inputSinger();
        LocalDateTime timeStart = inputTimeStart();
        LocalDateTime timeEnd = inputTimeEnd();
        ELocation location = inputLocation();
        double showPrice = Double.parseDouble(inputShowPrice());

        Show show = new Show(iShowService.nextIdShow(), showName, singer, timeStart, timeEnd, location, showPrice);
        iShowService.createShow(show);
        manageMusicShowMenu();
    }

    private static String inputShowName(){
        boolean validateName = true;
        String showName;
        do {
            System.out.print("Enter show name: ");
            showName = scanner.nextLine();
            if (ValidateUtils.isValidShowName(showName)){
                validateName = false;
            } else {
                System.out.println("'Show Name' must start with an alphabetical character or number," +
                        " included 2-30 character");
            }
        } while (validateName);
        return showName;
    }
    private static String inputSinger(){
        boolean validateSinger = true;
        String singer;
        do {
            System.out.print("Enter singer: ");
            singer = scanner.nextLine();
            if (ValidateUtils.isValidSinger(singer)){
                validateSinger = false;
            } else {
                System.out.println("'Singer' must start with an alphabetical character," +
                        " included 2-20 character");
            }
        } while (validateSinger);
        return singer;
    }
    private static ELocation inputLocation(){
        System.out.print("Choose location: ");
        for (ELocation e : ELocation.values()) {
            System.out.print(e.getId() + ". " + e +"\t");
        }
        ELocation location;
        do {
            long idLocation = Long.parseLong(scanner.nextLine());
            location = ELocation.findLocationById(idLocation);
        }
        while (location == null);
        return location;
    }
    private static LocalDateTime inputTimeStart(){
        LocalDateTime timeStart;
        do {
            System.out.print("Enter time start (dd-mm-yyyy hh:mm): ");
            timeStart = DateUtils.parseDateTime(scanner.nextLine());
        }
        while (timeStart == null);
        return timeStart;
    }
    private static LocalDateTime inputTimeEnd(){
        LocalDateTime timeEnd;
        do {
            System.out.print("Enter time end (dd-mm-yyyy hh:mm): ");
            timeEnd = DateUtils.parseDateTime(scanner.nextLine());
        }
        while (timeEnd == null);
        return timeEnd;
    }
    private static String inputShowPrice(){
        boolean validateShowPrice = true;
        String showPrice;
        do {
            System.out.print("Enter singer: ");
            showPrice = scanner.nextLine();
            if (ValidateUtils.isValidShowPrice(showPrice)){
                validateShowPrice = false;
            } else {
                System.out.println("'Singer' must start with an alphabetical character," +
                        " included 2-20 character");
            }
        } while (validateShowPrice);
        return showPrice;
    }

    //    private long id;
    //    private String showName;
    //    private String singer;
    //    private LocalDate timeStart;
    //    private LocalDate timeEnd;
    //    private String location;

    private static void showAllShows() {
        List<Show> showList = iShowService.getAllShows();
        System.out.printf("%10s | %25s | %20s | %30s | %30s | %20s | %20s\n", "ID SHOW", "SHOW NAME", "SINGER",
                "TIME START", "TIME END", "LOCATION", "SHOW PRICE(VND)");
        for (Show s : showList){
            System.out.printf("%10s | %25s | %20s | %30s | %30s | %20s | %20.3f\n", s.getIdShow(), s.getShowName(),
                    s.getSinger(), DateUtils.formatDateTime(s.getTimeStart()), DateUtils.formatDateTime(s.getTimeEnd()), s.getLocation(), s.getShowPrice());
        }
        manageMusicShowMenu();
    }

    public static void manageUsersMenu() {
        System.out.println("            ╔════════════════════════════════════════════╗");
        System.out.println("            ║            USER MANAGEMENT MENU            ║");
        System.out.println("            ║      1. Show all users                     ║");
        System.out.println("            ║      2. Add new user                       ║");
        System.out.println("            ║      3. Edit user                          ║");
        System.out.println("            ║      4. Find user by ID                    ║");
        System.out.println("            ║      5. Delete user                        ║");
        System.out.println("            ║      0. Return                             ║");
        System.out.println("            ╚════════════════════════════════════════════╝");

        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1: {
                showAllUser();
                break;
            }
            case 2: {
                addUser();
                manageUsersMenu();
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
            default:{
                System.out.println("Please enter a number between 0-6");
                manageUsersMenu();
            }
        }
    }


    private static void showAllUser() {
        List<User> userList = iUserService.getAllUsers();
        System.out.printf("%10s | %20s | %15s | %15s | %30s | %20s | %15s | %10s | %10s\n", "ID USER", "NAME", "ACCOUNT NAME"
                , "DATE OF BIRTH", "EMAIL", "ADDRESS", "PHONE NUMBER", "GENDER", "ROLE");
        for (User u : userList){
            System.out.printf("%10s | %20s | %15s | %15s | %30s | %20s | %15s | %10s | %10s\n", u.getIdUser(), u.getName(),
                    u.getAccountName(), DateUtils.formatDate(u.getDob()), u.getEmail(), u.getAddress(), u.getPhoneNumber(), u.getGender(), u.getRole());
        }
        manageUsersMenu();
    }
    public static void addUser() {
        String name = inputName();
        String accountName = inputAccountName();
        String password = inputPassword();
        System.out.print("Enter your date of birth: ");
        LocalDate dob = DateUtils.parseDate(scanner.nextLine());
        String email = inputEmail();
        String address = inputAddress();
        String phoneNumber = inputPhoneNumber();
        EGender gender = inputGender();
//        ERole role = inputRole();

        User user = new User(iUserService.nextIdUser(), name, accountName, PasswordUtils.generatePassword(password), dob, email, address, phoneNumber, gender, ERole.USER);
        iUserService.createUser(user);
    }
    private static void editUser() {
        System.out.print("Enter id to find: ");
        long idUser = Long.parseLong(scanner.nextLine());
        User editUser = iUserService.findUserById(idUser);
        showUser(editUser);

        editUser.setName(inputName());
        editUser.setAccountName(inputAccountName());
        editUser.setPassword(inputPassword());
        System.out.print("Enter your date of birth: ");
        editUser.setDob(DateUtils.parseDate(scanner.nextLine()));
        editUser.setEmail(inputEmail());
        editUser.setAddress(inputAddress());
        editUser.setPhoneNumber(inputPhoneNumber());
        editUser.setGender(inputGender());
//        editUser.setRole(inputRole());

        iUserService.updateUser(editUser);
        manageUsersMenu();
    }
    private static void findUserById() {
        System.out.print("Enter id to find: ");
        long idUser = Long.parseLong(scanner.nextLine());
        showUser(iUserService.findUserById(idUser));
        manageUsersMenu();
    }

    private static void deleteUser() {
        System.out.print("Enter id to delete: ");
        long idUser = Long.parseLong(scanner.nextLine());
        iUserService.deleteUser(idUser);
        manageUsersMenu();
    }
    private static void showUser(User u){
        System.out.printf("%10s | %20s | %15s | %15s | %30s | %20s | %15s | %10s | %10s\n", "ID", "NAME", "ACCOUNT NAME",
                 "DATE OF BIRTH", "EMAIL", "ADDRESS", "PHONE NUMBER", "GENDER", "ROLE");
        System.out.printf("%10s | %20s | %15s | %15s | %30s | %20s | %15s | %10s | %10s\n", u.getIdUser(), u.getName(),
                u.getAccountName(), DateUtils.formatDate(u.getDob()), u.getEmail(), u.getAddress(), u.getPhoneNumber(), u.getGender(), u.getRole());
    }

    private static String inputName(){
        boolean validateName = true;
        String name;
        do {
            System.out.print("Enter your name: ");
            name = scanner.nextLine();
            if (ValidateUtils.isValidName(name)){
                validateName = false;
            } else {
                System.out.println("'Name' must start with an alphabetical character," +
                        " included 2-20 character");
            }
        } while (validateName);
        return name;
    }
    private static String inputAccountName(){
        boolean validateAccountName = true;
        String accountName;
        do{
            System.out.print("Enter your account name: ");
            accountName = scanner.nextLine();
            if (ValidateUtils.isValidAccountName(accountName)){
                validateAccountName = false;
            } else {
                System.out.println("'Account Name' must start with an alphabetical character," +
                        " included 8-20 characters that are either alphabetical, numeric, or underscore!");
            }
        } while (validateAccountName);
        return accountName;
    }

    private static String inputPassword(){
        String password;
        boolean validatePassword = false;
        do {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            if (ValidateUtils.isValidPassword(password)){
                validatePassword = false;
            } else {
                validatePassword = true;
                System.out.println("'Password' contains at least one alphabetical character, one digit or " +
                        "one special character from '@$!%*?&', included 6-20 characters!");
            }
        } while (validatePassword);
        return password;
    }
    private static String inputEmail(){
        String email;
        boolean validateEmail = false;
        do {
            System.out.print("Enter your email: ");
            email = scanner.nextLine();
            if (ValidateUtils.isValidEmail(email)){
                validateEmail = false;
            } else {
                validateEmail = true;
                System.out.println("'Email' contains 5-32 alphabetical character, digit or underscore + @ + " +
                        "at least two alphabetical character or digit + '.' + 2-4 alphabetical character or digit");
            }
        } while (validateEmail);
        return email;
    }
    private static String inputAddress(){
        String address;
        boolean validateAddress = false;
        do {
            System.out.print("Enter your address: ");
            address = scanner.nextLine();
            if (ValidateUtils.isValidAddress(address)){
                validateAddress = false;
            } else {
                validateAddress = true;
                System.out.println("'Address' contains at least 2 alphabetical character or digit");
            }
        } while (validateAddress);
        return address;
    }
    private static String inputPhoneNumber(){
        String phoneNumber;
        boolean validatePhoneNumber = false;
        do {
            System.out.print("Enter your phone number: ");
            phoneNumber = scanner.nextLine();
            if (ValidateUtils.isValidPhoneNumber(phoneNumber)){
                validatePhoneNumber = false;
            } else {
                validatePhoneNumber = true;
                System.out.println("'Phone number' contains 10 digit and begin with number 0");
            }
        } while (validatePhoneNumber);
        return phoneNumber;
    }
    private static EGender inputGender(){
        System.out.print("Enter your gender: ");
        for (EGender e : EGender.values()) {
            System.out.print(e.getId() + ". " + e.getName() +"\t");
        }
        EGender gender;
        do {
            long idGender = Long.parseLong(scanner.nextLine());
            gender = EGender.findGenderById(idGender);
        }
        while (gender == null);
        return gender;
    }
}
