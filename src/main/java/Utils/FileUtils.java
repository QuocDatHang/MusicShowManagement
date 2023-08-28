package Utils;

import Models.EGender;
import Models.ERole;
import Models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<User> readData(String fileUser) {
        List<User> userList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileUser);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] str = line.split(",");
                User user = new User(Long.parseLong(str[0]), str[1], str[2], str[3], DateUtils.parseDate(str[4]),
                        str[5], str[6], str[7], EGender.valueOf(str[8]), ERole.valueOf(str[9]));
                userList.add(user);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public static void writeData(String fileUser, List<User> userList){
        try {
            FileWriter fileWriter = new FileWriter(fileUser);
            for (User u : userList) {
                fileWriter.write(u.toString() + "\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
