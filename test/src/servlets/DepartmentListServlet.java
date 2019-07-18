package servlets;

import dao.DepartmentDao;
import dao.exceptions.DBConnectionException;
import dao.impl.DepartmentDaoImpl;
import dao.models.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/departmentList")
public class DepartmentListServlet extends HttpServlet {
    private DepartmentDao departmentDao;
    private List<Department> departments;

    @Override
    public void init() throws ServletException {
        departmentDao = new DepartmentDaoImpl();
        departments = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        departments = findAllDepartment();
        req.setAttribute("list", departments);

        String url = "view/departmentListPage.jsp";
        req.getRequestDispatcher(url).forward(req, resp);
    }

    private List<Department> findAllDepartment() {
        try {
            departments = departmentDao.findAll();
        } catch (SQLException | DBConnectionException e) {
            e.printStackTrace();
        }
        return departments;
    }
}