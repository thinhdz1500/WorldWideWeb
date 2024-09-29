package iuh.fit.sigupservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "sigupServlet",value = "/sigup")
public class SigupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object firstName = req.getParameter("firstName");
        Object lastName = req.getParameter("lastName");
//        Object username = req.getParameter("username");
//        Object email = req.getParameter("email");
//        Object password = req.getParameter("password");
//        Object fb = req.getParameter("fb");
//        Object bio = req.getParameter("bio");

        PrintWriter out =resp.getWriter();
        out.println("<h1> Sigup success, hello "+ firstName+" "+lastName);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object firstName = req.getParameter("firstName");
        Object lastName = req.getParameter("lastName");
//        Object username = req.getParameter("username");
//        Object email = req.getParameter("email");
//        Object password = req.getParameter("password");
//        Object fb = req.getParameter("fb");
//        Object bio = req.getParameter("bio");

        PrintWriter out =resp.getWriter();
        out.println("<h1> Sigup success, hello "+ firstName+" "+lastName);
    }
}
