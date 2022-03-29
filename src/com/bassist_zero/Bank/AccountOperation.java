package com.bassist_zero.Bank;

import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public enum AccountOperation {
    deposit,
    withdraw,
    transfer,
    balance,
    history;

    // MARK: - Private Properties

    private final AccountManager accountManager = AccountManager.getInstance();
    private final UserManager userManager = UserManager.getInstance();

    // MARK: - Public Methods

    public void execute(User user, String uid) {
        switch (this) {
            case deposit -> deposit(user, uid);
            case withdraw -> withdraw(user, uid);
            case transfer -> transfer(user, uid);
            case balance -> balance(user, uid);
            case history -> history(user, uid);
        }
    }

    // MARK: - Configuration

    private void deposit(User user, String uid) {
        System.out.println();
        System.out.println("Enter the amount:");

        float amount = new Scanner(System.in).nextFloat();

        Account account = getAccount(user, uid);

        account.deposit(amount);
        account.addTransaction(new Transaction(user, null, amount, new Date()));
    }

    private void withdraw(User user, String uid) {
        System.out.println();
        System.out.println("Enter the amount:");

        float amount = new Scanner(System.in).nextFloat();

        Account account = getAccount(user, uid);

        account.withdraw(amount);
        account.addTransaction(new Transaction(user, null, amount, new Date()));
    }

    private void transfer(User user, String uid) {
        System.out.println();
        System.out.println("Enter the amount:");

        float amount = new Scanner(System.in).nextFloat();

        System.out.println();
        System.out.println("Enter the receiver");
        String receiverLogin = new Scanner(System.in).next();

        Account senderAccount = getAccount(user, uid);
        Account receiverAccount = getAccount(receiverLogin);

        senderAccount.withdraw(amount);
        receiverAccount.deposit(amount);

        Transaction transaction = new Transaction(user, userManager.getUser(receiverLogin), amount, new Date());

        senderAccount.addTransaction(transaction);
        receiverAccount.addTransaction(transaction);
    }

    private void balance(User user, String uid) {
        System.out.println();
        System.out.println("-:-:-:-:-:-:-:-:-:-");
        System.out.println("Your balance is: " + getAccount(user, uid).getMoney());
        System.out.println("-:-:-:-:-:-:-:-:-:-");
    }

    private void history(User user, String uid) {
        getAccount(user, uid).getHistory();
    }

    // MARK: - Private Methods

    private Account getAccount(User user, String uid) {
        Optional<Account> account = accountManager.getAccount(user, uid);

        return account.get();
    }

    private Account getAccount(String login) {
        Optional<Account> account = accountManager.getAccount(login);

        return account.get();
    }

}
