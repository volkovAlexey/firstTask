package dao.mappers;

import dao.RowMapper;
import dao.exceptions.DBConnectionException;
import dao.models.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setIdEmployee(resultSet.getInt("id"));
        employee.setFirstName(resultSet.getString("firstName"));
        employee.setLastName(resultSet.getString("lastName"));
        employee.setBirthDate(resultSet.getDate("birthDate"));
        employee.setSalary(resultSet.getDouble("salary"));
        employee.setEmail(resultSet.getString("email"));
        employee.setIdDepartment(resultSet.getInt("departments_id"));
        return employee;
    }
}
