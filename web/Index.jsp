<%-- 
    Document   : index
    Created on : Jun 12, 2021, 1:43:47 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>Index Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Tiki</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="MainController">Home <span class="sr-only">(current)</span></a>
                    </li>
                </ul>
                <c:if test="${empty sessionScope.account}">
                    <a class="btn btn-success my-2 my-sm-0"  href="Login.html">Login</a>
                </c:if>
                <c:forEach var="item" items="${sessionScope.listCart}">
                    <c:set var="totalQuanlity" value="${totalQuanlity + item.value}"/>
                </c:forEach>
                <c:if test="${not empty sessionScope.account}">
                    <a href="MainController?btnAction=ViewHistory&UserID=${sessionScope.account.userID}" class="btn btn-success">View History</a>
                    <div style="margin: 0px 50px">
                        <a href="MainController?btnAction=ViewListCart">
                            <i class="fas fa-shopping-cart">
                                (${totalQuanlity > 0 ? totalQuanlity : 0})
                            </i>
                        </a>
                    </div>
                    <h1 style="color: white">Hello, ${sessionScope.account.userName}</h1>
                    <a class="btn btn-success my-2 my-sm-0"  href="MainController?btnAction=Logout">logout</a>
                </c:if>

            </div>
        </nav>

        <h1 style="color: red; text-align: center">${requestScope.errorAddToCart}</h1>
        
        <div class="container mt-5">
            <div class="row">
                <form class="form-inline my-2 my-lg-0" action="MainController" method="Post">
                    <select style="padding: 7px 7px; margin: 0px 10px" name="category">
                        <c:forEach items="${requestScope.listCategory}" var="item">
                            <option value="${item.categoryName}" ${requestScope.category eq item.categoryName ? "selected" : ""}>${item.categoryName}</option>
                        </c:forEach>
                    </select>
                    <input class="form-control mr-sm-2" name="title" value="${requestScope.title}" type="search" placeholder="Search" aria-label="Search">
                    <input class="form-control mr-sm-2" name="min" value="${requestScope.min}" type="number" placeholder="min" min="0" aria-label="Min">
                    <input class="form-control mr-sm-2" name="max" value="${requestScope.max}" type="number" placeholder="max"  aria-label="Max">
                    <button class="btn btn-success my-2 my-sm-0" type="submit" name="btnAction" value="search">Search</button>
                </form>
                <a href="MainController?btnAction=Reset" class="btn btn-success my-2 my-sm-0 ml-2 px-2">Reset</a>
            </div>
        </div>
        <c:if test="${empty requestScope.mess}">
            <div class="container mt-5">
                <div class="row">
                    <c:forEach items="${requestScope.listBook}" var="item">
                        <div class="col-4 mr-auto mb-3">
                            <div class="card" style="width: 18rem;">
                                <img style="width: 100px; height: 100px" class="card-img-top" src="./image/${item.image}.jpeg">
                                <div class="card-body">
                                    <h5 class="card-title">Title: ${item.title}</h5>
                                    <h5 class="card-title">Author: ${item.author}</h5>
                                    <h5 class="card-title">Quantity ${item.quantity}</h5>
                                    <p class="card-text">Description: ${item.description}</p>
                                    <p class="card-text">Price:  ${item.price}$</p>
                                    <p class="card-text">Date: ${item.date}</p>
                                    <a href="MainController?btnAction=AddToCart&bookID=${item.bookID}" class="btn btn-primary">Buy</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>

        <c:if test="${not empty requestScope.mess}">
            <div class="container mt-5">
                <div class="row">
                    <h1 style="color: green">${requestScope.mess}</h1>
                </div>
            </div>
        </c:if>




        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
