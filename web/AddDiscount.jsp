<%-- 
    Document   : AddDiscount.jsp
    Created on : Jun 19, 2021, 8:13:54 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Dropdown
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Another action</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Something else here</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#">Disabled</a>
                    </li>
                </ul>
                <c:if test="${not empty sessionScope.account}">
                    <div style="display: flex;align-items: center">
                        <h1 style="color: white">Hello, ${sessionScope.account.userName}</h1>
                        <a class="btn btn-success"  href="MainController?btnAction=Logout">logout</a>
                    </div>
                </c:if>
            </div>
        </nav>
        <h1 style="text-align: center">New Discount</h1>
        <div class="container">
            <div class="row">
                <div class="col-4">
                    <form method="Post" action="MainController">
                        code ID <input type="text" name="codeID" value="${requestScope.codeID}" /><br/>
                        <p style="color: red">${requestScope.error.codeIDEmpty}</p>
                        <p style="color: red">${requestScope.error.codeIDFormat}</p>
                        <p style="color: red">${requestScope.error.codeIDduplicate}</p>
                        code Value <input type="text" name="codeValue" value="${requestScope.codeValue}" /><br/>
                        <p style="color: red">${requestScope.error.codeValueEmpty}</p>
                        <p style="color: red">${requestScope.error.codeValueFormat}</p>
                        <input type="submit" value="createDiscount" name="btnAction" class="btn btn-danger"/>
                    </form>
                </div>
                <div class="col-8">
                    <table style="text-align: center">
                        <thead>
                            <tr>
                                <th>ID code</th>
                                <th>code value</th>
                                <th>create Date</th>
                                <th>user</th>
                                <th>Add discount user</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${requestScope.listCode}">
                                <tr>
                                    <td>${item.codeID}</td>
                                    <td>${item.codeValue}</td>
                                    <td>${item.createDate}</td>
                                    <td>
                                        <c:forEach var="i" items="${requestScope.listUser}">
                                           ${i.userName}<br/>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:forEach var="i" items="${requestScope.listUser}">
                                            <div style="display: flex">
                                                <a class="btn btn-danger" href="MainController?btnAction=AddDiscount&userID=${i.userID}&codeID=${item.codeID}">Add discount</a><br/>
                                            </div>
                                        </c:forEach>
                                    </td>
                                    <c:forEach var="i" items="${requestScope.listUser}">
                                        <c:if test="${i.userID eq requestScope.userID && item.codeID eq requestScope.codeIDSelect}">
                                            <td style="color: green">
                                                ${requestScope.mess}
                                            </td>
                                        </c:if>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <a class="btn btn-danger" href="MainController?btnAction=loadAdmin">back Admin</a>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
