package Services;

import Models.User;

import java.util.List;

public interface IUserService {
    void createUser(User user);
    List<User> getAllUsers();
    abstract void updateUser(User user);
    public void deleteUser(long id);
    public long nextIdUser();
}
