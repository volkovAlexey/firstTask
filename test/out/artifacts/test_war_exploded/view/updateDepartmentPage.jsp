<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Department</title>
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
<form action="${pageContext.request.contextPath}/department?idDepartment=${requestScope.department.idDepartment}"
      method="post">
    <p><label>
        <input type="text" name="name" value="${requestScope.department.departmentName}" required>
    </label><span
            class="error"> ${requestScope.error.name}</span></p>
    <input type="submit" value="Update">
</form>
<p>
    <a href="${pageContext.request.contextPath}/departmentList">back</a>
</p>
</body>
</html>