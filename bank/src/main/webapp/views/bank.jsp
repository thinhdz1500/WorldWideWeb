<%--
  Created by IntelliJ IDEA.
  User: THINKKK
  Date: 10/27/2024
  Time: 12:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<th><fmt:formatNumber value="${ac.amount}" type="number" /></th>--%>
<html>
<head>
    <title>Controll Account</title>
</head>
<body>
<div>
    <form action="controller" method="get">
        <select name="searchType">
            <option value="name">Name</option>
            <option value="balance">Balance</option>
        </select>
        <input type="hidden" name="action" value="search"/>
        <input type="text" name="searchValue" placeholder="Enter search value"  />
        <button type="submit">Search</button>
    </form>
    <table style="width: 100%;">
        <thead>
        <tr>
            <th>account_number</th>
            <th>name</th>
            <th>card_number</th>
            <th>address</th>
            <th>amount</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ac" items="${accounts}">
            <tr>
                <th>${ac.accountNumber}</th>
                <th>${ac.name}</th>
                <th>${ac.cardNumber}</th>
                <th>${ac.address}</th>
                <th><fmt:formatNumber value="${ac.amount}" type="number" /></th>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>
