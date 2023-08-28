package View;

import Models.EGender;
import Models.ERole;
import Models.User;
import Services.IUserService;
import Services.UserService;
import Utils.DateUtils;
import Utils.ValidateUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final Scanner scanner = new Scanner(System.in);
    private IUserService iUserService;
    UserView(){
        iUserService = new UserService();
    }
    void launcher() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║            USER MANAGEMENT MENU            ║");
        System.out.println("║      1. Show all users                     ║");
        System.out.println("║      2. Add new user                       ║");
        System.out.println("║      3. Edit user                          ║");
        System.out.println("║      4. Find user by ID                    ║");
        System.out.println("║      5. Delete user                        ║");
        System.out.println("║      0. Return                             ║");
        System.out.println("╚════════════════════════════════════════════╝");

        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1: {
                showAllUser();
                break;
            }
            case 2: {
                addUser();
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
        }
    }

    private void deleteUser() {

    }

    private void findUserById() {

    }

    private void editUser() {

    }

    private String inputName(){
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
    private String inputAccountName(){
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

    private String inputPassword(){
        String password;
        boolean validatePassword = false;
        do {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            if (ValidateUtils.isValidPassword(password)){
                validatePassword = false;
            } else {
                validatePassword = true;
                System.out.println("'Password' contains at least one alphabetical character,  one digit, " +
                        "one special character from '@$!%*?&', included 8-20 characters!");
            }
        } while (validatePassword);
        return password;
    }
    private String inputEmail(){
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

    private void addUser() {
        String name = inputName();
        String accountName = inputAccountName();
        String password = inputPassword();
        System.out.print("Enter your date of birth: ");
        LocalDate dob = DateUtils.parseDate(scanner.nextLine());
        String email = inputEmail();





        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter your gender: ");
        for (EGender e : EGender.values()) {
            System.out.print(e.getId() + ". " + e.getName() +"\t");
        }
        EGender gender;
        do {
            long idGender = Long.parseLong(scanner.nextLine());
            gender = EGender.findGenderById(idGender);
        }
        while (gender.equals(null));

        String r;
        do {
            System.out.print("Enter your role (USER/ADMIN): ");
            r = scanner.nextLine();
        }
        while (!r.equals("USER") && !r.equals("ADMIN"));
        ERole role = ERole.valueOf(r);

        User user = new User(iUserService.nextIdUser(), name, accountName, password, dob, email, address, phoneNumber, gender, role);
        iUserService.createUser(user);
        showAllUser();
    }

    private void showAllUser() {
        List<User> userList = iUserService.getAllUsers();
        System.out.printf("%10s | %20s | %15s | %15s | %15s | %20s | %20s | %15s | %10s | %10s\n", "ID", "NAME", "ACCOUNT NAME",
                                "PASSWORD", "DATE OF BIRTH", "EMAIL", "ADDRESS", "PHONE NUMBER", "GENDER", "ROLE");
        for (User u : userList){
            System.out.printf("%10s | %20s | %15s | %15s | %15s | %20s | %20s | %15s | %10s | %10s\n", u.getId(), u.getName(),
                    u.getAccountName(), u.getPassword(), u.getDob(), u.getEmail(), u.getAddress(), u.getPhoneNumber(), u.getGender(), u.getRole());
        }
    }

    public static void main(String[] args) {
        UserView userView = new UserView();
        userView.launcher();
    }
}
