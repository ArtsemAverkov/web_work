<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
<title>Create User</title>
</head>
<body class="text-center">>
<table>
<a href = "/pages/shop/userShop.jsp">userShop</a>||
<a href = "/pages/shop/adminShop.jsp">adminShop</a>
<thead>
<tr>

</tr>
</thead>
<tbody>
<form action ="/updateProduct" method= "GET">
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
<label for="floatingInput">Prise</label>
</div>
<div class="form-floating">
<input type ="text" name = "newName" placeholder ="newName">
<label for="floatingInput">NewName</label>
</div>
<div class="form-floating">
<input type ="text" name = "newModel" placeholder ="newModel">
<label for="floatingInput">NewModel</label>
</div>
<div class="form-floating">
<input type ="text" name = "newPrice" placeholder ="newPrice">
<label for="floatingInput">NewPrice</label>
</div>
<td>
<input type ="submit" value = "update"></td>
</tr>
</from>
</tbody>



</table>
</body>
</html>