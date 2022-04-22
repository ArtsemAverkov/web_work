<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<form action ="/readAllProduct" method= "GET">
<head>
<title>Product</title>
</head>

<body>
<table>
<a href = "/pages/shop/userShop.jsp">userShop</a>||
<a href = "/pages/shop/adminShop.jsp">adminShop</a>||
<a href = "/index.jsp">exit</a>
<thead>
<tr>


</tr>
</thead>
<tbody>

</tbody>
<div class="form-floating">
<a href = "/readAllProduct">Product</a>
</div>
<div class="form-floating">
<a href = "/pages/product/Create_Product.jsp">Create Product</a>
</div>
<div class="form-floating">
<a href = "/pages/product/Delete_Product.jsp">Delete Product</a>
</div>
<div class="form-floating">
<a href = "/pages/product/Update_Product.jsp">Update_Product</a>
</div>
</div>
<div class="form-floating">
<a href = "/pages/user/Delete_User.jsp">Delete_User</a>
</div>
</div>
<div class="form-floating">
<a href = "/user/readAllUser">List_Usher</a>
</div>
</div>
<div class="form-floating">
<a href = "/pages/user/Update_User.jsp">Update_User</a>
</div>





</table>
</body>
</html>