<%-- 
    Document   : ViewListHistory
    Created on : Jun 16, 2021, 11:03:32 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>view history Page</title>
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
        <h1 style="text-align: center">List history</h1>
        <div class="container">
            <div class="row">
                <form action="MainController">
                    Search name book <input type="text" name="title" value="${requestScope.title}" />
                    Date Order <input type="date" name="dateBooking" value="${requestScope.dateBooking}" />
                    <input type="hidden" name="UserID" value="${sessionScope.account.userID}" />
                    <input type="submit" value="Search History" name="btnAction" class="btn btn-success" />
                </form>
            </div>
        </div>
        <c:if test="${not empty requestScope.emptyMap}">
            <h1 style="text-align: center">${requestScope.emptyMap}</h1>
        </c:if>
        <c:if test="${empty requestScope.emptyMap}">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <table style="border: none; text-align: center">
                            <thead>
                                <tr>
                                    <th>ID Cart</th>
                                    <th>Date Order</th>
                                    <th>Date Ship</th>
                                    <th>Status Payment</th>
                                    <th>Book ID</th>
                                    <th>Title</th>
                                    <th>Image</th>
                                    <th>Status Book</th>
                                    <th>Category Name</th>
                                    <th>Price Book</th>
                                    <th>Quantity Book</th> 
                                    <th>Discount</th> 
                                    <th>Total Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${requestScope.listHistoryMap}">
                                    <tr>
                                        <td>${item.value[0].cart.IDcart}</td>
                                        <td>
                                            ${item.value[0].cart.dateOrder}
                                        </td>
                                        <td>
                                            ${item.value[0].cart.dateShip}
                                        </td>
                                        <td>
                                            ${item.value[0].cart.isPayment}
                                        </td>
                                        <td>
                                            <c:forEach var="i" items="${item.value}">
                                                ${i.book.bookID}<br/>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach var="i" items="${item.value}">
                                                ${i.book.title}<br/>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach var="i" items="${item.value}">
                                                ${i.book.image}<br/>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach var="i" items="${item.value}">
                                                ${i.book.status.statusName}<br/>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach var="i" items="${item.value}">
                                                ${i.book.category.categoryName}<br/>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach var="i" items="${item.value}">
                                                ${String.format("%,.0f", i.book.price)}VNĐ<br/>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach var="i" items="${item.value}">
                                                ${i.quantity}<br/>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:if test="${not empty item.value[0].cart.code.codeValue}">
                                                 ${item.value[0].cart.code.codeValue}%
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${not empty item.value[0].cart.totalPrice}">
                                                 ${String.format("%,.0f", item.value[0].cart.totalPrice)}VNĐ
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </c:if>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
