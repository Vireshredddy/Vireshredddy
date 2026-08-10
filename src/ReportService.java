import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class ReportService {
    private final EmployeeService employeeService;
    private final ExecutorService executorService;

    public ReportService(
            EmployeeService employeeService,
            ExecutorService executorService) {

        this.employeeService = employeeService;
        this.executorService = executorService;
    }

    public Future<List<Employee>>
    getAllEmployeesReport() {

        Callable<List<Employee>> task = () -> {

            System.out.println(
                    Thread.currentThread().getName()
                            + " -> Generating employee report"
            );

            Thread.sleep(500);

            return employeeService.getAllEmployees();
        };

        return executorService.submit(task);
    }
    public Future<Double>
    getTotalSalaryReport() {

        Callable<Double> task = () -> {

            System.out.println(
                    Thread.currentThread().getName()
                            + " -> Calculating total salary"
            );

            Thread.sleep(500);

            return employeeService
                    .getAllEmployees()
                    .stream()
                    .mapToDouble(Employee::getSalary)
                    .sum();
        };

        return executorService.submit(task);
    }

    public Future<Double>
    getAverageSalaryReport() {

        Callable<Double> task = () -> {

            System.out.println(
                    Thread.currentThread().getName()
                            + " -> Calculating average salary"
            );

            Thread.sleep(500);

            return employeeService
                    .getAllEmployees()
                    .stream()
                    .mapToDouble(Employee::getSalary)
                    .average()
                    .orElse(0.0);
        };

        return executorService.submit(task);
    }

    public Future<Employee>
    getHighestSalaryEmployee() {

        Callable<Employee> task = () -> {

            System.out.println(
                    Thread.currentThread().getName()
                            + " -> Finding highest salary"
            );

            Thread.sleep(500);

            return employeeService
                    .getAllEmployees()
                    .stream()
                    .max(
                            Comparator.comparingDouble(
                                    Employee::getSalary
                            )
                    )
                    .orElse(null);
        };

        return executorService.submit(task);
    }

    public Future<Employee>
    getLowestSalaryEmployee() {

        Callable<Employee> task = () -> {

            System.out.println(
                    Thread.currentThread().getName()
                            + " -> Finding lowest salary"
            );

            Thread.sleep(500);

            return employeeService
                    .getAllEmployees()
                    .stream()
                    .min(
                            Comparator.comparingDouble(
                                    Employee::getSalary
                            )
                    )
                    .orElse(null);
        };

        return executorService.submit(task);
    }

    public Future<List<Employee>>
    getDepartmentReport(String department) {

        Callable<List<Employee>> task = () -> {

            System.out.println(
                    Thread.currentThread().getName()
                            + " -> Generating "
                            + department
                            + " department report"
            );

            Thread.sleep(500);

            return employeeService
                    .getAllEmployees()
                    .stream()
                    .filter(employee ->
                            employee
                                    .getDepartment()
                                    .equalsIgnoreCase(
                                            department
                                    )
                    )
                    .collect(Collectors.toList());
        };

        return executorService.submit(task);
    }

    public Future<List<Employee>>
    sortBySalary() {

        Callable<List<Employee>> task = () -> {

            System.out.println(
                    Thread.currentThread().getName()
                            + " -> Sorting employees"
            );

            Thread.sleep(500);

            return employeeService
                    .getAllEmployees()
                    .stream()
                    .sorted(
                            Comparator
                                    .comparingDouble(
                                            Employee::getSalary
                                    )
                                    .reversed()
                    )
                    .collect(Collectors.toList());
        };

        return executorService.submit(task);
    }
    public Future<Integer>
    getEmployeeCountReport() {

        Callable<Integer> task = () -> {

            System.out.println(
                    Thread.currentThread().getName()
                            + " -> Counting employees"
            );

            return employeeService
                    .getEmployeeCount();
        };

        return executorService.submit(task);
    }
}

