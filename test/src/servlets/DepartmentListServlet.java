package servlets;

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
import java.util.List;

@WebServlet(value = "/departmentList")
public class DepartmentListServlet extends HttpServlet {
    private DepartmentDaoImpl departmentDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list", findAll());

        String url = "view/departmentListPage.jsp";
        req.getRequestDispatcher(url).forward(req, resp);
    }

    private List<Department> findAll() {
        List<Department> departments = null;
        try {
            departments = departmentDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBConnectionException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public void init() throws ServletException {
        departmentDao = new DepartmentDaoImpl();
    }
}
