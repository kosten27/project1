package com.kostenko.servlets;

import com.kostenko.dao.DataSourceDB;
import com.kostenko.dao.impl.ClientDBDao;
import com.kostenko.services.ClientService;
import com.kostenko.services.impl.ClientServiceImpl;
import com.kostenko.servlets.filters.ClientFilter;
import com.kostenko.validators.impl.ValidationServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApp implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataSourceDB dataSource = new DataSourceDB();
        ValidationServiceImpl validationService = new ValidationServiceImpl(new ClientDBDao(dataSource));
        ClientService clientService = new ClientServiceImpl(new ClientDBDao(dataSource), validationService);
        ServletContext servletContext = sce.getServletContext();
        servletContext.addServlet("ClientServlet", new ClientServlet(clientService)).addMapping("/clients/*");
//        servletContext.addFilter("ClientFilter", new ClientFilter());
//        servletContext.addFilter("ClientFilter", new ClientFilter(validationService));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
