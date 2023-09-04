package Services;

import Models.User;
import Utils.PasswordUtils;

import static View.AdminView.adminMenu;
import static View.MainView.mainMenu;
import static View.UserView.userMenu;

public class LoginService {
    private static final UserService userService = new UserService();
    public static void checkAccount(String accountName, String password){
        String hashPassword = PasswordUtils.isValidAccountName(accountName);
        User user = userService.findUserByAccountName(accountName);
        if (hashPassword == null){
            System.out.println("Your account name or password is not correct.");
            mainMenu();
        }
        else {
            if (PasswordUtils.isValidPassword(password, hashPassword)){
                if (accountName.equals("quocdathang")) {
                    adminMenu();
                }
                else {
                    userMenu(user.getIdUser());
                }
            }
            else {
                System.out.println("Your account name or password is not correct.");
                mainMenu();
            }
        }
    }
}
