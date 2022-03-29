package com.bassist_zero.Bank;

import java.util.Scanner;

public enum Operation {
    create,
    delete,
    deposit,
    withdraw,
    transfer,
    balance,
    history,
    all;

    private final AccountManager accountManager = AccountManager.getInstance();

    public void execute(User user) {
        switch (this) {
            case create -> createAccount(user);
            case delete -> deleteAccount(user);
            case deposit -> depositAccount(user);
            case withdraw -> withdrawAccount(user);
            case transfer -> transferAccount(user);
            case balance -> balanceAccount(user);
            case history -> historyAccount(user);
            case all ->  allAccounts(user);
        }
    }

    private void createAccount(User user) {
        Account account = new Account(user);
        accountManager.addAccount(account);
    }

    private void deleteAccount(User user) {
        System.out.println();
        System.out.println("Type a uid of the account you want to delete:");

        allAccounts(user);

        String uid = new Scanner(System.in).next();

        accountManager.deleteAccount(user, uid);

        System.out.println();
        System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-:-");
        System.out.println("Account Successfully Deleted");
        System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-:-");
    }

    private void depositAccount(User user) {

    }

    private void withdrawAccount(User user) {

    }

    private void transferAccount(User user) {

    }

    private void balanceAccount(User user) {

    }

    private void historyAccount(User user) {

    }

    private void allAccounts(User user) {
        if (accountManager.getUserAccounts(user).isEmpty()) {
            System.out.println("No Accounts Yet");
            return;
        }

        accountManager.getUserAccounts(user).stream()
                .map(Account::toString)
                .forEach(System.out::println);
    }
}
