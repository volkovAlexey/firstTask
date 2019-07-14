package dao.models;

import com.sun.istack.internal.NotNull;
import com.sun.org.glassfish.gmbal.ParameterNames;

import java.util.Date;
import java.util.Objects;

public class Employee {
    private int idEmployee;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private double salary;
    private String email;
    private int idDepartment;


    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if(salary < 0){
            throw new IllegalArgumentException("Salary can't be negative!");
        }else {
            this.salary = salary;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getIdEmployee() == employee.getIdEmployee();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdEmployee());
    }

    @Override
    public String toString() {
        return firstName + lastName;
    }
}
