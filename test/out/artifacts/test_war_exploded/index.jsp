<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<style>

    a {
        border: 1px solid #333; /* Рамка */
        background-color: #00ffff;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.4);
        display: inline-block;
        padding: 5px 15px;
        text-decoration: none;
        color: black
    }

    a:hover {
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.3); /* Тень */
        background: linear-gradient(to bottom, #b7fafa, #d8ffff); /* Градиент */
        color: black;
    }
</style>
<a href="${pageContext.request.contextPath}/departmentList">Show List</a>
</body>
</html>
