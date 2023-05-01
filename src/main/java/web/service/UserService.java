package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    void remove(User user);
    void change(User user);
    User getUserById(int id);
}
