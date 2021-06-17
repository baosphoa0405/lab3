<%-- 
    Document   : DetailBook
    Created on : Jun 13, 2021, 6:23:07 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Update Book</h1>
        <form method="Post" action="MainController">
            BookID: <input type="text" name="bookID" disabled value="${requestScope.bookDetail.bookID}" /> <br/>
            <input type="hidden" name="bookID"  value="${requestScope.bookDetail.bookID}" /> <br/>
            title: <input type="text" name="title" value="${requestScope.bookDetail.title}" /><br/>
            img <input type="text" name="image" value="${requestScope.bookDetail.image}" /><br/>
            author <input type="text" name="author" value="${requestScope.bookDetail.author}" /><br/>
            quantity <input type="number" min="0" name="quantity" value="${requestScope.bookDetail.quantity}" /><br/>
            price <input type="numer" min="0" name="price" value="${requestScope.bookDetail.price}" /><br/>
            description  <textarea class="form-control" rows="3" id="des" name="description">${requestScope.bookDetail.description}</textarea><br/>
            status<select name="status">
                <c:forEach var="item" items="${requestScope.listStatus}">
                    <option value="${item.statusName}" ${requestScope.bookDetail.status.statusID eq item.statusID ? "selected" : ""}>${item.statusName}</option>
                </c:forEach>
            </select> <br/>
            category <select name="categoryID">
                <c:forEach var="item" items="${requestScope.listCategory}">
                    <option value="${item.categoryID}" ${requestScope.bookDetail.category.categoryName}>${item.categoryName}</option>
                </c:forEach>
            </select><br/>
            date: <input type="date" name="date" value="${requestScope.bookDetail.date}"/><br/>
            <input type="submit" value="Updated"  name="btnAction"/>
        </form>
    </body>
</html>
