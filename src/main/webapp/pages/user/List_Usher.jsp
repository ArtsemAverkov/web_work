<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Product</title>
</head>

<body>
<table>
<thead>
<tr>
<th>login</th>
<th>password</th>

</tr>
</thead>
<tbody>
<form action ="/user/readAllUser" method= "GET">
<c:forEach var = "user" items = "${users}">
<tr>
<td>${user.login}</td>

<td>${user.password}</td>


</tr>
</c:forEach>
</tbody>

</table>
</body>
</html>