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

</tr>
</thead>
<tbody>
<form action ="/user/create" method= "GET">
<tr>

<div class="form-floating">
<input type ="text" name = "login" placeholder ="login">
<label for="floatingInput">Name</label>
</div>

<div class="form-floating">
<input type ="text" name = "password" placeholder ="password">
<label for="floatingInput">Model</label>
</div>

<td>
<input type ="submit" value = "create">
</td>

</tr>
</from>
</tbody>

</table>
</body>
</html>