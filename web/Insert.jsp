<%-- 
    Document   : Insert
    Created on : Jun 13, 2021, 3:12:42 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Page</title>
    </head>
    <body>
        <h1>Insert Page</h1>
        ${requestScope.error}
        ${requestScope.addFail}
        <form method="Post" action="MainController">
            bookID <input type="text" name="bookID" value="${requestScope.bookID}" /><br/>
            <p style="color: red">${requestScope.error.bookIDEmpty}</p>
            <p style="color: red">${requestScope.error.bookIDDuplicate}</p>
            <p style="color: red">${requestScope.error.bookIDFormat}</p>
            title <input type="text" name="title" value="${requestScope.title}" /><br/>
            <p style="color: red">${requestScope.error.titleEmpty}</p>
            <p style="color: red">${requestScope.error.titleLength}</p>
            author <input type="text" name="author" value="${requestScope.author}" /><br/>
            <p style="color: red">${requestScope.error.authorEmpty}</p>
            <p style="color: red">${requestScope.error.authorLength}</p>
            image <input type="text" name="image" value="" /><br/>
            <p style="color: red">${requestScope.error.imageEmpty}</p>
            <p style="color: red">${requestScope.error.imageFormat}</p>
            price <input type="number" name="price" value="${requestScope.price}" min="0"/><br/>
            <p style="color: red">${requestScope.error.priceEmpty}</p>
            quantity <input type="number" name="quantity" value="${requestScope.quantity}" min="0"/><br/>
            <p style="color: red">${requestScope.error.quanityEmpty}</p>
            category name <select name="categoryID">
                <c:forEach var="item" items="${requestScope.listCategory}">
                    <option value="${item.categoryID}" ${requestScope.categoryID eq item.categoryID ? "selected" : ""}>${item.categoryName}</option>
                </c:forEach>
            </select><br/>
            <label for="des">description</label><br/>
            <textarea class="form-control" rows="3" id="des" name="description">${requestScope.description}</textarea><br/>
             <p style="color: red">${requestScope.error.descriptionEmpty}</p>
             <p style="color: red">${requestScope.error.descriptionLength}</p>
            <input type="submit" value="Submit Insert" name="btnAction"/>
        </form>
    </body>
</html>
