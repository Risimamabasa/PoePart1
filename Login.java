package com.chatapp;

import java.util.regex.Pattern;

/**
 * Handles user registration and login validation for the chat application.
 *
 * Validation rules (as specified in the PoE brief):
 *  - Username: must contain an underscore and be no more than 5 characters long.
 *  - Password: at least 8 characters, containing at least one capital letter,
 *              one number, and one special character.
 *  - Cell phone number: must be a South African number, i.e. start with the
 *              international country code "+27" followed by the subscriber
 *              number (up to 10 characters total, per the brief).
 *
 * NOTE: Registration data is stored in static fields to represent a single
 * "current" registered account, since the brief describes creating one
 * account and then logging into that same account (not managing a list of
 * many users). This can be swapped out for a proper user store/database
 * later if Part 2 requires multiple accounts.
 */
public class Login {

    // --- Regex patterns used for validation ---
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}$");

    private static final Pattern CELL_PHONE_PATTERN =
            Pattern.compile("^\\+27\\d{9}$");

    // --- Static "storage" of the single registered account ---
    private static String registeredUsername;
    private static String registeredPassword;
    private static String registeredCellPhoneNumber;
    private static String registeredFirstName;
    private static String registeredLastName;
    private static boolean accountExists = false;

    // --- Instance fields for the account currently being registered/logged in ---
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    // Tracks the outcome of the most recent loginUser() call, so
    // returnLoginStatus() can report the right message afterwards.
    private boolean lastLoginSuccessful = false;

    /**
     * Constructor used for registering a new account.
     */
    public Login(String username, String password, String cellPhoneNumber,
                 String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Constructor used for attempting a login (only username + password needed).
     */
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Checks that the username contains an underscore and is no more than
     * five characters long.
     */
    public boolean checkUserName() {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    /**
     * Checks that the password meets the complexity rules:
     * at least 8 characters, one capital letter, one number, one special character.
     */
    public boolean checkPasswordComplexity() {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    /**
     * Checks that the cell phone number contains the South African
     * international country code (+27) followed by the subscriber number.
     */
    public boolean checkCellPhoneNumber() {
        return cellPhoneNumber != null && CELL_PHONE_PATTERN.matcher(cellPhoneNumber).matches();
    }

    /**
     * Registers the user if the username, password, and cell phone number
     * all pass validation. Returns the appropriate message for the first
     * validation failure encountered, or a success message if registration
     * succeeded.
     */
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that "
                    + "your username contains an underscore and is no more "
                    + "than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that "
                    + "the password contains at least eight characters, a "
                    + "capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not "
                    + "contain international code; please correct the "
                    + "number and try again.";
        }

        // All checks passed - persist this as the registered account.
        registeredUsername = this.username;
        registeredPassword = this.password;
        registeredCellPhoneNumber = this.cellPhoneNumber;
        registeredFirstName = this.firstName;
        registeredLastName = this.lastName;
        accountExists = true;

        return "Username successfully captured. Password successfully "
                + "captured. Cell phone number successfully added. "
                + "You have successfully registered!";
    }

    /**
     * Verifies that the username and password entered for login match the
     * details stored when the user registered.
     */
    public boolean loginUser() {
        lastLoginSuccessful = accountExists
                && registeredUsername.equals(this.username)
                && registeredPassword.equals(this.password);
        return lastLoginSuccessful;
    }

    /**
     * Returns the appropriate welcome/error message based on the outcome
     * of the most recent loginUser() call.
     */
    public String returnLoginStatus() {
        if (lastLoginSuccessful) {
            return "Welcome " + registeredFirstName + ", " + registeredLastName
                    + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // --- Getters (useful for testing / the console app) ---
    public String getUsername() {
        return username;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }
}
