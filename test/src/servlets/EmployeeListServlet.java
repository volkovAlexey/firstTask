package servlets;

import dao.exceptions.DBConnectionException;
import dao.impl.EmployeeDaoImpl;
import dao.models.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/employeeList")
public class EmployeeListServlet extends HttpServlet {
    EmployeeDaoImpl employeeDao;

    @Override
    public void init() throws ServletException {
        employeeDao = new EmployeeDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("idDepartment");
        int idDepartment = Integer.parseInt(parameter);

        List<Employee> employees = findAllById(idDepartment);
        req.setAttribute("list", employees);
        String url = "/view/employeeListPage.jsp";
        req.getRequestDispatcher(url).forward(req, resp);
    }

    private List<Employee> findAllById(int idDepartment) {
        List<Employee> employeeList = null;
        try {
            employeeList = employeeDao.findAllByIdDepartment(idDepartment);
        } catch (SQLException | DBConnectionException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}
