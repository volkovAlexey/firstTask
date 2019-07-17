<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<html>
<head>
    <title>Department List</title>
</head>
<body>
<style>
    table {
        width: 500px;
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
<a href="${pageContext.request.contextPath}/departmentPage" class="button">Create Department</a>
<table>
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>List</th>
        <th>Update</th>
        <th>Remove</th>
    </tr>
    <c:forEach var="department" items="${requestScope.list}">
        <tr>
            <td>${department.idDepartment}</td>
            <td>${department.departmentName}</td>
            <td>
                <a href="${pageContext.request.contextPath}/employeeList?idDepartment=${department.idDepartment}" class="button">Show
                    List Employees</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/departmentController?idDepartment=${department.idDepartment}" class="button">Update</a>
            </td>
            <td>
                <br>
                <form action="${pageContext.request.contextPath}/removeDepartment?idDepartment=${department.idDepartment}"
                      method="post">
                    <input class="button" type="submit" value="Remove">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
