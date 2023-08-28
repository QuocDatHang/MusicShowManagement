package Services;

import Models.User;
import Utils.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class UserService implements IUserService {
    String fileUser = "./data/Users.txt";

    @Override
    public void createUser(User user) {
        List<User> userList = getAllUsers();
        userList.add(user);
        FileUtils.writeData(fileUser, userList);
    }

    @Override
    public List<User> getAllUsers() {
        return FileUtils.readData(fileUser);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(long id) {

    }

    public long nextIdUser(){
        long maxIdUser = 10000;
        List<User> userList = getAllUsers();
        for (User u : userList){
            if (u.getId() > maxIdUser){
                maxIdUser = u.getId();
            }
        }
        return maxIdUser + 1;
    }
}
