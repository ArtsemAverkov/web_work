<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
<title>Create User</title>
</head>
<body class="text-center">>
<table>
<a href = "/pages/shop/userShop.jsp">userShop</a> ||
<a href = "/pages/shop/adminShop.jsp">adminShop</a>
<thead>
<tr>

</tr>
</thead>
<tbody>
<form action ="/user/delete" method= "GET">
<tr>
<div class="form-floating">
<input type ="text" name = "login" placeholder ="login">
<label for="floatingInput">Login</label>
</td>

<div class="form-floating">
<input type ="text" name = "password" placeholder ="password">
<label for="floatingInput">Password</label>
</td>

<td>
<input type ="submit" value = "delete">
</td>

</tr>
</from>
</tbody>



</table>
</body>
</html>