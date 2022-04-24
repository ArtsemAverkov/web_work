<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Product</title>
</head>

<body>
<table>
<a href = "/pages/shop/userShop.jsp">userShop</a>||
<a href = "/pages/shop/adminShop.jsp">adminShop</a>||
<thead>
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
<a href = "pages/product/ReadProduct.jsp">Buy_A_Product</a>
</table>
</body>
</html>