<%-- 
    Document   : viewListCart
    Created on : Jun 15, 2021, 3:09:26 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>View List Cart Page</title>
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
        <h1 style="text-align: center">Cart shopping</h1>
        <h1 style="text-align: center;color: red">${requestScope.orderFail}</h1>
        <c:if test="${empty sessionScope.listCart}">
            <h1 style="text-align: center">No list Cart Please Buy Something</h1>
        </c:if>
        <c:if test="${not empty sessionScope.listCart}">
            <div class="container">
                <div class="row">
                    <div class="col-9">
                        <table  style="margin-inline: auto; text-align: center; border: none">
                            <thead>
                                <tr>
                                    <th>BookID</th>
                                    <th>Date</th>
                                    <th>Tittle</th>
                                    <th>Image</th>
                                    <th>Category</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th>Quantity In Cart</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${requestScope.listBook}">
                                    <c:forEach var="itemCart" items="${sessionScope.listCart}">
                                        <c:if test="${itemCart.key eq item.bookID}">
                                            <tr>
                                                <td>${item.bookID}</td>
                                                <td>${item.date}</td>
                                                <td>${item.title}</td>
                                                <td>${item.image}</td>
                                                <td>${item.category.categoryName}</td>
                                                <td>${String.format("%,.0f", item.price)}Đ</td>
                                                <td>${item.quantity}</td>
                                                <td>${String.format("%,.0f", item.price * itemCart.value)}Đ</td>
                                                <td>
                                                    <a class="btn btn-danger" disabled="${not empty requestScope.mess ? "true" : "false"}" 
                                                       style="cursor: ${not empty requestScope.mess && requestScope.bookID eq item.bookID ? "not-allowed" : ""}"
                                                       href="MainController?btnAction=AddDeleteToCart&bookID=${item.bookID}&flag=true">+</a>   
                                                    <span style="padding: ${itemCart.value < 10 ? "5px" : "0px"}">${itemCart.value}</span>
                                                    <a class="btn btn-danger" href="MainController?btnAction=AddDeleteToCart&bookID=${item.bookID}&flag=false">-</a>
                                                </td>
                                                <td>
                                                    <a class="btn btn-success" href="MainController?btnAction=AddDeleteToCart&Action=Removed&bookID=${item.bookID}">Remove</a>
                                                </td>
                                                <c:set var="total" value="${total + (item.price * itemCart.value)}"/>
                                                <c:if test="${not empty requestScope.mess && item.bookID eq requestScope.bookID}">
                                                    <td style="color: red">${requestScope.mess}</td>
                                                </c:if>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-3">
                        <h6>Information Bill</h6>
                        <p>Total: ${total > 0 ? String.format("%,.0f", total) : 0} Đ</p>
                        <form method="Post" action="MainController">
                            <span>DateBook: <input type="date" id="dateOrder" name="dateOrder" value="${requestScope.dateOrder}" disabled="true"> </span>
                            <label for="discount">Choose promotion: </label>
                            <select id="discount" name="code">
                                <c:forEach var="item" items="${requestScope.listCode}">
                                    <option value="${item.codeValue}">${item.codeValue}%</option>
                                </c:forEach>
                            </select><br/>
                            <span>DateShip: <input type="date" name="dateShip" value=""  min="${requestScope.dateOrder}"/>
                                <span style="color: red">${requestScope.errorDateShip}</span>   
                                <input type="hidden" name="total" value="${total}" />
                                <input type="hidden" name="dateOrder" value="${requestScope.dateOrder}" />
                                <input  ${not empty requestScope.mess ? "disabled" : ""} 
                                    style="cursor: ${not empty requestScope.mess  ? "not-allowed" : ""}"   class="btn btn-danger" type="submit" value="Checkout" name="btnAction" />
                        </form>
                    </div>
                    <!--return confirm('Are you sure checkout?')-->
                </div>
            </div>
        </c:if>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
