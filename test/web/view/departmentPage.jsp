<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Department Page</title>
</head>
<body>
<style>
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

    .error {
        color: red;
    }
</style>
<form action="${pageContext.request.contextPath}/departmentController?idDepartment=${requestScope.department.idDepartment}"
      method="post">
    <p><input type="text" name="name" placeholder="Name" value="${requestScope.department.departmentName}" required>
        <span class="error"> ${requestScope.error.name}</span></p>
    <input class="button" type="submit" value="Submit">
</form>
<p>
    <a href="${pageContext.request.contextPath}/departmentList" class="button">back</a>
</p>
</body>
</html>