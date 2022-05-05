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
<th>amount</th>

</tr>
</thead>
<tbody>
<form action ="/ReadAllProductSearch" method= "GET">
<tr>
<div class="form-floating">
<input type ="text" name = "name" placeholder ="name"   >
<input type ="submit" value = "search">

</div>
</tr>



<form action ="/readAllProduct" method= "GET">
<c:forEach var = "product" items = "${products}">

<tr>
<td>
${product.name}
</td>

<td>
${product.model}

</td>

<td>
${product.price}

</td>

<td>
${product.amount}

</td>

</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>