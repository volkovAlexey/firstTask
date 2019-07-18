package servlets;

import dao.exceptions.DBConnectionException;
import dao.impl.EmployeeDaoImpl;
import dao.models.Employee;
import dao.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/employeeController")
public class EmployeeServlet extends HttpServlet {
    private EmployeeDaoImpl employeeDao;
    private Map<String, String> errorsMap;
    private Validator validator;
    private Employee employee;


    @Override
    public void init() throws ServletException {
        employeeDao = new EmployeeDaoImpl();
        errorsMap = new HashMap<>();
        validator = new Validator();
        employee = new Employee();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idEmployee = req.getParameter("idEmployee");
        int id = Integer.parseInt(idEmployee);
        employee = findEmployeeById(id);
        req.setAttribute("employee", employee);
        String url = "/view/employeePage.jsp?idDepartment=" + employee.getIdDepartment();
        req.getRequestDispatcher(url).forward(req, resp);
    }

    private Employee findEmployeeById(int id) {
        try {
            employee = employeeDao.findById(id);
        } catch (SQLException | DBConnectionException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errorsMap.clear();
        String idEmployee = req.getParameter("idEmployee");
        String idDepartment = req.getParameter("idDepartment");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String birthDate = req.getParameter("birthDate");
        String salary = req.getParameter("salary");
        String email = req.getParameter("email");

        validator.validateStringField("firstName", firstName, errorsMap);
        validator.validateStringField("lastName", lastName, errorsMap);
        validator.validateNumericField("salary", salary, errorsMap);
        validator.emailValidation("email", idEmployee, email, errorsMap);

        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setBirthDate(Date.valueOf(birthDate));
        employee.setSalary(Double.parseDouble(salary));
        employee.setEmail(email);
        employee.setIdDepartment(Integer.parseInt(idDepartment));
        if (!errorsMap.isEmpty()) {
            req.setAttribute("employee", employee);
            req.setAttribute("error", errorsMap);
            String url = "/view/employeePage.jsp";
            req.getRequestDispatcher(url).forward(req, resp);
            return;
        }
        if (!idEmployee.isEmpty()) {
            employee.setIdEmployee(Integer.parseInt(idEmployee));
            updateData(employee);
        } else {
            createEmployee(employee);
        }
        String url = "/employeeList?idDepartment=" + employee.getIdDepartment();
        resp.sendRedirect(url);
    }

    private void createEmployee(Employee employee) {
        employeeDao.create(employee);
    }

    private void updateData(Employee employee) {
        employeeDao.update(employee);
    }
}