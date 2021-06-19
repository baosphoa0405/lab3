<%-- 
    Document   : Admin
    Created on : Jun 12, 2021, 5:21:02 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>ADMIN Page</title>
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
        <h1  style="text-align: center; color: green">${requestScope.messSuccess}</h1>
        <h1 style="text-align: center"> Management Book</h1>
        <h1 style="text-align: center">${requestScope.addSuccess}</h1>
        <a class="btn btn-danger" href="MainController?btnAction=Insert">Add new Book</a>
        <a class="btn btn-danger" href="MainController?btnAction=ViewDiscount">View Discount</a>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <table  style="margin-inline: auto; text-align: center; border: none">
                        <thead>
                            <tr>
                                <th>BookID</th>
                                <th>Title</th>
                                <th>image</th>
                                <th>description</th>
                                <th>Author</th>
                                <th>CategoryName</th>
                                <th>Status</th>
                                <th>Quantity</th>
                                <th>Date</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${requestScope.listBook}">
                                <tr>
                                    <td>${item.bookID}</td>
                                    <td>${item.title}</td>
                                    <td>${item.image}</td>
                                    <td>${item.description}</td>
                                    <td>${item.author}</td>
                                    <td>${item.status.statusName}</td>
                                    <td>${item.category.categoryName}</td>
                                    <td>${item.quantity}</td>
                                    <td>${item.date}</td>
                                    <c:if test="${item.status.statusName ne 'InActive'}">
                                        <td>
                                            <form method="POST" action="MainController">
                                                <input type="hidden" name="bookID" value="${item.bookID}" />
                                                <input   id="${item.bookID} Delete" style="display: none" type="submit" value="Delete"  name="btnAction" />
                                            </form>
                                            <button class="btn btn-success" onclick="ConfirmDelete('${item.bookID} Delete')">Delete</button>
                                        </td>
                                    </c:if>
                                    <c:if test="${item.status.statusName eq 'InActive'}">
                                        <td>
                                        </td>
                                    </c:if>  
                                    <td> 
                                        <form method="POST" action="MainController">
                                            <input type="hidden" name="bookID" value="${item.bookID}" />
                                            <input style="display: none" id="${item.bookID} Update" type="submit" value="Update"  name="btnAction" />
                                        </form>
                                        <button class="btn btn-success" onclick="ConfirmUpdate('${item.bookID} Update')">Update</button>
                                    </td>
                                    <c:if test="${not empty requestScope.mess && requestScope.bookID eq item.bookID}">
                                        <td style="color: green">${requestScope.mess}</td>
                                    </c:if>
                                </tr>    
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
        <script>
            function ConfirmDelete(bookID) {
                confirmModal = confirm("Do you want to  " + bookID);
                if (confirmModal) {
                    document.getElementById(bookID).click();
                }
            }
            function ConfirmUpdate(bookID) {
                confirmModal = confirm("Do you want to " + bookID);
                if (confirmModal) {
                    document.getElementById(bookID).click();
                }
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
