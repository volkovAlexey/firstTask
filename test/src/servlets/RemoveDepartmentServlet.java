package servlets;

import dao.impl.DepartmentDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/removeDepartment")
public class RemoveDepartmentServlet extends HttpServlet {
    private DepartmentDaoImpl departmentDao;

    @Override
    public void init() throws ServletException {
        departmentDao = new DepartmentDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("idDepartment");
        int idDepartment = Integer.parseInt(parameter);
        removeById(idDepartment);
        String url = "/departmentList";
        resp.sendRedirect(url);
    }

    private void removeById(int idDepartment) {
        departmentDao.deleteById(idDepartment);
    }
}
