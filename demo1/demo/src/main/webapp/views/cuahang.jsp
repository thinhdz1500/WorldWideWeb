<%@ page language="java" contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        table,
        th,
        td {
            border: 1px solid;
        }

        table {
            width: 100%;
        }
    </style>
</head>

<body>
<div class="container" style="width: 1000px; margin: auto;">
    <h1 style="text-align: center;">CỬA HÀNG BÁN XE GẮN MÁY ABC</h1>
    <div style="display: flex; flex-direction: row; justify-content: space-between;">
        <div>
            <a href="#">Danh sách xe</a>
            <a href="#">Thêm xe</a>
        </div>
        <div>
            <input type="text" placeholder="">
            <button>Tìm kiếm</button>
        </div>
    </div>
    <div>
        <table>
            <thead>
            <tr>
                <th>STT</th>
                <th>Mã xe</th>
                <th>Tên xe</th>
                <th>giá xe</th>
                <th>Năm SX</th>
                <th>Hãng Xe</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>STT</th>
                <th>Mã xe</th>
                <th>Tên xe</th>
                <th>giá xe</th>
                <th>Năm SX</th>
                <th>Hãng Xe</th>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>