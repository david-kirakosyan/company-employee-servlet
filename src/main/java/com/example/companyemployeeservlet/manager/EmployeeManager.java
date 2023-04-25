package com.example.companyemployeeservlet.manager;


import com.example.companyemployeeservlet.db.DBConnectionProvider;
import com.example.companyemployeeservlet.model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {

    private static Connection connection = DBConnectionProvider.getInstance().getConnection();

    private static CompanyManager companyManager = new CompanyManager();

    public void save(Employee employee) {
        try (Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO company_employee.employee(name,surname, email,company_id) VALUES('%s','%s','%s', %d)";
            statement.executeUpdate(String.format(sql, employee.getName(), employee.getSurname(), employee.getEmail(), employee.getCompany().getId()), Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                employee.setId(generatedKeys.getInt(1));
            }
            System.out.println("Employee inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getAll() {
        List<Employee> employeeList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM company_employee.employee");
            while (resultSet.next()) {
                employeeList.add(getEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public Employee getById(int id) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM company_employee.employee WHERE id = " + id);
            if (resultSet.next()) {
                return getEmployeeFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Employee employee) {
        String sql = "UPDATE company_employee.employee SET name = '%s', surname = '%s', email = '%s', company_id = %d WHERE id = %d";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(String.format(sql, employee.getName(), employee.getSurname(),
                    employee.getEmail(), employee.getCompany().getId(), employee.getId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Employee> getAllByCompanyId(int companyId) {
        List<Employee> employeeList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM company_employee.employee WHERE company_id = " + companyId);
            while (resultSet.next()) {
                employeeList.add(getEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public void removeById(int employeeId) {
        String sql = "DELETE FROM company_employee.employee WHERE id = " + employeeId;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Employee getEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setName(resultSet.getString("name"));
        employee.setSurname(resultSet.getString("surname"));
        employee.setEmail(resultSet.getString("email"));
        int companyId = resultSet.getInt("company_id");
        employee.setCompany(companyManager.getById(companyId));
        return employee;
    }


}
