package com.bassist_zero.Bank.Storage;

import com.bassist_zero.Bank.Domain.Account;
import com.bassist_zero.Bank.Domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountManager {

    // MARK: - Private Properties

    private final List<Account> accounts = new ArrayList<>();
    private static AccountManager instance;
    private AccountManager() { }

    // MARK: - Public Methods

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

    public Optional<Account> getAccount(User user, String accountUid) {
        return accounts.stream()
                .filter(savedAccount -> savedAccount.getOwner().equals(user))
                .filter(savedAccount -> savedAccount.getUid().equals(accountUid))
                .findFirst();
    }

    public Optional<Account> getAccount(String login) {
        return accounts.stream()
                .filter(savedAccount -> savedAccount.getOwner().getLogin().equals(login))
                .findFirst();
    }

    public List<Account> getUserAccounts(User user) {
        return accounts.stream()
                .filter(account -> account.getOwner().equals(user))
                .collect(Collectors.toList());
    }

}
