package dao;

import dao.exceptions.DBConnectionException;
import dao.models.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {

    List<Employee> findAllByIdDepartment(int idDepartment)throws SQLException, DBConnectionException;

    Employee findById(int idEmployee) throws SQLException, DBConnectionException;

    void create(Employee employee) throws SQLException;

    void deleteById(int idEmployee) throws SQLException;

    void update(Employee employee) throws SQLException;

    boolean isDuplicateEmail(String idEmployee, String email) throws SQLException;
}