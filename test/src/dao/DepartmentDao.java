package dao;

import dao.exceptions.DBConnectionException;
import dao.models.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDao {
    List<Department> findAll() throws SQLException, DBConnectionException;

    void create(Department department) throws SQLException;

    void deleteById(int id) throws SQLException;

    void update(Department department) throws SQLException;

    Department findById(int id);

    boolean isDuplicateName(String id, String name);
}