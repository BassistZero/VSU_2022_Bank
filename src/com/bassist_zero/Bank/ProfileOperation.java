package com.bassist_zero.Bank;

import java.util.Optional;
import java.util.Scanner;

public enum ProfileOperation {
    create,
    delete,
    getAccount,
    getAccounts;

    // MARK: - Private Properties

    private final AccountManager accountManager = AccountManager.getInstance();

    // MARK: - Public Methods

    public void execute(User user, String uid) {
        switch (this) {
            case create -> createAccount(user);
            case delete -> deleteAccount(user);
            case getAccount -> getAccount(user, uid);
            case getAccounts ->  getAccounts(user);
        }
    }

    // MARK: - Configuration

    private void createAccount(User user) {
        Account account = new Account(user);
        accountManager.addAccount(account);
    }

    private void deleteAccount(User user) {
        System.out.println();
        System.out.println("Type the id of the account you want to delete:");

        getAccounts(user);

        String uid = new Scanner(System.in).next();

        accountManager.deleteAccount(user, uid);

        System.out.println();
        System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-:-");
        System.out.println("Account Successfully Deleted");
        System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-:-");
    }

    private Optional<Account> getAccount(User user, String uid) {
        return accountManager.getAccount(user , uid);
    }

    private void getAccounts(User user) {
        if (accountManager.getUserAccounts(user).isEmpty()) {
            System.out.println("No Accounts Yet");
            return;
        }

        accountManager.getUserAccounts(user).stream()
                .map(Account::toString)
                .forEach(System.out::println);
    }

}
