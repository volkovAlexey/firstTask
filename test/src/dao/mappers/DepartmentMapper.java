package dao.mappers;

import dao.RowMapper;
import dao.models.Department;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper implements RowMapper<Department> {
    public Department mapRow(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setIdDepartment(rs.getInt("id"));
        department.setDepartmentName(rs.getString("name"));
        return department;
    }
}
