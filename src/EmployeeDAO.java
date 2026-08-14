import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // =====================================================
    // 1. ADD EMPLOYEE
    // =====================================================

    public void addEmployee(Employee employee) {

        String sql = """
                INSERT INTO employee
                (
                    first_name,
                    last_name,
                    email,
                    phone_number,
                    age,
                    identification_number,
                    experience_years,
                    performance_rating,
                    salary,
                    bonus,
                    gender,
                    active,
                    designation,
                    department_id,
                    employment_type,
                    date_of_birth,
                    joining_date,
                    address,
                    remarks,
                    manager_name
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            ps.setString(4, employee.getPhoneNumber());
            ps.setInt(5, employee.getAge());
            ps.setString(6, employee.getIdentificationNumber());
            ps.setInt(7, employee.getExperienceYears());
            ps.setDouble(8, employee.getPerformanceRating());
            ps.setDouble(9, employee.getSalary());
            ps.setDouble(10, employee.getBonus());
            ps.setString(11, employee.getGender());
            ps.setBoolean(12, employee.isActive());
            ps.setString(13, employee.getDesignation());
            ps.setInt(14, employee.getDepartmentId());
            ps.setString(15, employee.getEmploymentType());

            ps.setDate(
                    16,
                    java.sql.Date.valueOf(employee.getDateOfBirth())
            );

            ps.setDate(
                    17,
                    java.sql.Date.valueOf(employee.getJoiningDate())
            );

            ps.setString(18, employee.getAddress());
            ps.setString(19, employee.getRemarks());
            ps.setString(20, employee.getManagerName());

            ps.executeUpdate();

            System.out.println("Employee added successfully.");

        } catch (SQLException e) {
            System.out.println("Error while adding employee: "
                    + e.getMessage());
        }
    }




    public List<Employee> getAllEmployees() {

        List<Employee> employees = new ArrayList<>();

        String sql = "SELECT * FROM employee";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                employees.add(mapResultSetToEmployee(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching employees: "
                    + e.getMessage());
        }

        return employees;
    }




    public Employee getEmployeeById(int employeeId) {

        String sql = """
                SELECT *
                FROM employee
                WHERE employee_id = ?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapResultSetToEmployee(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while finding employee: "
                    + e.getMessage());
        }

        return null;
    }




    public void updateSalary(int employeeId, double salary) {

        String sql = """
                UPDATE employee
                SET salary = ?
                WHERE employee_id = ?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, salary);
            ps.setInt(2, employeeId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Salary updated successfully.");
            } else {
                System.out.println("Employee not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error while updating salary: "
                    + e.getMessage());
        }
    }




    public void deleteEmployee(int employeeId) {

        String sql = """
                DELETE FROM employee
                WHERE employee_id = ?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Employee not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error while deleting employee: "
                    + e.getMessage());
        }
    }




    public List<Employee> searchByName(String name) {

        List<Employee> employees = new ArrayList<>();

        String sql = """
                SELECT *
                FROM employee
                WHERE first_name LIKE ?
                   OR last_name LIKE ?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String searchName = "%" + name + "%";

            ps.setString(1, searchName);
            ps.setString(2, searchName);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    employees.add(mapResultSetToEmployee(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while searching name: "
                    + e.getMessage());
        }

        return employees;
    }




    public List<Employee> searchByDepartment(int departmentId) {

        List<Employee> employees = new ArrayList<>();

        String sql = """
                SELECT *
                FROM employee
                WHERE department_id = ?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, departmentId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    employees.add(mapResultSetToEmployee(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while searching department: "
                    + e.getMessage());
        }

        return employees;
    }




    public List<Employee> searchBySalaryRange(
            double minimumSalary,
            double maximumSalary) {

        List<Employee> employees = new ArrayList<>();

        String sql = """
                SELECT *
                FROM employee
                WHERE salary BETWEEN ? AND ?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, minimumSalary);
            ps.setDouble(2, maximumSalary);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    employees.add(mapResultSetToEmployee(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while searching salary: "
                    + e.getMessage());
        }

        return employees;
    }




    public List<Employee> getActiveEmployees() {

        List<Employee> employees = new ArrayList<>();

        String sql = """
                SELECT *
                FROM employee
                WHERE active = true
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                employees.add(mapResultSetToEmployee(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching active employees: "
                    + e.getMessage());
        }

        return employees;
    }




    public List<Employee> getHREmployees() {

        List<Employee> employees = new ArrayList<>();

        String sql = """
                SELECT *
                FROM employee
                WHERE designation = ?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "HR");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    employees.add(mapResultSetToEmployee(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching HR employees: "
                    + e.getMessage());
        }

        return employees;
    }




    private Employee mapResultSetToEmployee(ResultSet rs)
            throws SQLException {

        Employee employee = new Employee();

        employee.setEmployeeId(
                rs.getInt("employee_id")
        );

        employee.setFirstName(
                rs.getString("first_name")
        );

        employee.setLastName(
                rs.getString("last_name")
        );

        employee.setEmail(
                rs.getString("email")
        );

        employee.setPhoneNumber(
                rs.getString("phone_number")
        );

        employee.setAge(
                rs.getInt("age")
        );

        employee.setIdentificationNumber(
                rs.getString("identification_number")
        );

        employee.setExperienceYears(
                rs.getInt("experience_years")
        );

        employee.setPerformanceRating(
                rs.getDouble("performance_rating")
        );

        employee.setSalary(
                rs.getDouble("salary")
        );

        employee.setBonus(
                rs.getDouble("bonus")
        );

        employee.setGender(
                rs.getString("gender")
        );

        employee.setActive(
                rs.getBoolean("active")
        );

        employee.setDesignation(
                rs.getString("designation")
        );

        employee.setDepartmentId(
                rs.getInt("department_id")
        );

        employee.setEmploymentType(
                rs.getString("employment_type")
        );

        java.sql.Date dob =
                rs.getDate("date_of_birth");

        if (dob != null) {
            employee.setDateOfBirth(
                    dob.toLocalDate()
            );
        }

        java.sql.Date joiningDate =
                rs.getDate("joining_date");

        if (joiningDate != null) {
            employee.setJoiningDate(
                    joiningDate.toLocalDate()
            );
        }

        employee.setAddress(
                rs.getString("address")
        );

        employee.setRemarks(
                rs.getString("remarks")
        );

        employee.setManagerName(
                rs.getString("manager_name")
        );

        return employee;
    }
}