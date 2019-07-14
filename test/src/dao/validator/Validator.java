package dao.validator;

import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.impl.DepartmentDaoImpl;
import dao.impl.EmployeeDaoImpl;

import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String EMPTY_FIELD = "This field is required!";
    private static final String NEGATIVE_FIELD = "This field can't be negative!";
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String WRONG_EMAIL = "Wrong email!";
    private static final String NOT_CORRECTLY = "This field is not correctly";
    private static final String EMAIL_ALREADY_EXISTS = "This email already exists!";
    private static final String NAME_ALREADY_EXISTS = "This name already exists!";

    public void validateStringField(String fieldName, String value, Map<String, String> errorsMap) {
        if (isEmptyField(value)) {
            errorsMap.put(fieldName, EMPTY_FIELD);
            return;
        }
        if (!value.matches("[A-Z]([a-z]\\s*)*")) {
            errorsMap.put(fieldName, NOT_CORRECTLY);
        }
    }

    public void validateNumericField(String fieldName, String value, Map<String, String> errorMap) {
        if (isEmptyField(value)) {
            errorMap.put(fieldName, EMPTY_FIELD);
            return;
        }
        if (isNegative(value)) {
            errorMap.put(fieldName, NEGATIVE_FIELD);
        }
    }

    public void emailValidation(String fieldName, String id, String value, Map<String, String> errorMap){
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            errorMap.put(fieldName, WRONG_EMAIL);
            return;
        }
        if (isEmailDuplicate(id, value)) {
            errorMap.put(fieldName, EMAIL_ALREADY_EXISTS);
        }
    }

    public void nameValidation(String fieldName, String id, String value, Map<String, String> errorMap) {
        if (isNameDuplicate(id, value)) {
            errorMap.put(fieldName, NAME_ALREADY_EXISTS);
        }
    }

    private boolean isEmailDuplicate(String id, String email){
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        boolean isFind = false;
        try {
           isFind = employeeDao.findByIdAndEmail(id, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isFind;
    }

    private boolean isNameDuplicate(String id, String name) {
        DepartmentDao departmentDao = new DepartmentDaoImpl();
        return departmentDao.findByIdAndName(id, name);
    }

    private boolean isNegative(String num) {
        double value = Double.parseDouble(num);
        return value < 0;
    }

    private boolean isEmptyField(String text) {
        return text == null || text.trim().isEmpty();
    }

}