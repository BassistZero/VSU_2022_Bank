package com.bassist_zero.Bank.Operation;

import com.bassist_zero.Bank.Domain.Account;
import com.bassist_zero.Bank.Storage.AccountManager;
import com.bassist_zero.Bank.Storage.UserManager;
import com.bassist_zero.Bank.Domain.Transaction;
import com.bassist_zero.Bank.Domain.User;

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

        if (amount <= 0) {
            System.out.println();
            System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-");
            System.out.println("Amount should be positive");
            System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-");
            return;
        }

        Account account = getAccount(user, uid);

        account.deposit(amount);
        account.addTransaction(new Transaction(user, user, amount, new Date()));
    }

    private void withdraw(User user, String uid) {
        System.out.println();
        System.out.println("Enter the amount:");

        float amount = new Scanner(System.in).nextFloat();

        if (amount <= 0) {
            System.out.println();
            System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-");
            System.out.println("Amount should be positive");
            System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-");
            return;
        }

        Account account = getAccount(user, uid);

        account.withdraw(amount);
        account.addTransaction(new Transaction(user, user, amount, new Date()));
    }

    private void transfer(User user, String uid) {
        System.out.println();
        System.out.println("Enter the amount:");

        float amount = new Scanner(System.in).nextFloat();

        System.out.println();
        System.out.println("Enter the receiver");
        String receiverLogin = new Scanner(System.in).next();

        Account senderAccount = getAccount(user, uid);
        Optional<Account> receiverAccount = getAccount(receiverLogin);

        if (receiverAccount.isEmpty()) {
            System.out.println();
            System.out.println("-:-:-:-:-:-:-");
            System.out.println("No such User");
            System.out.println("-:-:-:-:-:-:-");

            return;
        }

        senderAccount.withdraw(amount);
        receiverAccount.get().deposit(amount);

        Transaction transaction = new Transaction(user, userManager.getUser(receiverLogin), amount, new Date());

        senderAccount.addTransaction(transaction);
        receiverAccount.get().addTransaction(transaction);
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

    private Optional<Account> getAccount(String login) {
        Optional<Account> account = accountManager.getAccount(login);

        return account;
    }

}
