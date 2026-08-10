import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EMSApplication {
    public static void main(String[] args)
            throws Exception {
        EmployeeService employeeService =
                new EmployeeService();

        AuthenticationService authenticationService =
                new AuthenticationService();
        System.out.println(
                "===================================="
        );

        System.out.println(
                "     EMPLOYEE MANAGEMENT SYSTEM"
        );

        System.out.println(
                "===================================="
        );

        authenticationService.registerUser(
                "admin",
                "admin123"
        );

        authenticationService.registerUser(
                "manager",
                "manager123"
        );
        boolean login =
                authenticationService.login(
                        "admin",
                        "admin123"
                );

        if (!login) {

            System.out.println(
                    "Login failed. Application stopped."
            );

            return;
        }

        ExecutorService executorService =
                Executors.newFixedThreadPool(4);
        executorService.submit(() ->
                employeeService.addEmployee(
                        new Employee(
                                101,
                                "veer",
                                50000,
                                "IT"
                        )
                )
        );
        executorService.submit(() ->
                employeeService.addEmployee(
                        new Employee(
                                102,
                                "sumit",
                                60000,
                                "HR"
                        )
                )
        );
        executorService.submit(() ->
                employeeService.addEmployee(
                        new Employee(
                                103,
                                "Priya",
                                70000,
                                "Finance"
                        )
                )
        );
        executorService.submit(() ->
                employeeService.addEmployee(
                        new Employee(
                                104,
                                "niharika",
                                80000,
                                "IT"
                        )
                )
        );

        Thread.sleep(1000);
        System.out.println(
                "\n===== ALL EMPLOYEES ====="
        );

        employeeService
                .getAllEmployees()
                .forEach(System.out::println);
        executorService.submit(() ->
                employeeService.updateSalary(
                        101,
                        55000
                )
        );
        executorService.submit(() ->
                employeeService.deleteEmployee(102)
        );


        Thread.sleep(1000);
        ReportService reportService =
                new ReportService(
                        employeeService,
                        executorService
                );
        Future<Double> totalSalary =
                reportService.getTotalSalaryReport();

        Future<Double> averageSalary =
                reportService.getAverageSalaryReport();

        Future<Employee> highestSalary =
                reportService.getHighestSalaryEmployee();

        Future<Employee> lowestSalary =
                reportService.getLowestSalaryEmployee();

        Future<Integer> employeeCount =
                reportService.getEmployeeCountReport();

        System.out.println(
                "\n===== EMPLOYEE REPORT ====="
        );

        System.out.println(
                "Total Salary: "
                        + totalSalary.get()
        );

        System.out.println(
                "Average Salary: "
                        + averageSalary.get()
        );

        System.out.println(
                "Highest Salary Employee: "
                        + highestSalary.get()
        );

        System.out.println(
                "Lowest Salary Employee: "
                        + lowestSalary.get()
        );

        System.out.println(
                "Employee Count: "
                        + employeeCount.get()
        );
        Future<List<Employee>> itEmployees =
                reportService.getDepartmentReport(
                        "IT"
                );

        System.out.println(
                "\n===== IT DEPARTMENT ====="
        );

        itEmployees
                .get()
                .forEach(System.out::println);
        Future<List<Employee>> sortedEmployees =
                reportService.sortBySalary();

        System.out.println(
                "\n===== EMPLOYEES BY SALARY ====="
        );

        sortedEmployees
                .get()
                .forEach(System.out::println);
        System.out.println(
                "\nAdmin logged in: "
                        + authenticationService
                        .isLoggedIn("admin")
        );
        authenticationService.logout("admin");
        System.out.println(
                "Admin logged in after logout: "
                        + authenticationService
                        .isLoggedIn("admin")
        );
        executorService.shutdown();
        System.out.println(
                "\n===================================="
        );

        System.out.println(
                "     EMS APPLICATION COMPLETED"
        );

        System.out.println(
                "===================================="
        );
    }

}
