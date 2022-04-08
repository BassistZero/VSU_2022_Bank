package com.bassist_zero.Bank.Domain;

import java.util.ArrayList;
import java.util.List;

public class Account {

    // MARK: - Private Properties

    private final User owner;
    private final String uid;
    private float money;
    private final List<Transaction> transactions = new ArrayList<>();

    // MARK: - Public Methods

    public Account(User user) {
        this.owner = user;
        this.uid = generateUid();
    }

    public User getOwner() {
        return owner;
    }

    public String getUid() {
        return uid;
    }

    public float getMoney() {
        return money;
    }

    public void getHistory() {
        transactions.forEach(transaction -> System.out.println(transaction + "\n"));
    }

    public void deposit(float money) {
        this.money += money;
    }

    public void withdraw(float money) {
        if (money <= this.money) {
            this.money -= money;
            return;
        }

        System.out.println();
        System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-");
        System.out.println("You don't have enough money to do that");
        System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-");
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    // MARK: - Formatting

    @Override
    public String toString() {
        return "Account{" +
                "id: '" + uid + '\'' +
                ", balance: '" + money + '\'' +
                '}';
    }

    // MARK: - Private Methods

    private String generateUid() {
        return String.valueOf(System.currentTimeMillis());
    }

}
