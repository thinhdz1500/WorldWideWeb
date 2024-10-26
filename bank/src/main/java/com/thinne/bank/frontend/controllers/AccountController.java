package com.thinne.bank.frontend.controllers;

import com.thinne.bank.backend.dtos.AccountDTO;
import com.thinne.bank.frontend.models.AccountModel;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "controller", value = "/controller")
public class AccountController extends HttpServlet {
    @Inject
    private AccountModel accountModel;
    private static final Logger LOGGER = Logger.getLogger(AccountController.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action ==null){
            List<AccountDTO> list = accountModel.getAllAccounts();
            req.setAttribute("accounts",list);
            req.getRequestDispatcher("/views/bank.jsp").forward(req,resp);
        }
        else if(action.equalsIgnoreCase("search")){
            String searchType = req.getParameter("searchType");
            String searchValue = req.getParameter("searchValue");
            if (searchValue.equalsIgnoreCase("")){
                List<AccountDTO> list = accountModel.getAllAccounts();
                req.setAttribute("accounts",list);
                req.getRequestDispatcher("/views/bank.jsp").forward(req,resp);
            }
            else if(searchType.equalsIgnoreCase("name")){
                List<AccountDTO> list = accountModel.getAccountByName(searchValue);
                LOGGER.info("Accounts found: " + list.size());
                req.setAttribute("accounts",list);
                req.getRequestDispatcher("/views/bank.jsp").forward(req,resp);
            }
            else if(searchType.equalsIgnoreCase("balance")){
                double amount = Double.parseDouble(searchValue);
                List<AccountDTO> list = accountModel.getAccountByAmount(amount);
                req.setAttribute("accounts",list);
                req.getRequestDispatcher("/views/bank.jsp").forward(req,resp);
            }
        }
    }
}
