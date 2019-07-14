<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Employee</title>
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
<form action="${pageContext.request.contextPath}/employeeController?idDepartment=${param.idDepartment}" method="post">
    <p><input type="text" name="firstName" placeholder="First Name" value="${requestScope.employee.firstName}"
              required><span class="error"> ${requestScope.error.firstName}</span></p>
    <p><input type="text" name="lastName" placeholder="Last Name" value="${requestScope.employee.lastName}"
              required><span
            class="error"> ${requestScope.error.lastName}</span></p>
    <p><input type="date" name="birthDate" placeholder="Birth Date" value="${requestScope.employee.birthDate}"
              required><span
            class="error"> ${requestScope.error.birthDate}</span></p>
    <p><input type="number" name="salary" placeholder="Salary" value="${requestScope.employee.salary}" required min="0"><span
            class="error"> ${requestScope.error.salary}</span></p>
    <p><input type="email" name="email" placeholder="Email" value="${requestScope.employee.email}" required><span
            class="error"> ${requestScope.error.email}</span></p>
    <input type="submit" value="OK">
</form>
<p>
    <a href="${pageContext.request.contextPath}/employeeList?idDepartment=${param.idDepartment}">back</a>
</p>
</body>
</html>