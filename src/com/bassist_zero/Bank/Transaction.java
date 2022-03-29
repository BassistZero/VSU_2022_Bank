package com.bassist_zero.Bank;

import java.util.Date;

public class Transaction {

    private User from;
    private User to;
    private float amount;
    private Date date;

    public Transaction(User from, User to, float amount, Date date) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.date = date;
    }

}
