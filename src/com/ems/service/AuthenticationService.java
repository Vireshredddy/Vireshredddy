package com.ems.service;

import java.util.Scanner;

public class AuthenticationService {

    private final String USERNAME = "viresh";
    private final String PASSWORD = "viresh@46";

    private final int MAX_ATTEMPTS = 3;

    public boolean login() {

        Scanner sc = new Scanner(System.in);
        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {

            System.out.println("\n========== LOGIN ==========");

            System.out.print("Username : ");
            String username = sc.nextLine();

            System.out.print("Password : ");
            String password = sc.nextLine();

            if (username.isBlank() || password.isBlank()) {
                System.out.println("Username and Password cannot be empty.");
                continue;
            }

            if (username.equals(USERNAME) && password.equals(PASSWORD)) {
                System.out.println("\nLogin Successful.");
                return true;
            } else {
                attempts++;
                System.out.println("Invalid Username or Password.");
                System.out.println("Remaining Attempts : " + (MAX_ATTEMPTS - attempts));
            }
        }

        System.out.println("\nMaximum Login Attempts Reached.");
        System.out.println("Application Closed.");

        return false;
    }
}