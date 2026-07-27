package com.ems.service;

import java.util.Scanner;



public class EmployeeService {

    Scanner sc = new Scanner(System.in);

    // Array storage
    Employee[] employees = new Employee[100];

    int count = 0;

    int employeeNumber = 1;


    // Register Employee

    public void registerEmployee() {

        if (count == employees.length) {

            System.out.println("Employee storage is full.");
            return;
        }


        Employee emp = new Employee();


        // Auto Generate Employee ID

        emp.setEmployeeId("EMP" + employeeNumber++);


        System.out.print("Enter Employee Name : ");
        emp.setName(sc.nextLine());


        System.out.print("Enter Age : ");
        emp.setAge(Integer.parseInt(sc.nextLine()));


        System.out.print("Enter Gender : ");
        emp.setGender(sc.nextLine());


        System.out.print("Enter Email : ");
        emp.setEmail(sc.nextLine());


        System.out.print("Enter Mobile Number : ");
        emp.setMobile(sc.nextLine());



        // Department Selection

        System.out.println("\nDepartments");
        System.out.println("1. Software Development");
        System.out.println("2. Quality Assurance");
        System.out.println("3. Human Resources");
        System.out.println("4. Project Management");

        System.out.print("Select Department : ");

        int deptChoice = Integer.parseInt(sc.nextLine());


        switch(deptChoice) {

            case 1:
                emp.setDepartment("Software Development");
                break;

            case 2:
                emp.setDepartment("Quality Assurance");
                break;

            case 3:
                emp.setDepartment("Human Resources");
                break;

            case 4:
                emp.setDepartment("Project Management");
                break;

            default:
                emp.setDepartment("Not Assigned");

        }



        // Employee Type Selection

        System.out.println("\nEmployee Types");

        System.out.println("1. Developer");
        System.out.println("2. Tester");
        System.out.println("3. HR Executive");
        System.out.println("4. Manager");

        System.out.print("Select Employee Type : ");

        int typeChoice = Integer.parseInt(sc.nextLine());


        switch(typeChoice) {

            case 1:
                emp.setEmployeeType("Developer");
                break;

            case 2:
                emp.setEmployeeType("Tester");
                break;

            case 3:
                emp.setEmployeeType("HR Executive");
                break;

            case 4:
                emp.setEmployeeType("Manager");
                break;

            default:
                emp.setEmployeeType("Not Assigned");

        }



        System.out.print("Enter Salary : ");

        emp.setSalary(Double.parseDouble(sc.nextLine()));



        System.out.print("Enter Joining Date : ");

        emp.setJoiningDate(sc.nextLine());



        // Status Selection

        System.out.println("\nEmployment Status");

        System.out.println("1. Active");
        System.out.println("2. Inactive");
        System.out.println("3. Suspended");

        System.out.print("Select Status : ");

        int statusChoice = Integer.parseInt(sc.nextLine());


        switch(statusChoice) {

            case 1:
                emp.setStatus("Active");
                break;

            case 2:
                emp.setStatus("Inactive");
                break;

            case 3:
                emp.setStatus("Suspended");
                break;

            default:
                emp.setStatus("Active");

        }



        // Store Employee

        employees[count] = emp;

        count++;


        System.out.println("\nEmployee Registered Successfully.");

        System.out.println("Generated Employee ID : "
                + emp.getEmployeeId());

    }




    // Search Employee

    public void searchEmployee() {


        System.out.print("Enter Employee ID : ");

        String id = sc.nextLine();


        boolean found = false;


        for(int i = 0; i < count; i++) {


            if(employees[i].getEmployeeId()
                    .equalsIgnoreCase(id)) {


                System.out.println("\nEmployee Found");

                employees[i].displayEmployee();

                found = true;

                break;

            }

        }


        if(!found) {

            System.out.println("Employee Not Found.");

        }

    }




    // Show All Employees

    public void showEmployees() {


        if(count == 0) {

            System.out.println("No Employee Records Available.");

            return;

        }



        System.out.println("\n========== Employee List ==========");



        for(int i = 0; i < count; i++) {


            employees[i].displayEmployee();

        }

    }




    // Delete Employee

    public void deleteEmployee() {


        System.out.print("Enter Employee ID : ");

        String id = sc.nextLine();


        boolean found = false;



        for(int i = 0; i < count; i++) {



            if(employees[i].getEmployeeId()
                    .equalsIgnoreCase(id)) {



                // Shift remaining employees

                for(int j = i; j < count - 1; j++) {

                    employees[j] = employees[j + 1];

                }



                employees[count - 1] = null;


                count--;


                found = true;


                System.out.println(
                        "Employee Deleted Successfully.");

                break;

            }

        }



        if(!found) {

            System.out.println("Employee Not Found.");

        }


    }
    public Employee[] getEmployees() {

        return employees;

    }


    public int getCount() {

        return count;

    }

}