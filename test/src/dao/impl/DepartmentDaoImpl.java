package dao.impl;

import dao.configs.Config;
import dao.DepartmentDao;
import dao.exceptions.DBConnectionException;
import dao.mappers.DepartmentMapper;
import dao.models.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {
    private static final String FIND_DEPARTMENTS = "SELECT * FROM departments";
    private static final String INSERT_INTO_DEPARTMENTS = "INSERT INTO departments (name) VALUES (?)";
    private static final String DELETE = "DELETE FROM departments WHERE departments.id = ?";
    private static final String UPDATE_DEPARTMENT = "UPDATE departments SET name = ? WHERE id = ?";
    private static final String FIND_DEPARTMENT = "SELECT * FROM departments WHERE id = ?";
    private static final String FIND_BY_NAME = "SELECT * FROM departments WHERE name = ?";
//    private static final String FIND_BY_NAME = "SELECT name FROM departments WHERE name = ?";

    @Override
    public List<Department> findAll() throws SQLException, DBConnectionException {
        List<Department> departments = new ArrayList<>();
        DepartmentMapper departmentMapper = new DepartmentMapper();
        ResultSet rs = null;
        try (Connection con = Config.getConnection();
             Statement statement = con.createStatement()) {
            rs = statement.executeQuery(FIND_DEPARTMENTS);
            while (rs.next()) {
                Department department = departmentMapper.mapRow(rs);
                departments.add(department);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return departments;
    }

    @Override
    public Department findById(int id) {
        Department department = new Department();
        DepartmentMapper departmentMapper = new DepartmentMapper();
        ResultSet rs = null;
        try (Connection con = Config.getConnection();
             PreparedStatement prStatement = con.prepareStatement(FIND_DEPARTMENT)) {
            prStatement.setInt(1, id);
            rs = prStatement.executeQuery();
            while (rs.next()) {
                department = departmentMapper.mapRow(rs);
            }
        } catch (SQLException | DBConnectionException e) {
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
        return department;
    }

    @Override
    public boolean findByIdAndName(String id, String name) {
        ResultSet rs = null;
        try (Connection con = Config.getConnection();
             PreparedStatement prStatement = con.prepareStatement(FIND_BY_NAME)) {
            prStatement.setString(1, name);
            rs = prStatement.executeQuery();
            if (rs.next()) {
                return !rs.getString("id").equals(id);
            }
        } catch (SQLException | DBConnectionException e) {
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

    @Override
    public void create(Department department) {
        try (Connection con = Config.getConnection();
             PreparedStatement prStatement = con.prepareStatement(INSERT_INTO_DEPARTMENTS)) {
            prStatement.setString(1, department.getDepartmentName());
            prStatement.executeUpdate();
        } catch (DBConnectionException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection con = Config.getConnection();
             PreparedStatement prStatement = con.prepareStatement(DELETE)) {
            prStatement.setInt(1, id);
            prStatement.executeUpdate();
        } catch (DBConnectionException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Department department) throws SQLException {
        try (Connection con = Config.getConnection();
             PreparedStatement prStatement = con.prepareStatement(UPDATE_DEPARTMENT)) {
            prStatement.setString(1, department.getDepartmentName());
            prStatement.setInt(2, department.getIdDepartment());
            prStatement.executeUpdate();
        } catch (DBConnectionException e) {
            e.printStackTrace();
        }
    }
}
