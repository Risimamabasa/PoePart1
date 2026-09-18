package com.chatapp;

import java.util.Scanner;

/**
 * Console entry point for Part 1: Registration and Login.
 * This is a console-only application (no GUI), as required by the brief.
 */
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean registered = false;

        System.out.println("=== Welcome to the Chat App ===");
        System.out.println("Please register an account to get started.\n");

        // --- Registration loop: keep trying until registration succeeds ---
        while (!registered) {
            System.out.print("Enter a username: ");
            String username = scanner.nextLine();

            System.out.print("Enter a password: ");
            String password = scanner.nextLine();

            System.out.print("Enter your South African cell phone number (e.g. +27831234567): ");
            String cellPhoneNumber = scanner.nextLine();

            System.out.print("Enter your first name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter your last name: ");
            String lastName = scanner.nextLine();

            Login registration = new Login(username, password, cellPhoneNumber, firstName, lastName);
            String message = registration.registerUser();
            System.out.println("\n" + message + "\n");

            if (message.contains("successfully registered")) {
                registered = true;
            }
        }

        // --- Login loop ---
        System.out.println("Please log in to your account.\n");
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.print("Enter your username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter your password: ");
            String loginPassword = scanner.nextLine();

            Login loginAttempt = new Login(loginUsername, loginPassword);
            loggedIn = loginAttempt.loginUser();
            System.out.println("\n" + loginAttempt.returnLoginStatus() + "\n");
        }

        System.out.println("=== Login complete. Chat features coming in later parts of the PoE. ===");
        scanner.close();
    }
}
