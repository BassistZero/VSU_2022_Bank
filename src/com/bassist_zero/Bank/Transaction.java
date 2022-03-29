package com.bassist_zero.Bank;

import java.util.Date;

public class Transaction {

    // MARK: - Private Properties

    private final User from;
    private final User to;
    private final float amount;
    private final Date date;

    // MARK: - Public Methods

    public Transaction(User from, User to, float amount, Date date) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.date = date;
    }

    // MARK: - Formatting

    @Override
    public String toString() {
        String safeLogin;

        if (to == null) {
            safeLogin = "Nobody";
        } else {
            safeLogin = to.getLogin();
        }

        return "from: " + from.getLogin() +
                ", to: " + safeLogin +
                ", amount: " + amount +
                ", on: " + date +
                '}';
    }

}
