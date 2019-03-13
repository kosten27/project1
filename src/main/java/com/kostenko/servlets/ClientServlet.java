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

@WebServlet(urlPatterns = "/clients")
public class ClientServlet extends HttpServlet {

    private ClientService clientService = new ClientServiceImpl(new ClientDBDao(new DataSourceDB()), new ValidationServiceImpl(new ClientDBDao(new DataSourceDB())));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Client> allClients = clientService.getAllClients();
        PrintWriter writer = resp.getWriter();
        for (Client client:allClients) {
            writer.println("<h1>"+ client + "</h1>");
        }
    }
}
