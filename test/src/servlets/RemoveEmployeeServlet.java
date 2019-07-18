package servlets;

import dao.impl.EmployeeDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/removeEmployee")
public class RemoveEmployeeServlet extends HttpServlet {
    private EmployeeDaoImpl employeeDao;

    @Override
    public void init() throws ServletException {
        employeeDao = new EmployeeDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameterEmployee = req.getParameter("idEmployee");
        String parameterDepartment = req.getParameter("idDepartment");
        int idEmployee = Integer.parseInt(parameterEmployee);
        int idDepartment = Integer.parseInt(parameterDepartment);
        remove(idEmployee);
        String url = "/employeeList?idDepartment=" + idDepartment;
        resp.sendRedirect(url);
    }

    private void remove(int idEmployee) {
        employeeDao.deleteById(idEmployee);
    }
}