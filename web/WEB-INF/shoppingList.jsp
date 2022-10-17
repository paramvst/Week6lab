<%-- 
    Document   : shoppingList
    Created on : 12-Oct-2022, 1:14:08 PM
    Author     : hsp28
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
    </head>
    <body>
        <h1>Shopping Page</h1>
        <p>Hello ${sessionUser}</p>
        <a href="ShoppingList?logout">Logout</a>
        
        <div>
            <h2>List</h2>
            <form action="ShoppingList" method="post">
                Add item: <input type="text" name="userItem" value="">
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Add">
            </form>
            <p style="color: red">${itemInvalid}</p>
            
            <form action="ShoppingList" method="post">
                <c:forEach items="${sessionItems}" var="item">
                    <p><input type="radio" name="${item}" value="${item}">${item}</p>
                </c:forEach>
                <input type="hidden" name="action" value="delete">
                <input type="submit" value="Delete">
            </form>
                <p style="color: red">${noDel}</p>
        </div>
        
    </body>
</html>
