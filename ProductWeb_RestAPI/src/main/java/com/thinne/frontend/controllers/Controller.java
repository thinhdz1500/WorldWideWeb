package com.thinne.frontend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.thinne.backend.data.entities.Product;
import com.thinne.frontend.dtos.ProductDTO;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String action = req.getParameter("action");
        switch (action.toLowerCase()) {
            case "add_product":
                addProduct(req, resp);
                break;
            case "delete_product":
                deleteProduct(req, resp);
                break;
            default:
                listProducts(req, resp);
        }
    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductDTO> productDTOS = productModel.getAllProducts();
        req.setAttribute("productDTOS", productDTOS);
        req.getRequestDispatcher("views/products.jsp").forward(req, resp);
        // Thiết lập kiểu nội dung là JSON
        // resp.setContentType("application/json");
        // resp.setCharacterEncoding("UTF-8");

        // // Chuyển đổi danh sách sản phẩm thành JSON
        // String json = new ObjectMapper().writeValueAsString(productDTOS);
        // // Gửi JSON về client
        // resp.getWriter().write(json);
    }

    private void findProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                ProductDTO productDTO = productModel.getProductById(id);
                req.setAttribute("foundProduct", productDTO);
            } catch (NumberFormatException e) {
                req.setAttribute("error", "ID sản phẩm không hợp lệ");
            } catch (Exception e) {
                req.setAttribute("error", "Không tìm thấy sản phẩm");
            }
        }
        listProducts(req, resp);
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        ProductDTO productDTO = new ProductDTO(name, description, null, price);
        productModel.addProduct(productDTO);
        resp.sendRedirect("controller?action=list_products");
//        req.setAttribute("productDTOS", productModel.getAllProducts());
//        req.getRequestDispatcher("views/products.jsp").forward(req, resp);
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {

                int id = Integer.parseInt(idParam);
                productModel.deleteProduct(id); // Gọi phương thức xóa
            } catch (NumberFormatException e) {
                req.setAttribute("error", "ID sản phẩm không hợp lệ");
            } catch (Exception e) {
                req.setAttribute("error", "Không thể xóa sản phẩm");
            }
        }
        resp.sendRedirect("controller?action=list_products");
//        req.setAttribute("productDTOS", productModel.getAllProducts());
//        req.getRequestDispatcher("views/products.jsp").forward(req, resp);
    }
}
