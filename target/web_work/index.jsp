<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
<title>Create User</title>
</head>
<body class="text-center">>
<table>
<thead>
<tr>
<th>login</th>
<th>password</th>
</tr>
</thead>
<tbody>
<form action ="/come_in" method= "GET">
<tr>
<td><input type ="text" name = "login" placeholder ="login"></td>
<td><input type ="text" name = "password" placeholder ="password"></td>
<td><input type ="submit" value = "come in"></td>
</tr>
</from>
</tbody>

<a href = "/pages/user/Registracion.jsp">Registration</a>

</table>
</body>
</html>