package com.bassist_zero.Bank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountManager {

    private final List<Account> accounts = new ArrayList<>();
    private static AccountManager instance;
    private AccountManager() { }

    public static AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
        }

        return instance;
    }


    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void deleteAccount(User user, String accountUid) {
        Account account = getUserAccounts(user).stream()
                .filter(a -> a.getUid().equals(accountUid))
                .findFirst()
                .orElse(null);

        accounts.remove(account);
    }

    public List<Account> getUserAccounts(User user) {
        return accounts.stream()
                .filter(account -> account.getOwner().equals(user))
                .collect(Collectors.toList());
    }
}
