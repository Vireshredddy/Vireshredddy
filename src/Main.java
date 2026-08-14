import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void main(String[] args) {

        while (true) {

            printMenu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewAllEmployees();
                    break;

                case 2:
                    findEmployeeById();
                    break;

                case 3:
                    addEmployee();
                    break;

                case 4:
                    updateSalary();
                    break;

                case 5:
                    deleteEmployee();
                    break;

                case 6:
                    searchByName();
                    break;

                case 7:
                    searchByDepartment();
                    break;

                case 8:
                    searchBySalaryRange();
                    break;

                case 9:
                    viewActiveEmployees();
                    break;

                case 10:
                    viewHREmployees();
                    break;

                case 11:
                    System.out.println("Application closed.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }




    private static void printMenu() {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("       EMPLOYEE MANAGEMENT SYSTEM");
        System.out.println("==============================================");
        System.out.println("1.  View All Employees");
        System.out.println("2.  Find Employee By ID");
        System.out.println("3.  Add Employee");
        System.out.println("4.  Update Employee Salary");
        System.out.println("5.  Delete Employee");
        System.out.println("6.  Search Employee By Name");
        System.out.println("7.  Search Employee By Department");
        System.out.println("8.  Search Employee By Salary Range");
        System.out.println("9.  View Active Employees");
        System.out.println("10. View HR Employees");
        System.out.println("11. Exit");
        System.out.println("==============================================");
    }




    private static void viewAllEmployees() {

        List<Employee> employees =
                employeeDAO.getAllEmployees();

        printEmployees(employees);
    }




    private static void findEmployeeById() {

        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Employee employee =
                employeeDAO.getEmployeeById(id);

        if (employee == null) {
            System.out.println("Employee not found.");
        } else {
            System.out.println();
            System.out.println(employee);
        }
    }




    private static void addEmployee() {

        Employee employee = new Employee();

        System.out.println();
        System.out.println("========== ADD EMPLOYEE ==========");

        System.out.print("First Name: ");
        employee.setFirstName(scanner.nextLine());

        System.out.print("Last Name: ");
        employee.setLastName(scanner.nextLine());

        System.out.print("Email: ");
        employee.setEmail(scanner.nextLine());

        System.out.print("Phone Number: ");
        employee.setPhoneNumber(scanner.nextLine());

        System.out.print("Age: ");
        employee.setAge(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Identification Number: ");
        employee.setIdentificationNumber(scanner.nextLine());

        System.out.print("Experience Years: ");
        employee.setExperienceYears(scanner.nextInt());

        System.out.print("Performance Rating: ");
        employee.setPerformanceRating(scanner.nextDouble());

        System.out.print("Salary: ");
        employee.setSalary(scanner.nextDouble());

        System.out.print("Bonus: ");
        employee.setBonus(scanner.nextDouble());

        scanner.nextLine();

        System.out.print("Gender: ");
        employee.setGender(scanner.nextLine());

        System.out.print("Active (true/false): ");
        employee.setActive(scanner.nextBoolean());
        scanner.nextLine();

        System.out.print("Designation: ");
        employee.setDesignation(scanner.nextLine());

        System.out.print("Department ID: ");
        employee.setDepartmentId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Employment Type: ");
        employee.setEmploymentType(scanner.nextLine());

        System.out.print("Date of Birth (yyyy-mm-dd): ");
        employee.setDateOfBirth(
                LocalDate.parse(scanner.nextLine())
        );

        System.out.print("Joining Date (yyyy-mm-dd): ");
        employee.setJoiningDate(
                LocalDate.parse(scanner.nextLine())
        );

        System.out.print("Address: ");
        employee.setAddress(scanner.nextLine());

        System.out.print("Remarks: ");
        employee.setRemarks(scanner.nextLine());

        System.out.print("Manager Name: ");
        employee.setManagerName(scanner.nextLine());

        employeeDAO.addEmployee(employee);
    }

    private static void updateSalary() {

        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter new salary: ");
        double salary = scanner.nextDouble();

        scanner.nextLine();

        employeeDAO.updateSalary(id, salary);
    }



    private static void deleteEmployee() {

        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();

        scanner.nextLine();

        employeeDAO.deleteEmployee(id);
    }



    private static void searchByName() {

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        List<Employee> employees =
                employeeDAO.searchByName(name);

        printEmployees(employees);
    }



    private static void searchByDepartment() {

        System.out.print("Enter department ID: ");
        int departmentId = scanner.nextInt();

        scanner.nextLine();

        List<Employee> employees =
                employeeDAO.searchByDepartment(departmentId);

        printEmployees(employees);
    }




    private static void searchBySalaryRange() {

        System.out.print("Enter minimum salary: ");
        double minSalary = scanner.nextDouble();

        System.out.print("Enter maximum salary: ");
        double maxSalary = scanner.nextDouble();

        scanner.nextLine();

        List<Employee> employees =
                employeeDAO.searchBySalaryRange(
                        minSalary,
                        maxSalary
                );

        printEmployees(employees);
    }




    private static void viewActiveEmployees() {

        List<Employee> employees =
                employeeDAO.getActiveEmployees();

        printEmployees(employees);
    }




    private static void viewHREmployees() {

        List<Employee> employees =
                employeeDAO.getHREmployees();

        printEmployees(employees);
    }




    private static void printEmployees(
            List<Employee> employees) {

        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println();
        System.out.println("========== EMPLOYEES ==========");

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        System.out.println("===============================");
    }
}