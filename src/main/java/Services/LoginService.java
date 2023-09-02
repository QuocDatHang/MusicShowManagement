package Services;

import Utils.PasswordUtils;

import static View.AdminView.adminMenu;
import static View.MainView.mainMenu;
import static View.UserView.userMenu;

public class LoginService {
    public static void checkAccount(String accountName, String password){
        String hashPassword = PasswordUtils.isValidAccountName(accountName);
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
                    userMenu();
                }
            }
            else {
                System.out.println("Your account name or password is not correct.");
                mainMenu();
            }
        }
    }
}
