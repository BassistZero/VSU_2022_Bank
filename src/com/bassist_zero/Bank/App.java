package com.bassist_zero.Bank;

import com.bassist_zero.Bank.Domain.Account;
import com.bassist_zero.Bank.Domain.User;
import com.bassist_zero.Bank.Operation.AccountOperation;
import com.bassist_zero.Bank.Operation.ProfileOperation;
import com.bassist_zero.Bank.Storage.AccountManager;
import com.bassist_zero.Bank.Storage.UserManager;

import java.util.Optional;
import java.util.Scanner;

public class App {

    // MARK: - Private Properties

    private final Scanner input = new Scanner(System.in);
    private Menu menu = Menu.main;
    private Menu previousMenu;
    private final UserManager userManager = UserManager.getInstance();
    private User user;
    private String uid;

    // MARK: - Public Methods

    public void on() {
        while (true) {
            configureUI(menu);
        }
    }

    // MARK: - UI

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
            case account -> configureAccountMenu(user, uid);
        }

        previousMenu = menu;
    }

    // MARK: - Configuration

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
            userManager.authenticate(login, password);
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
            userManager.addUser(newUser);
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
            case 4 -> showAccount(user);
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

    private void configureAccountMenu(User user, String uid) {
        switch (input.nextInt()) {
            case 1 -> deposit(user, uid);
            case 2 -> withdraw(user, uid);
            case 3 -> transfer(user, uid);
            case 4 -> balance(user, uid);
            case 5 -> history(user, uid);
            case 0 -> this.menu = Menu.profile;
            default -> this.menu = Menu.error;
        }
    }

    // MARK: - Private Methods

    private void createAccount(User user) {
        ProfileOperation.create.execute(user, null);

        System.out.println();
        System.out.println("-:-:-:-:-:-:-:-");
        System.out.println("Account Created");
        System.out.println("-:-:-:-:-:-:-:-");
    }

    private void deleteAccount(User user) {
        ProfileOperation.delete.execute(user, null);

        System.out.println();
        System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-:-");
        System.out.println("Account Successfully Deleted");
        System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-:-");
    }

    private void showAllAccounts(User user) {
        System.out.println();
        System.out.println("-:-:-:-:-:-:-:-");
        System.out.println("Accounts List:");

        ProfileOperation.getAccounts.execute(user, null);
        System.out.println("-:-:-:-:-:-:-:-");
    }

    private void showAccount(User user) {
        if(AccountManager.getInstance().getUserAccounts(user).isEmpty()) {
            System.out.println();
            System.out.println("-:-:-:-:-:-:-:-:-:-:-");
            System.out.println("You have no accounts");
            System.out.println("-:-:-:-:-:-:-:-:-:-:-");

            return;
        } else if(AccountManager.getInstance().getUserAccounts(user).size() == 1) {
            this.menu = Menu.account;
            this.uid = AccountManager.getInstance().getUserAccounts(user).stream().findFirst().get().getUid();
            return;
        }

        showAllAccounts(user);

        System.out.println();
        System.out.println("Type the id of the account:");

        this.uid = input.next();

        Optional<Account> account = AccountManager.getInstance().getAccount(user, uid);

        if(account.isEmpty()) {
            System.out.println();
            System.out.println("-:-:-:-:-:-:-:-");
            System.out.println("No such account");
            System.out.println("-:-:-:-:-:-:-:-");
        } else {
            this.menu = Menu.account;
        }
    }

    private void deposit(User user, String uid) {
        AccountOperation.deposit.execute(user, uid);
    }

    private void withdraw(User user, String uid) {
        AccountOperation.withdraw.execute(user, uid);
    }

    private void transfer(User user, String uid) {
        AccountOperation.transfer.execute(user, uid);
    }

    private void balance(User user, String uid) {
        AccountOperation.balance.execute(user, uid);
    }

    private void history(User user, String uid) {
        AccountOperation.history.execute(user, uid);
    }

}
