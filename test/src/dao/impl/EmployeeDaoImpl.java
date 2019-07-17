package dao.impl;

import dao.EmployeeDao;
import dao.configs.Config;
import dao.exceptions.DBConnectionException;
import dao.mappers.EmployeeMapper;
import dao.models.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    private static final String FIND_EMPLOYEES = "SELECT * FROM employees WHERE departments_id = ?";
    private static final String INSERT_INTO_EMPLOYEES = "INSERT INTO employees VALUES (NULL, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM employees WHERE id = ?";
    private static final String UPDATE_EMPLOYEE = "UPDATE employees SET firstName = ?, lastName = ?, birthDate = ?, " +
            "salary = ?, email = ? WHERE id = ?";
    private static final String FIND_BY_ID = "SELECT * FROM employees WHERE id = ?";
    private static final String FIND_BY_ID_AND_EMAIL = "SELECT id,email FROM employees WHERE email = ?";

    @Override
    public List<Employee> findAllByIdDepartment(int idDepartment) throws SQLException, DBConnectionException {
        List<Employee> employees = new ArrayList<>();
        EmployeeMapper employeeMapper = new EmployeeMapper();
        ResultSet rs = null;
        try (Connection con = Config.getConnection();
             PreparedStatement prStatement = con.prepareStatement(FIND_EMPLOYEES)) {
            prStatement.setInt(1, idDepartment);
            rs = prStatement.executeQuery();
            while (rs.next()) {
                Employee employee = employeeMapper.mapRow(rs);
                employees.add(employee);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return employees;
    }

    @Override
    public void create(Employee employee) {
        try (Connection con = Config.getConnection();
             PreparedStatement prStatement = con.prepareStatement(INSERT_INTO_EMPLOYEES)) {
            prStatement.setString(1, employee.getFirstName());
            prStatement.setString(2, employee.getLastName());
            prStatement.setDate(3, (Date) employee.getBirthDate());
            prStatement.setDouble(4, employee.getSalary());
            prStatement.setString(5, employee.getEmail());
            prStatement.setInt(6, employee.getIdDepartment());
            prStatement.executeUpdate();
        } catch (SQLException | DBConnectionException e) {
            e.getStackTrace();
        }
    }

    @Override
    public Employee findById(int idEmployee) throws SQLException, DBConnectionException {
        Employee employee = null;
        EmployeeMapper employeeMapper = new EmployeeMapper();
        ResultSet rs = null;
        try (Connection con = Config.getConnection();
             PreparedStatement prStatement = con.prepareStatement(FIND_BY_ID)) {
            prStatement.setInt(1, idEmployee);
            rs = prStatement.executeQuery();
            while (rs.next()) {
                employee = employeeMapper.mapRow(rs);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return employee;
    }

    @Override
    public void deleteById(int idEmployee) {
        try (Connection con = Config.getConnection();
             PreparedStatement prStatement = con.prepareStatement(DELETE)) {
            prStatement.setInt(1, idEmployee);
            prStatement.executeUpdate();
        } catch (DBConnectionException | SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Employee employee) {
        try (Connection con = Config.getConnection();
             PreparedStatement prStatement = con.prepareStatement(UPDATE_EMPLOYEE)) {
            prStatement.setString(1, employee.getFirstName());
            prStatement.setString(2, employee.getLastName());
            prStatement.setDate(3, (Date) employee.getBirthDate());
            prStatement.setDouble(4, employee.getSalary());
            prStatement.setString(5, employee.getEmail());
            prStatement.setInt(6, employee.getIdEmployee());
            prStatement.executeUpdate();
        } catch (DBConnectionException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isDuplicateEmail(String idEmployee, String email) throws SQLException {
        ResultSet rs = null;
        try (Connection con = Config.getConnection();
             PreparedStatement prStatement = con.prepareStatement(FIND_BY_ID_AND_EMAIL)) {
            prStatement.setString(1, email);
            rs = prStatement.executeQuery();
            if (rs.next()) {
                return !rs.getString("id").equals(idEmployee);
            }
        } catch (DBConnectionException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}

