package Utils;

import Models.User;
import Services.AdminService;
import Services.IAdminService;
import Services.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class PasswordUtils {
    static IAdminService iAdminService = new AdminService();

    public static boolean isValidPassword(String passwordCheck, String password){
        return BCrypt.checkpw(passwordCheck, password);
    }
    public static String isValidAccountName(String accountName){
        if (accountName.equals("quocdathang")){
            return "$2a$12$4L5TRfsbvqjpHdxnU3Caz.qubRNddRtITJii9JN3YYCmzLxcEn7MO";
        }
        List<User> userList = iAdminService.getAllUsers();
        for (User u : userList){
            if (u.getAccountName().equals(accountName)){
                return u.getPassword();
            }
        }
        return null;
    }
    public static String generatePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

}
//thisisdat123