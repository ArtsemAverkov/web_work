<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
 <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
 <link href="signin.css" rel="stylesheet">
<title>Create User</title>
</head>
<body class="text-center">
<main class="form-signin">
<table>
<thead>
<tr>

</tr>
</thead>
<tbody>
<form action ="/user/come_in" method= "GET">

 <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
<tr>

<div class="form-floating">
<input type ="text" name = "login" placeholder ="login">
<label for="floatingInput">Login</label>
</div>

<div class="form-floating">
<input type ="text" name = "password" placeholder ="password">
<label for="floatingInput">Password</label>
</div>

<td><input type ="submit" value = "Sign in"></td>
</tr>
</from>
</tbody>

<a href = "/pages/user/Registracion.jsp">Registration</a>

</table>
</body>
</html>