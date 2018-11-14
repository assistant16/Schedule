<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ page isELIgnored = 'false' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%--<style>--%>
<%--<%@include file='css/bootstrap.css' %>--%>
<%--</style>--%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Some Title</title>
</head>
<body>
<form action="/student" method="POST">
    <input type="text" name="firstName">
    <input type="text" name="secondName">
    <input type="submit" value="OK">
</form>

<table>
    <thead>
    <tr>
        <th>First name</th>
        <th>Second name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.firstName}</td>
            <td>${student.secondName}</td>
            <input type="button" value="delete">
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>