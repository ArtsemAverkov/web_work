<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Product</title>
</head>

<body>
<table>
<li><a href = "/pages/product/SearchProduct.jsp">SearchProduct</a> ||
<a href = "/pages/product/ReadProduct.jsp">Buy_A_Product</a> ||
<li><a href = "/ReadAllProductASPName">Sort_by_name(a-z)</a> ||
<a href = "/ReadAllProductDESCName">Sort_by_name(z-a)</a> ||
<a href = "/ReadAllProductASPPrice">Sort_by_price(0-9)</a> ||
<a href = "/ReadAllProductDESPPrice">Sort_by_price(9-0)</a> ||
<thead>
<tr>
<form action ="/ReadProductFromBefore" method= "GET">
<td>
<input type ="text" name = "from" placeholder ="from">
<input type ="text" name = "before" placeholder ="before">
<input type ="submit" value = "ok">
</td>

<tr>
<th>name</th>
<th>model</th>
<th>price</th>
<th>amount</th>
</tr>
</thead>
<tbody>




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