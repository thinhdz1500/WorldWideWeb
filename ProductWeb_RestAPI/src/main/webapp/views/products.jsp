<%@ page import="com.thinne.frontend.dtos.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
</head>
<body>
    <h1>Danh sách sản phẩm</h1>
    
    <div>
        <form action="controller" method="get">
            <input type="hidden" name="action" value="find_product">
            <label for="productId">Tìm sản phẩm theo ID:</label>
            <input type="text" id="productId" name="id" required>
            <input type="submit" value="Tìm kiếm">
        </form>
    </div>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
            out.println("<p style='color: red;'>" + error + "</p>");
        }

        Product foundProduct = (Product) request.getAttribute("foundProduct");
        if (foundProduct != null) {
            out.println("<h3>Kết quả tìm kiếm:</h3>");
            out.println("<p>" + foundProduct + "</p>");
        }
    %>

    <h2>Tất cả sản phẩm:</h2>
    <ul>
    <%
        List<Product> products = (List<Product>) request.getAttribute("products");
        if (products != null) {
            for (Product product : products) {
                out.println("<li>" + product + "</li>");
            }
        }
    %>
    </ul>

    <p><a href="controller?action=list_products">Làm mới danh sách</a></p>
</body>
</html>
