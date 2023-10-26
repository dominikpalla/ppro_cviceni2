package cz.uhk.cviceni22.model;

import java.util.List;

public interface UserDAO {
    User getUserById(int id);
    List<User> getAllUsers();
    List<User> getAllUsersWhereName(String name);
    void saveUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
}
