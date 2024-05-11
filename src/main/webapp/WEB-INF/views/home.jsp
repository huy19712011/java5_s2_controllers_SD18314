<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>

<html>
<head>
    <title>Spring Boot + JSP</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
    </tr>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.email}</td>
        </tr>
    </c:forEach>

</body>
</html>