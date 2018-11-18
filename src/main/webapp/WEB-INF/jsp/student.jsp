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
    <%--<link rel="stylesheet" href="css/bootstrap.min.css" />--%>
    <%--<link rel="stylesheet" href="style.css">--%>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Some Title</title>
</head>
<body>
<form name="StudentForm" action="/student" method="post">
    <div class="form-group">
        <input type="text" name="firstName">
        <input type="text" name="lastName">
        <input type="submit" value="OK">
    </div>
</form>

<table>
    <thead>
    <tr>
        <th>First name </th>
        <th>Last name </th>

    </tr>
    </thead>
    <tbody id="tbody"></tbody>
</table>

<script type="text/javascript">

    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/student?json', false);
    xhr.send();

    if (xhr.status != 200) {
            alert( xhr.status + ': ' + xhr.statusText );
        } else {
            var data = JSON.parse( xhr.responseText);
        }

        var body, tab, tr, td, tn, row, col;

        body = document.getElementsByTagName('tbody')[0];

        for (row = 0; row < data.length; row++){

            tr = document.createElement('tr');
            var ob = data[row];
            td = document.createElement('td');
            tn = document.createTextNode(ob.firstName);
            td.appendChild(tn);
            tr.appendChild(td);

            td = document.createElement('td');
            tn = document.createTextNode(ob.lastName);
            td.appendChild(tn);
            tr.appendChild(td);
            body.appendChild(tr);
    }

</script>
</body>
<%--<table>--%>
    <%--<thead>--%>
    <%--<tr>--%>
        <%--<th>First name</th>--%>
        <%--<th>Second name</th>--%>
    <%--</tr>--%>
    <%--</thead>--%>
    <%--<tbody>--%>
    <%--<c:forEach items="${students}" var="student">--%>
        <%--<tr>--%>
            <%--<td>${student.firstName}</td>--%>
            <%--<td>${student.lastName}</td>--%>
        <%--</tr>--%>
    <%--</c:forEach>--%>
    <%--</tbody>--%>
<%--</table>--%>
</html>