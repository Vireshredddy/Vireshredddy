import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EmployeeService {

    private final Map<Integer, Employee> employees =
            new ConcurrentHashMap<>();

    public void addEmployee(Employee employee) {

        employees.put(employee.getId(), employee);

        System.out.println(
                Thread.currentThread().getName()
                        + " -> Added Employee: "
                        + employee.getName()
        );
    }

    public Employee getEmployee(int id) {

        return employees.get(id);
    }

    public List<Employee> getAllEmployees() {

        return new ArrayList<>(employees.values());
    }
    public synchronized void updateSalary(
            int id,
            double newSalary) {

        Employee employee = employees.get(id);

        if (employee != null) {

            employee.setSalary(newSalary);

            System.out.println(
                    Thread.currentThread().getName()
                            + " -> Updated salary for "
                            + employee.getName()
                            + " = "
                            + newSalary
            );

        } else {

            System.out.println(
                    "Employee not found: " + id
            );
        }
    }

    public void deleteEmployee(int id) {

        Employee employee = employees.remove(id);

        if (employee != null) {

            System.out.println(
                    Thread.currentThread().getName()
                            + " -> Deleted Employee: "
                            + employee.getName()
            );

        } else {

            System.out.println(
                    "Employee not found: " + id
            );
        }
    }
    public int getEmployeeCount() {

        return employees.size();
    }

    public double getTotalSalary() {

        return employees.values()
                .stream()
                .mapToDouble(Employee::getSalary)
                .sum();
    }
}

