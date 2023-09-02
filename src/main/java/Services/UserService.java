package Services;

import Models.Admin;
import Models.User;
import Utils.FileUtils;
import Utils.PasswordUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UserService implements IUserService {
    String fileUser = "./data/Users.txt";

    @Override
    public void createUser(User user) {
        List<User> userList = getAllUsers();
        userList.add(user);
        FileUtils.writeData(fileUser, userList);
        System.out.println("Create user successful!");
    }

    @Override
    public List<User> getAllUsers() {
        return FileUtils.readData(fileUser, User.class);
    }

    @Override
    public void updateUser(User user) {
        List<User> userList = getAllUsers();
        for (User u : userList){
            if (u.getIdUser() == user.getIdUser()){
                u.setName(user.getName());
                u.setAccountName((user.getAccountName()));
                u.setPassword(user.getPassword());
                u.setDob(user.getDob());
                u.setEmail(user.getEmail());
                u.setAddress(user.getAddress());
                u.setPhoneNumber(user.getPhoneNumber());
                u.setGender(user.getGender());
                u.setRole(user.getRole());
                break;
            }
        }
        FileUtils.writeData(fileUser, userList);
        System.out.println("Edit successful!");
    }

    @Override
    public void deleteUser(long id) {
        List<User> userList = getAllUsers();
        int count = 0;
        for (User u : userList){
            if (u.getIdUser() == id){
                userList.remove(u);
                FileUtils.writeData(fileUser, userList);
                System.out.println("Delete successful!");
                count++;
                break;
            }
        }
        if (count == 0) System.out.println("Cannot find id to delete!");
    }

    public User findUserById(long id){
        List<User> userList = getAllUsers();
        for (User u : userList){
            if (u.getIdUser() == id){
                return u;
            }
        }
        return null;
    }

    public long nextIdUser(){
        long maxIdUser = 10000;
        List<User> userList = getAllUsers();
        for (User u : userList){
            if (u.getIdUser() > maxIdUser){
                maxIdUser = u.getIdUser();
            }
        }
        return maxIdUser + 1;
    }

}