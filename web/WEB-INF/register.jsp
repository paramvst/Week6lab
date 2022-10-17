<%-- 
    Document   : newjspregister.jsp
    Created on : 12-Oct-2022, 1:13:49 PM
    Author     : hsp28
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <form action="ShoppingList" method="post">
            Username: <input type="text" name="username" value="">
            <input type="hidden" name="action" value="register">
            <input type="submit" value="Register name"> 
        </form>
            <p style="color: red">${invalid}</p>
    </body>
</html>
