package com.kostenko.servlets;

import com.kostenko.domain.Client;
import com.kostenko.services.ClientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientServlet extends HttpServlet {

    private ClientService clientService;

    public ClientServlet(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Client> allClients = clientService.getAllClients();
        List<String> clients = new ArrayList<>();
        for (Client client : allClients) {
            clients.add(client.toString());
        }
        req.setAttribute("clients", clients);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("clients/clients.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String method = req.getParameter("method");
        if ("delete".equals(method)) {
            doDelete(req, resp);
            return;
        } else if ("put".equals(method)) {
            doPut(req, resp);
            return;
        }

        System.out.println("doPost");

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        clientService.createClient(name, surname, Integer.parseInt(age), email, phone);
        doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientId = req.getParameter("clientId");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        clientService.modifyClient(Long.parseLong(clientId), name, surname, Integer.parseInt(age), email, phone);
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String clientId = req.getParameter("clientId");
        clientService.deleteClient(Long.parseLong(clientId));
        doGet(req, resp);
    }
}
