<%--
  Created by IntelliJ IDEA.
  User: Noirix
  Date: 09.02.2023
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
${passengerFullName}

<div>
    <h1>System Passengers</h1>
</div>
<div>
    <table>
        <tr>
            <td>Pass Id</td>
            <td>Full Name</td>
            <td>Personal id</td>
            <td>Created</td>
            <td>Changed</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        <c:forEach var="passenger" items="${passengers}">
            <tr>
                <td>${passenger.idPass}</td>
                <td>${passenger.fullName}</td>
                <td>${passenger.personalId}</td>
                <td>${passenger.created}</td>
                <td>${passenger.changed}</td>
                <td><button>Edit</button></td>
                <td><button>Delete</button></td>
            </tr>
        </c:forEach>
    </table>
</div>


</body>
</html>
