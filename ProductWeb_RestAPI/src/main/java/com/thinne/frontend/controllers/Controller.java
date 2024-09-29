package com.thinne.frontend.controllers;

import com.thinne.frontend.dtos.Product;
import com.thinne.frontend.models.ProductModel;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Controller", value = "/controller")
public class Controller extends HttpServlet {
    @Inject
    private ProductModel productModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list_products"; // Default action
        }

        switch (action.toLowerCase()) {
            case "list_products":
                listProducts(req, resp);
                break;
            case "find_product":
                findProduct(req, resp);
                break;
            default:
                listProducts(req, resp);
        }
    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productModel.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("views/products.jsp").forward(req, resp);
    }

    private void findProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Product product = productModel.getProductById(id);
                req.setAttribute("foundProduct", product);
            } catch (NumberFormatException e) {
                req.setAttribute("error", "ID sản phẩm không hợp lệ");
            } catch (Exception e) {
                req.setAttribute("error", "Không tìm thấy sản phẩm");
            }
        }
        listProducts(req, resp);
    }
}
