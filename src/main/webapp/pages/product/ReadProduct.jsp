<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Create User</title>
</head>
<body class="text-center">
<table>
<thead>
<tr>

</tr>
</thead>
<tbody>
<form action ="/readProductAdd" method= "GET">
<tr>
<div class="form-floating">
<input type ="text" name = "name" placeholder ="name">
<label for="floatingInput">Name</label>
</div>

<div class="form-floating">
<input type ="text" name = "model" placeholder ="model">
<label for="floatingInput">Model</label>
</div>

<div class="form-floating">
<input type ="text" name = "price" placeholder ="price">
<label for="floatingInput">Price</label>
</div>

<div class="form-floating">
<input type ="text" name = "amount" placeholder ="amount">
<label for="floatingInput">Amount</label>
</td>

<td>
<input type ="submit" value = "Buy">
</td>

</tr>
</from>
</tbody>

</table>
</body>
</html>