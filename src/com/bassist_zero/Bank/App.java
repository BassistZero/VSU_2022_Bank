package com.bassist_zero.Bank;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class App {

    private final Scanner input = new Scanner(System.in);
    private Menu menu = Menu.main;
    private Menu previousMenu = Menu.main;

    private final ArrayList<User> users = new ArrayList<>();

    public void on() {
        users.add(new User("root", "root"));
        users.add(new User("test", "123"));

        while (true) {
            configureUI(menu);
        }
    }

    private void configureUI(Menu menu) {
        menu.showUI();

        switch (menu) {
            case main -> configureMainMenu();
            case signIn -> configureSignInMenu();
            case signUp -> configureSignUpMenu();
            case exit -> configureExitMenu();
            case error -> { configureErrorMenu(); return; }
            case profile -> configureProfileMenu();
            case profileNotFound -> configureProfileNotFoundMenu();
            case profileAlreadyExists -> configureProfileAlreadyExistsMenu();
        }

        previousMenu = menu;
    }

    private void configureMainMenu() {
        switch (input.nextInt()) {
            case 1 -> this.menu = Menu.signIn;
            case 2 -> this.menu = Menu.signUp;
            case 0 -> this.menu = Menu.exit;
            default -> this.menu = Menu.error;
        }
    }

    private void configureSignInMenu() {
        System.out.println("Enter Your Login:");
        String login = input.next();

        System.out.println("Enter Your Password:");
        String password = input.next();

        User currentUser = new User(login, password);

        if (users.stream().anyMatch(user -> Objects.equals(user, currentUser))) {
            this.menu = Menu.profile;
        } else {
            this.menu = Menu.profileNotFound;
        }
    }

    private void configureSignUpMenu() {
        System.out.println("Enter Your New Login:");
        String login = input.next();

        System.out.println("Enter Your New Password:");
        String password = input.next();

        User newUser = new User(login, password);

        if (users.stream().anyMatch(user -> Objects.equals(user.login, newUser.login))) {
            this.menu = Menu.profileAlreadyExists;
        } else {
            users.add(newUser);
            this.menu = Menu.profile;

            System.out.println();
            System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-:-");
            System.out.println("Profile successfully created");
            System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-:-");
        }
    }

    private void configureExitMenu() {
        System.exit(0);
    }

    private void configureErrorMenu() {
        this.menu = previousMenu;
    }

    private void configureProfileMenu() {
        switch (input.nextInt()) {
            case 1 -> createAccount();
            case 2 -> deleteAccount();
            case 3 -> showAllAccounts();
            case 0 -> this.menu = Menu.main;
            default -> this.menu = Menu.error;
        }
    }

    private void configureProfileNotFoundMenu() {
        configureMainMenu();
    }

    private void configureProfileAlreadyExistsMenu() {
        this.menu = Menu.signUp;
    }

    private void createAccount() {
        System.out.println("Account created");
    }

    private void deleteAccount() {
        System.out.println("Account deleted");
    }

    private void showAllAccounts() {
        System.out.println("*All Accounts*");
    }

}
