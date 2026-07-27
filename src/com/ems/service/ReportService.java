package com.ems.service;



public class ReportService {


    // Generate All Reports

    public void generateReports(Employee[] employees, int count) {


        if (count == 0) {

            System.out.println("No Employee Data Available.");

            return;
        }


        int active = 0;
        int inactive = 0;
        int suspended = 0;


        int software = 0;
        int qa = 0;
        int hr = 0;
        int project = 0;


        int developer = 0;
        int tester = 0;
        int hrExecutive = 0;
        int manager = 0;


        double highestSalary = employees[0].getSalary();
        double lowestSalary = employees[0].getSalary();
        double totalSalary = 0;



        for(int i = 0; i < count; i++) {


            Employee emp = employees[i];


            // Status Count

            if(emp.getStatus().equalsIgnoreCase("Active")) {

                active++;

            }
            else if(emp.getStatus().equalsIgnoreCase("Inactive")) {

                inactive++;

            }
            else if(emp.getStatus().equalsIgnoreCase("Suspended")) {

                suspended++;

            }



            // Department Count

            switch(emp.getDepartment()) {


                case "Software Development":
                    software++;
                    break;


                case "Quality Assurance":
                    qa++;
                    break;


                case "Human Resources":
                    hr++;
                    break;


                case "Project Management":
                    project++;
                    break;

            }



            // Employee Type Count

            switch(emp.getEmployeeType()) {


                case "Developer":
                    developer++;
                    break;


                case "Tester":
                    tester++;
                    break;


                case "HR Executive":
                    hrExecutive++;
                    break;


                case "Manager":
                    manager++;
                    break;

            }



            // Salary Calculation

            double salary = emp.getSalary();


            totalSalary += salary;



            if(salary > highestSalary) {

                highestSalary = salary;

            }


            if(salary < lowestSalary) {

                lowestSalary = salary;

            }

        }



        double averageSalary = totalSalary / count;



        // Display Report

        System.out.println("\n========== EMPLOYEE REPORT ==========");


        System.out.println("\nTotal Employees : " + count);


        System.out.println("\nEmployment Status");

        System.out.println("Active     : " + active);

        System.out.println("Inactive   : " + inactive);

        System.out.println("Suspended  : " + suspended);



        System.out.println("\nDepartment Wise Count");

        System.out.println("Software Development : " + software);

        System.out.println("Quality Assurance    : " + qa);

        System.out.println("Human Resources      : " + hr);

        System.out.println("Project Management   : " + project);



        System.out.println("\nEmployee Type Wise Count");

        System.out.println("Developer    : " + developer);

        System.out.println("Tester       : " + tester);

        System.out.println("HR Executive : " + hrExecutive);

        System.out.println("Manager      : " + manager);



        System.out.println("\nSalary Report");

        System.out.println("Highest Salary : " + highestSalary);

        System.out.println("Lowest Salary  : " + lowestSalary);

        System.out.println("Average Salary : " + averageSalary);


        System.out.println("\n====================================");

    }


}
