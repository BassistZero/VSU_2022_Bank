package com.bassist_zero.Bank;

public enum Menu {
    main,
    signIn,
    signUp,
    exit,
    profile,
    profileNotFound,
    profileAlreadyExists,
    error;

    public void showUI() {
        System.out.println();

        switch (this) {
            case main -> showMain();
            case signIn -> showSignIn();
            case signUp -> showSignUp();
            case profile -> showProfile();
            case profileNotFound -> showProfileNotFound();
            case profileAlreadyExists -> showProfileAlreadyExists();
            case exit -> showExit();
            case error -> showError();
        }
    }

    private void showMain() {
        System.out.println("-- Main Menu --");
        System.out.println("1: Sign In");
        System.out.println("2: Sign Up");
        System.out.println("0: Exit");
    }

    private void showSignIn() {
        System.out.println("-- Sign In Menu --");
    }

    private void showSignUp() {
        System.out.println("-- Registration Menu --");
    }

    private void showProfile() {
        System.out.println("-- Profile Menu --");
        System.out.println("1: Create new account");
        System.out.println("2: Delete account");
        System.out.println("3: Show all accounts");
        System.out.println("0: Log Out");
    }

    private void showProfileNotFound() {
        System.out.println("No Such Profile, Try This:");
        System.out.println("1: Sign In Again");
        System.out.println("2: Sign Up");
        System.out.println("0: Exit");
    }

    private void showExit() {
        System.out.println("-- See You Soon! --");
    }

    private void showError() {
        System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-");
        System.out.println("No Such Command. Please, try again");
        System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-");
    }

    private void showProfileAlreadyExists() {
        System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-");
        System.out.println("The Login Already exists. Please, try the new one");
        System.out.println("-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-:-");
    }
}