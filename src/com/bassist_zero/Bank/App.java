package com.bassist_zero.Bank;

import java.util.Scanner;

public class App {

    private final Scanner input = new Scanner(System.in);
    private Menu menu = Menu.main;
    private Menu previousMenu;
    private final UserManager userStore = UserManager.getInstance();
    private User user;

    public void on() {
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
            case profile -> configureProfileMenu(user);
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

        try {
            userStore.authenticate(login, password);
            this.menu = Menu.profile;
            this.user = new User(login, password);

        } catch (Exception exception) {
            this.menu = Menu.profileNotFound;
        }
    }

    private void configureSignUpMenu() {
        System.out.println("Enter Your New Login:");
        String login = input.next();

        System.out.println("Enter Your New Password:");
        String password = input.next();

        User newUser = new User(login, password);

        try {
            userStore.addUser(newUser);
            this.menu = Menu.profile;
            this.user = newUser;

            System.out.println();
            System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-:-");
            System.out.println("Profile successfully created");
            System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-:-");
        } catch (Exception exception) {
            this.menu = Menu.profileAlreadyExists;
        }
    }

    private void configureExitMenu() {
        System.exit(0);
    }

    private void configureErrorMenu() {
        this.menu = previousMenu;
    }

    private void configureProfileMenu(User user) {
        switch (input.nextInt()) {
            case 1 -> createAccount(user);
            case 2 -> deleteAccount(user);
            case 3 -> showAllAccounts(user);
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

    private void createAccount(User user) {
        Operation.create.execute(user);

        System.out.println();
        System.out.println("-:-:-:-:-:-:-:-");
        System.out.println("Account Created");
        System.out.println("-:-:-:-:-:-:-:-");
    }

    private void deleteAccount(User user) {
        Operation.delete.execute(user);
    }

    private void showAllAccounts(User user) {
        System.out.println();
        System.out.println("-:-:-:-:-:-:-:-");
        System.out.println("Accounts List:");
        System.out.println("-:-:-:-:-:-:-:-");

        Operation.all.execute(user);
    }

}
