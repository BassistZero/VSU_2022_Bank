package com.bassist_zero.Bank;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private final List<User> users = new ArrayList<>();
    private static UserManager instance;
    private UserManager() { }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }

        return instance;
    }


    public void addUser(User user) throws Exception {
        if (users.stream().anyMatch(oldUser -> oldUser.login.equals(user.login))) {
            throw new Exception();
        }
        users.add(user);
    }

    public void authenticate(String login, String password) throws Exception {
        User currentUser = new User(login, password);

        if (users.stream().anyMatch(user -> user.equals(currentUser))) {
            return;
        }

        throw new Exception();
    }

}
