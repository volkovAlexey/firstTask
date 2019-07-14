package servlets;

import dao.DepartmentDao;
import dao.impl.DepartmentDaoImpl;
import dao.models.Department;
import dao.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(value = "/departmentController")
public class DepartmentServlet extends HttpServlet {
    private DepartmentDao departmentDao;
    private Validator validator;
    private Map<String, String> errorMap;
    private Department department;

    @Override
    public void init() throws ServletException {
        departmentDao = new DepartmentDaoImpl();
        department = new Department();
        validator = new Validator();
        errorMap = new HashMap<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameterId = req.getParameter("idDepartment");
        int id = Integer.parseInt(parameterId);
        department = findDepartmentById(id);
        req.setAttribute("department", department);
        String url = "/view/updateDepartmentPage.jsp";
        req.getRequestDispatcher(url).forward(req, resp);
    }

    private Department findDepartmentById(int id) {
        return departmentDao.findById(id);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameterId = req.getParameter("idDepartment");
        String parameterName = req.getParameter("name");
        errorMap.clear();
        validator.validateStringField("name", parameterName, errorMap);
        validator.nameValidation("name", parameterId, parameterName, errorMap);
        department.setDepartmentName(parameterName);
        if (!errorMap.isEmpty()) {
            req.setAttribute("error", errorMap);
            req.setAttribute("department", department);
            if (parameterId == null) {
                String urlCreate = "/view/createDepartmentPage.jsp";
                req.getRequestDispatcher(urlCreate).forward(req, resp);
                return;
            } else {
                String urlUpdate = "/view/updateDepartmentPage.jsp?idDepartment=" + parameterId +
                        "&name=" + parameterName;
                req.getRequestDispatcher(urlUpdate).forward(req, resp);
                return;
            }
        }
        if (parameterId == null) {
            create(department);
        } else {
            department.setIdDepartment(Integer.parseInt(parameterId));
            updateData(department);
        }
        String url = "/departmentList";
        resp.sendRedirect(url);
    }

    private void create(Department department) {
        try {
            departmentDao.create(department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateData(Department department) {
        try {
            departmentDao.update(department);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
