package com.ems.service;



public class Employee {

    private String employeeId;
    private String name;
    private int age;
    private String gender;
    private String email;
    private String mobile;
    private String department;
    private String employeeType;
    private double salary;
    private String joiningDate;
    private String status;


    // Default Constructor
    public Employee() {

    }


    // Parameterized Constructor
    public Employee(String employeeId, String name, int age,
                    String gender, String email, String mobile,
                    String department, String employeeType,
                    double salary, String joiningDate, String status) {

        this.employeeId = employeeId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.department = department;
        this.employeeType = employeeType;
        this.salary = salary;
        this.joiningDate = joiningDate;
        this.status = status;

    }


    // Getters and Setters

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }


    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    // Display Employee Details

    public void displayEmployee() {

        System.out.println("----------------------------------");
        System.out.println("Employee ID     : " + employeeId);
        System.out.println("Name            : " + name);
        System.out.println("Age             : " + age);
        System.out.println("Gender          : " + gender);
        System.out.println("Email           : " + email);
        System.out.println("Mobile          : " + mobile);
        System.out.println("Department      : " + department);
        System.out.println("Employee Type   : " + employeeType);
        System.out.println("Salary          : " + salary);
        System.out.println("Joining Date    : " + joiningDate);
        System.out.println("Status          : " + status);
        System.out.println("----------------------------------");

    }

}

