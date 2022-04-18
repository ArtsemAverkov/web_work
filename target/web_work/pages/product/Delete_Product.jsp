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
<th>Mame</th>
<th>Model</th>
</tr>
</thead>
<tbody>
<form action ="/delete" method= "GET">
<tr>
<td><input type ="text" name = "name" placeholder ="name"></td>
<td><input type ="text" name = "model" placeholder ="model"></td>
<td><input type ="submit" value = "delete"></td>
</tr>
</from>
</tbody>

<a href = "/pages/user/eShop.jsp">Shop</a>

</table>
</body>
</html>