package com.example.companyemployeeservlet.manager;


import com.example.companyemployeeservlet.db.DBConnectionProvider;
import com.example.companyemployeeservlet.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {

    private static Connection connection = DBConnectionProvider.getInstance().getConnection();

    private static CompanyManager companyManager = new CompanyManager();

    public void save(Employee employee) {
        try (Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO employee(name,surname, email, pic_name, company_id) VALUES('%s','%s','%s','%s', %d)";
            String sqlFormatted = String.format(sql, employee.getName(), employee.getSurname(), employee.getEmail(),
                    employee.getPicName(), employee.getCompany().getId());
            statement.executeUpdate(sqlFormatted, Statement.RETURN_GENERATED_KEYS);
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee WHERE id = " + id);
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee WHERE company_id = " + companyId);
            while (resultSet.next()) {
                employeeList.add(getEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public void removeById(int employeeId) {
        String sql = "DELETE FROM employee WHERE id = " + employeeId;
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
        employee.setPicName(resultSet.getString("pic_name"));
        int companyId = resultSet.getInt("company_id");
        employee.setCompany(companyManager.getById(companyId));
        return employee;
    }


    public List<Employee> search(String keyword) {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT * FROM company_employee.employee WHERE name LIKE  ? OR  surname LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            keyword = "%" + keyword + "%";
            preparedStatement.setString(1, keyword);
            preparedStatement.setString(2, keyword);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeList.add(getEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}
