<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Employee Page</title>
</head>
<body>
<style>
    a {
        border: 1px solid #ffb4be;
        background-color: #00ffff;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
        display: inline-block;
        padding: 3px 7px;
        text-decoration: none;
        color: black
    }

    a:hover {
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.4);
        background: linear-gradient(to bottom, #b7fafa, #d8ffff);
        color: black;
    }

    .error {
        color: red;
    }
</style>
<form action=${pageContext.request.contextPath}/employeeController?idEmployee=${requestScope.employee.idEmployee}&idDepartment=${param.idDepartment}
      method="post">
    <p><label>
        <input type="text" name="firstName" placeholder="First Name" value="${requestScope.employee.firstName}" required>
    </label><span
            class="error"> ${requestScope.error.firstName}</span></p>
    <p><label>
        <input type="text" name="lastName" placeholder="Last Name" value="${requestScope.employee.lastName}" required>
    </label><span
            class="error"> ${requestScope.error.lastName}</span></p>
    <p><label>
        <input type="date" name="birthDate" placeholder="Birth Date" value="${requestScope.employee.birthDate}" required>
    </label><span
            class="error"> ${requestScope.error.birthDate}</span></p>
    <p><label>
        <input type="number" name="salary" placeholder="Salary" value="${requestScope.employee.salary}" required min="0">
    </label><span
            class="error"> ${requestScope.error.salary}</span></p>
    <p><label>
        <input type="email" name="email" placeholder="Email" value="${requestScope.employee.email}" required>
    </label><span
            class="error"> ${requestScope.error.email}</span></p>
    <input type="submit" value="Submit">
</form>
<p>
    <a href="<c:url value="/employeeList?idDepartment=${param.idDepartment}"/>">back</a>
</p>
</body>
</html>