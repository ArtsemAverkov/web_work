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
<th>name</th>
<th>model</th>
<th>price</th>

</tr>
</thead>
<tbody>
<form action ="/readAllProduct" method= "GET">
<c:forEach var = "product" items = "${products}">

<tr>
<td>${product.name} </td>
<td>${product.model} </td>
<td>${product.price} </td>
</tr>
</c:forEach>
</tbody>
<a href = "/index.jsp">back</a>
</table>
</body>
</html>