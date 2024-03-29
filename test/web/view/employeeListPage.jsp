<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
<style>
    table {
        width: 800px;
        margin: auto;
    }

    th {
        text-align: left;
    }

    td {
        text-align: left;
    }

    .button {
        border: 1px solid #ffb4be;
        background-color: #00ffff;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
        display: inline-block;
        padding: 1px 7px;
        text-decoration: none;
        color: black
    }

    .button:hover {
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.4);
        background: linear-gradient(to bottom, #b7fafa, #d8ffff);
        color: black;
    }

</style>
<br>
<a href="${pageContext.request.contextPath}/employeePage?idDepartment=${param.idDepartment}" class="button">Create
    Employee</a>
<table>
    <tr>
        <th>id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Birth Date</th>
        <th>Salary</th>
        <th>Email</th>
        <th>Update</th>
        <th>Remove</th>
    </tr>
    <c:forEach var="employee" items="${requestScope.list}">
        <tr>
            <td>${employee.idEmployee}</td>
            <td>${employee.firstName}</td>
            <td>${employee.lastName}</td>
            <td>${employee.birthDate}</td>
            <td>${employee.salary}</td>
            <td>${employee.email}</td>
            <td>
                <a href="${pageContext.request.contextPath}/employeeController?idEmployee=${employee.idEmployee}" class="button">Update</a>
            </td>
            <td>
                <br>
                <form action="${pageContext.request.contextPath}/removeEmployee?idEmployee=${employee.idEmployee}&idDepartment=${employee.idDepartment}"
                      method="post">
                    <input type="submit" class="button" value="Remove">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<p>
    <a href="${pageContext.request.contextPath}/departmentList" class="button">back</a>
</p>
</body>
</html>