package View;

import Services.IUserService;
import Services.UserService;

import java.util.Scanner;

public class UserView {
    private final Scanner scanner = new Scanner(System.in);
    private IUserService iUserService;

    UserView() {
        iUserService = new UserService();
    }





    public static void main(String[] args) {
        UserView userView = new UserView();
    }
}