<%-- 
    Document   : review
    Created on : Jun 22, 2021, 3:35:54 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            table { border: 0; }
            table td { padding: 5px; }
        </style>
    </head>
    <body>
        <h1 style="color: green; text-align: center">${requestScope.orderSuccess}</h1>
        <div align="center">
            <h1>Payment Done. Thank you for purchasing our products</h1>
            <br/>
            <h2>Receipt Details:</h2>
            <table>
                <tr>
                    <td><b>Merchant:</b></td>
                    <td>Company  Tiki.</td>
                </tr>
                <tr>
                    <td><b>Payer:</b></td>
                    <td>${payer.firstName} ${payer.lastName}</td>      
                </tr>
                <tr>
                    <td><b>Description:</b></td>
                    <td>${transaction.description}</td>
                </tr>
                <tr>
                    <td><b>list Item</b></td>
                    <td>
                       
                        <c:forEach var="i" items="${requestScope.listItem.items}">
                            name: ${i.name} - quantity: ${i.quantity} - price: ${i.price} $<br/>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td><b>Subtotal:</b></td>
                    <td>${transaction.amount.details.subtotal} $</td>
                </tr>
                <tr>
                    <td><b>Shipping </b></td>
                    <td>free ship </td>
                </tr>
                <tr>
                    <td><b>Total:</b></td>
                    <td>${transaction.amount.total} $</td>
                </tr>                    
            </table>
        </div>
        <a href="MainController">back to home</a>
    </body>
</html>
