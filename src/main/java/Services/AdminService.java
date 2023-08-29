package Services;

import Models.Admin;
import Utils.FileUtils;
import Utils.PasswordUtils;

import java.io.FileWriter;
import java.io.IOException;

public class AdminService {
    private static String fileAdmin = "./data/Admin.txt";
    public static void main(String[] args) throws IOException {
        Admin admin = new Admin("quocdathang", PasswordUtils.generatePassword("thisisdat123"));
        FileWriter fileWriter = new FileWriter(fileAdmin);
        fileWriter.write(admin.toString());
        fileWriter.flush();
    }
}