package cz.uhk.cviceni22.model;

import java.util.List;

public interface UserDAO {
    User getUserById(int id);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    List<User> getAllUsers();
    List<User> getAllUsersWhereName(String name);
}
