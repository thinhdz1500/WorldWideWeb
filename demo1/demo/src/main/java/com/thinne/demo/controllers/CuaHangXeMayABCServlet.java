package vn.edu.iuh.fit.donchung.demo5.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.donchung.demo5.entities.Xe;
import vn.edu.iuh.fit.donchung.demo5.repositories.HangXeDAO;
import vn.edu.iuh.fit.donchung.demo5.repositories.XeDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/cua-hang-xe-may")
public class CuaHangXeMayABCServlet extends HttpServlet {
    @Inject
    private HangXeDAO hangXeDao;

    @Inject
    private XeDAO xeDAO;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Xe> xes = xeDAO.getAll();

        req.setAttribute("xes", xes);
        req.getRequestDispatcher("views/cuahang.jsp").forward(req, resp);
    }
}
