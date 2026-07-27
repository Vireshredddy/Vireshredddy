package com.ems.service;

import java.util.Scanner;

import com.ems.service.AuthenticationService;
import com.ems.service.EmployeeService;

public class EMSApplication {

    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        welcomeScreen();

        AuthenticationService auth = new AuthenticationService();

        boolean loginStatus = auth.login();


        if (loginStatus) {

            dashboard();

        } else {

            System.out.println("Unable to login. Application Closed.");

        }


        sc.close();

    }


    // Welcome Screen

    public static void welcomeScreen() {


        System.out.println("\nWelcome to Employee Management System");
        System.out.println("Please Login to Continue...");

    }


    // Dashboard Menu

    public static void dashboard() {


        EmployeeService employeeService = new EmployeeService();


        int choice;


        do {


            System.out.println("\n===================================");
            System.out.println("             DASHBOARD");
            System.out.println("===================================");

            System.out.println("1. Employee Management");
            System.out.println("2. Reports");
            System.out.println("3. Logout");
            System.out.println("4. Exit Application");

            System.out.println("===================================");


            System.out.print("Enter your choice : ");

            choice = sc.nextInt();

            sc.nextLine();


            switch (choice) {


                case 1:

                    employeeManagement(employeeService);

                    break;


                case 2:

                    System.out.println(
                            "\nReports Module Coming Soon.");

                    break;


                case 3:

                    System.out.println(
                            "\nLogout Successful.");

                    AuthenticationService auth =
                            new AuthenticationService();


                    if (auth.login()) {

                        continue;

                    } else {

                        return;

                    }


                case 4:

                    System.out.println(
                            "\nThank you for using EMS.");

                    System.out.println(
                            "Application Closed.");

                    System.exit(0);

                    break;


                default:

                    System.out.println(
                            "Invalid Choice. Try Again.");

            }


        } while (true);


    }


    // Employee Management Menu

    public static void employeeManagement(
            EmployeeService service) {


        int choice;


        do {


            System.out.println("\n===================================");
            System.out.println("       EMPLOYEE MANAGEMENT");
            System.out.println("===================================");


            System.out.println("1. Register Employee");
            System.out.println("2. Search Employee");
            System.out.println("3. Show All Employees");
            System.out.println("4. Delete Employee");
            System.out.println("5. Back to Dashboard");


            System.out.println("===================================");


            System.out.print("Enter Choice : ");

            choice = sc.nextInt();

            sc.nextLine();


            switch (choice) {


                case 1:

                    service.registerEmployee();

                    break;


                case 2:

                    service.searchEmployee();

                    break;


                case 3:

                    service.showEmployees();

                    break;


                case 4:

                    service.deleteEmployee();

                    break;


                case 5:

                    System.out.println(
                            "Returning to Dashboard...");

                    break;


                default:

                    System.out.println(
                            "Invalid Choice.");

            }


        } while (choice != 5);


    }

}