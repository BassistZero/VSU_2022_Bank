package com.bassist_zero.Bank.Storage;

import com.bassist_zero.Bank.Domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    // MARK: - Private Properties

    private final List<User> users = new ArrayList<>();
    private static UserManager instance;
    private UserManager() { }

    // MARK: - Public Methods

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }

        return instance;
    }

    public void addUser(User user) throws Exception {
        if (users.stream().anyMatch(oldUser -> oldUser.getLogin().equals(user.getLogin()))) {
            throw new Exception();
        }
        users.add(user);
    }

    public User getUser(String login) {
        if (users.stream().anyMatch(savedUser -> savedUser.getLogin().equals(login))) {
            return users.stream().filter(user -> user.getLogin().equals(login)).findFirst().get();
        }
        return null;
    }

    public void authenticate(String login, String password) throws Exception {
        User currentUser = new User(login, password);

        if (users.stream().anyMatch(user -> user.equals(currentUser))) {
            return;
        }

        throw new Exception();
    }

}
