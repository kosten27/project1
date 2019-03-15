package com.kostenko.servlets;

import com.kostenko.dao.DataSourceDB;
import com.kostenko.dao.impl.ClientDBDao;
import com.kostenko.domain.Client;
import com.kostenko.services.ClientService;
import com.kostenko.services.impl.ClientServiceImpl;
import com.kostenko.validators.impl.ValidationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet("/clients")
public class ClientServlet extends HttpServlet {

//    private ClientService clientService = new ClientServiceImpl(new ClientDBDao(new DataSourceDB()), new ValidationServiceImpl(new ClientDBDao(new DataSourceDB())));
    private ClientService clientService;

    public ClientServlet(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if("/delete".equals(pathInfo)) {
            doDelete(req, resp);
            return;
        }
        resp.setContentType("text/html");
        List<Client> allClients = clientService.getAllClients();
        PrintWriter writer = resp.getWriter();
        for (Client client:allClients) {
            writer.println("<h2>"+ client + "</h2>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        clientService.createClient(name, surname, Integer.parseInt(age), email, phone);
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        clientService.deleteClient(Long.parseLong(id));
    }
}
