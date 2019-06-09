package dao;

import model.User;

public interface UserDAO {
    boolean insert(User u);
    User selectByUserId(int id);
    int validateCredentials(String login, String password);


}
