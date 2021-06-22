<%-- 
    Document   : Login
    Created on : Jun 12, 2021, 5:23:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <h1 style="text-align: center">Login Page</h1>
       <p style="color: red; text-align: center">${requestScope.messError}</p>
       <p style="color: red; text-align: center">${requestScope.errorLogin}</p>
        <div style="display: flex; justify-content: space-around">
            <form method="Post" action="MainController">
                <label>UserID</label><input type="text" name="userID" value="${requestScope.userID}"/><br/>
                <p style="color: red" >${requestScope.error.emptyUserID}</p>
                <label>Password<label> <input type="password" name="password" value="${requestScope.password}"/><br/>
                <p style="color: red" >${requestScope.error.emptyPassword}</p>
                <input type="submit" value="Login" name="btnAction" class="btn btn-success my-2 my-sm-0"/>
            </form>
        </div>
    </body>
</html>
